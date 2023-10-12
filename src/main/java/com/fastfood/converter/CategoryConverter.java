package com.fastfood.converter;

import org.springframework.stereotype.Component;

import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;

@Component
public class CategoryConverter {

	public CategoryDTO toDTO(CategoryEntity enitity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setType(enitity.getType());

		return dto;
	}

	public CategoryEntity toEntity(CategoryDTO dto) {
		CategoryEntity entity = new CategoryEntity();
		entity.setType(dto.getType());

		return entity;
	}

	public CategoryEntity toEntity(CategoryEntity result, CategoryDTO dto) {

		result.setType(dto.getType());

		return result;
	}
}
