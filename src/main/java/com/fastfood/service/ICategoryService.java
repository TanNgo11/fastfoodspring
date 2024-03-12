package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.dto.CategoryRevenueDTO;

public interface ICategoryService {
	List<CategoryDTO> findAllbyPage(Pageable pageable);

	CategoryDTO findByType(String type);

	ApiResponse softDeleteCategory(Long id);

	ApiResponse activeCategory(Long id);

	CategoryDTO save(CategoryDTO categoryDTO);

	int getTotalCate();

	List<CategoryDTO> findAll();

	CategoryDTO findByID(Long id);

	List<CategoryRevenueDTO> getRevenueByCategory();
	
	List<CategoryDTO> findAllByPage(Pageable pageable);
}
