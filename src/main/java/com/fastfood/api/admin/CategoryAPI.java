package com.fastfood.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.NewsDTO;
import com.fastfood.service.ICategoryService;

@RestController
@RequestMapping("/admin/api/v1")
public class CategoryAPI {

	@Autowired
	private ICategoryService cateService;
	
	
	@GetMapping("/categories")
	public ResponseEntity<CategoryDTO> loadAllCategory(
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setPage(page);
		categoryDTO.setLimit(limit);
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");

		Pageable pageable = new PageRequest(page - 1, limit, sort);
		categoryDTO.setListResult(cateService.findAllByPage(pageable));
		categoryDTO.setTotalItem(cateService.getTotalCate());

		categoryDTO.setTotalPage((int) Math.ceil((double) categoryDTO.getTotalItem() / categoryDTO.getLimit()));

		return new ResponseEntity<>(categoryDTO, HttpStatus.OK);
	}

	@GetMapping("/category/name/{name}")
	public ResponseEntity<CategoryDTO> findByName(@PathVariable String name) {
		return new ResponseEntity<>(cateService.findByType(name), HttpStatus.OK);
	}

	@GetMapping("/category/id/{id}")
	public ResponseEntity<CategoryDTO> findByID(@PathVariable Long id) {
		return new ResponseEntity<>(cateService.findByID(id), HttpStatus.OK);
	}

	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> save(@RequestBody CategoryDTO categoryDTO) {
		categoryDTO.setStatus(SystemConstant.ACTIVE_STATUS);
		return new ResponseEntity<>(cateService.save(categoryDTO), HttpStatus.OK);
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<ApiResponse> disableCategoryByID(@PathVariable Long id) {
		return new ResponseEntity<>(cateService.softDeleteCategory(id), HttpStatus.OK);
	}

	@PutMapping("/category/{id}")
	public ResponseEntity<ApiResponse> activeCategoryByID(@PathVariable Long id) {
		return new ResponseEntity<>(cateService.activeCategory(id), HttpStatus.OK);
	}
	
	@PutMapping("/categories/{id}")
	public ResponseEntity<ApiResponse> changeCategoryNameByID(@PathVariable Long id, @RequestParam String type, @RequestParam int status) {
		
		System.out.println(status);
		return new ResponseEntity<>(cateService.changeCategoryNameById(id, type,status ), HttpStatus.OK);
	}
}