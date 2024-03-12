package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;

@Component
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;

	public CategoryDTO mapToDTO(CategoryEntity categoryEntity) {
		CategoryDTO dto = new CategoryDTO();
		if (categoryEntity.getId() != 0)
			dto.setId(categoryEntity.getId());
		dto.setCreatedDate(categoryEntity.getCreatedDate());
		dto.setType(categoryEntity.getType());

		return dto;
	}

	public CategoryEntity mapToEntity(CategoryDTO categoryDTO) {
		return modelMapper.map(categoryDTO, CategoryEntity.class);
	}
}
