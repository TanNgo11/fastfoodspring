package com.fastfood.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.NewsDTO;
import com.fastfood.service.INewsService;

@RestController(value = "newsAPIofUser")
@RequestMapping("/api/v1")
public class NewsAPI {
	@Autowired
	private INewsService service;

	@GetMapping("/news")
	public ResponseEntity<NewsDTO> loadAllNews(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "2") int limit) {
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setPage(page);
		newsDTO.setLimit(limit);
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");

		Pageable pageable = new PageRequest(page - 1, limit, sort);
		Page<NewsDTO> pageResult = service.findActiveNews(page, limit);
		newsDTO.setListResult(pageResult.getContent());
		newsDTO.setTotalItem((int) pageResult.getTotalElements());
		newsDTO.setTotalPage(pageResult.getTotalPages());
		
		
		return new ResponseEntity<>(newsDTO, HttpStatus.OK);
	}

}
