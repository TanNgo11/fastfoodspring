package com.fastfood.api.admin;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.NewsDTO;
import com.fastfood.service.INewsService;
import com.fastfood.service.impl.NewsService;
import com.fastfood.utils.FileUploadUtil;

@RestController(value = "newsAPIofAdmin")
@RequestMapping("/admin/api/v1")
public class NewsAPI {
	@Autowired
	private INewsService service;

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@GetMapping("/news")
	public ResponseEntity<NewsDTO> loadAllNews(@RequestParam("page") int page, @RequestParam("limit") int limit) {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setPage(page);
		newsDTO.setLimit(limit);
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");

		Pageable pageable = new PageRequest(page - 1, limit, sort);
		newsDTO.setListResult(service.findAllByPage(pageable));
		newsDTO.setTotalItem(service.getTotalNews());

		newsDTO.setTotalPage((int) Math.ceil((double) newsDTO.getTotalItem() / newsDTO.getLimit()));

		return new ResponseEntity<>(newsDTO, HttpStatus.OK);
	}

	@PostMapping("/news")
	public ResponseEntity<NewsDTO> save(@RequestParam(name = "files", required = false) MultipartFile[] files,
			@RequestParam("title") String title, HttpServletRequest request,
			@RequestParam("description") String description) {

		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setTitle(title);
		newsDTO.setDescription(description);
		newsDTO.setStatus(SystemConstant.DRAFT);
		newsDTO = service.save(newsDTO);

		if (newsDTO != null) {
			List<String> imgs = fileUploadUtil.saveNewsFiles(files, request, newsDTO.getId());
			newsDTO.setImageURL(imgs.get(0));

		}

		return new ResponseEntity<>(service.save(newsDTO), HttpStatus.OK);
	}

	@PostMapping("/news/{id}")
	public  ResponseEntity<NewsDTO> editNewsByID(
			@RequestParam(name = "files", required = false) MultipartFile[] files, @RequestParam("title") String title,
			HttpServletRequest request, @RequestParam("description") String description, @PathVariable Long id) {

		NewsDTO newsDTO = service.findByID(id);
		newsDTO.setTitle(title);
		newsDTO.setDescription(description);
		newsDTO.setStatus(SystemConstant.DRAFT);

		List<String> imgs = fileUploadUtil.saveNewsFiles(files, request, newsDTO.getId());
		newsDTO.setImageURL(imgs.get(0));

		return new ResponseEntity<>(service.save(newsDTO), HttpStatus.OK);
	}

	@DeleteMapping("/news/{id}")
	public ResponseEntity<ApiResponse> disableNewsByID(@PathVariable Long id) {
		return new ResponseEntity<>(service.softDeleteNews(id), HttpStatus.OK);
	}

}
