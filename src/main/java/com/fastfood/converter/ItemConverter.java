package com.fastfood.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ItemDTO;
import com.fastfood.entity.ItemEntity;
import com.fastfood.repository.ProductRepository;

@Component
public class ItemConverter {

	@Autowired
	ProductConverter productConverter;
	
	@Autowired
	ProductRepository productRepository;

	public ItemDTO toDTO(ItemEntity entity) {
		ItemDTO dto = new ItemDTO();
		dto.setId(entity.getId());
		dto.setTotalPay(entity.getTotalPay());
		dto.setQuantity(entity.getQuantity());
		dto.setPrice(entity.getPrice());
		dto.setProductDTO(productConverter.toDTO(entity.getProductEntity()));

		if (entity.getCreatedBy() != null) {
			dto.setCreatedBy(entity.getCreatedBy());
		}
		if (entity.getCreatedDate() != null) {
			dto.setCreatedDate(entity.getCreatedDate());
		}
		if (entity.getModifiedBy() != null) {
			dto.setModifiedBy(entity.getModifiedBy());
		}
		if (entity.getModifiedDate() != null) {
			dto.setModifiedDate(entity.getModifiedDate());
		}

		return dto;
	}

	public ItemEntity toEntity(ItemDTO dto) {
		ItemEntity entity = new ItemEntity();
		entity.setTotalPay(dto.getTotalPay());
		entity.setQuantity(dto.getQuantity());
		entity.setPrice(dto.getPrice());
		entity.setProductEntity(productConverter.toEntity(productRepository.findOne(dto.getProductDTO().getId()),dto.getProductDTO()));

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

	public ItemEntity toEntity(ItemEntity result, ItemDTO dto) {

		result.setTotalPay(dto.getTotalPay());
		result.setQuantity(dto.getQuantity());
		result.setPrice(dto.getPrice());
		result.setProductEntity(productConverter.toEntity(result.getProductEntity(), dto.getProductDTO()));

		return result;
	}

}
