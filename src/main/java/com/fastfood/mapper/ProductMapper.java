package com.fastfood.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ImageEntity;
import com.fastfood.entity.ProductEntity;
import com.sun.mail.handlers.image_gif;

@Component
public class ProductMapper {

	private final ModelMapper modelMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;

		modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
				.addMapping(src -> src.getCategory(), ProductDTO::setCategoryDTO)
				.addMapping(src -> src.getImageEntities(), ProductDTO::setListImage);

		modelMapper.createTypeMap(ProductDTO.class, ProductEntity.class)
				.addMapping(scr -> scr.getCategoryDTO(), ProductEntity::setCategory)
				.addMapping(scr -> scr.getListImage(), ProductEntity::setImageEntities);
	}

	public ProductDTO mapToDTO(ProductEntity productEntity) {
		return modelMapper.map(productEntity, ProductDTO.class);
	}

	public ProductEntity mapToEntity(ProductDTO productDTO) {
		ProductEntity result = modelMapper.map(productDTO, ProductEntity.class);

		if (result.getImageEntities() != null) {
			List<ImageEntity> listImage = result.getImageEntities();
			for (ImageEntity imageEntity : listImage) {
				imageEntity.setProductEntity(result);
			}
		}

		return result;
	}

}
