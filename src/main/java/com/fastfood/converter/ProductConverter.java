package com.fastfood.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ProductEntity;

@Component
public class ProductConverter {
	
	@Autowired
	CategoryConverter categoryConverter;

	public ProductDTO toDTO(ProductEntity productEntity) {
		ProductDTO product = new ProductDTO();
		product.setId(productEntity.getId());
		product.setProductName(productEntity.getProductName());
		product.setPrice(productEntity.getPrice());
		product.setImg(productEntity.getImg());
		product.setCategoryDTO(categoryConverter.toDTO(productEntity.getCategory()));
		product.setSalePrice(productEntity.getSalePrice());
		product.setDescription(productEntity.getDescription());
		if (productEntity.getCreatedBy() != null) {
			product.setCreatedBy(productEntity.getCreatedBy());
		}
		if (productEntity.getCreatedDate() != null) {
			product.setCreatedDate(productEntity.getCreatedDate());
		}
		if (productEntity.getModifiedBy() != null) {
			product.setModifiedBy(productEntity.getModifiedBy());
		}
		if (productEntity.getModifiedDate() != null) {
			product.setModifiedDate(productEntity.getModifiedDate());
		}
		return product;
	}

	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();

		entity.setProductName(dto.getProductName());
		entity.setPrice(dto.getPrice());
		entity.setImg(dto.getImg());
		entity.setCategory(categoryConverter.toEntity(dto.getCategoryDTO()));
		entity.setSalePrice(dto.getSalePrice());
		entity.setDescription(dto.getDescription());
		if (dto.getCreatedBy() != null) {
			entity.setCreatedBy(dto.getCreatedBy());
		}
		if (dto.getCreatedDate() != null) {
			entity.setCreatedDate(dto.getCreatedDate());
		}
		if (dto.getModifiedBy() != null) {
			entity.setModifiedBy(dto.getModifiedBy());
		}
		if (dto.getModifiedDate() != null) {
			entity.setModifiedDate(dto.getModifiedDate());
		}
		return entity;
	}
	
	public ProductEntity toEntity(ProductEntity result,ProductDTO dto) {
		result.setProductName(dto.getProductName());
		result.setPrice(dto.getPrice());
		result.setImg(dto.getImg());
		result.setCategory(categoryConverter.toEntity(dto.getCategoryDTO()));
		result.setSalePrice(dto.getSalePrice());
		result.setDescription(dto.getDescription());
		if (dto.getCreatedBy() != null) {
			result.setCreatedBy(dto.getCreatedBy());
		}
		if (dto.getCreatedDate() != null) {
			result.setCreatedDate(dto.getCreatedDate());
		}
		if (dto.getModifiedBy() != null) {
			result.setModifiedBy(dto.getModifiedBy());
		}
		if (dto.getModifiedDate() != null) {
			result.setModifiedDate(dto.getModifiedDate());
		}
		return result;
	}
}
