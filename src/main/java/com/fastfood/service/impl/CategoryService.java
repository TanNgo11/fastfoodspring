package com.fastfood.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastfood.converter.CategoryConverter;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;
import com.fastfood.repository.CategoryRepository;
import com.fastfood.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	CategoryConverter converter;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryEntity> categories = categoryRepository.findAll();
		List<CategoryDTO> result = categories.stream().map(i -> converter.toDTO(i)).collect(Collectors.toList());
		return result;
	}

}
