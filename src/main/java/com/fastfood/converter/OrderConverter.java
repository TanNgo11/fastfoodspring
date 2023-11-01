package com.fastfood.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.ItemEntity;
import com.fastfood.entity.OrderEntity;
import com.fastfood.repository.UserRepository;

@Component
public class OrderConverter {
	@Autowired
	AccountConverter accountConverter;

	@Autowired
	ItemConverter itemConverter;

	@Autowired
	UserRepository userRepository;

	public OrderDTO toDTO(OrderEntity entity) {

		OrderDTO dto = new OrderDTO();
		dto.setId(entity.getId());
		dto.setAddress(entity.getAddress());
		dto.setEmail(entity.getEmail());
		dto.setPhonenumber(entity.getPhonenumber());
		dto.setTotalPay(entity.getTotalPay());
		dto.setCustomerName(entity.getCustomerName());
		dto.setAccountDTO(accountConverter.toDTO(entity.getAccountEntity()));
		List<ItemDTO> listItem = new ArrayList<ItemDTO>();
		List<ItemEntity> entities = entity.getItems();
		for (ItemEntity itemEntity : entities) {
			listItem.add(itemConverter.toDTO(itemEntity));
		}
		dto.setItems(listItem);

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

	public OrderEntity toEntity(OrderDTO dto) {
		OrderEntity entity = new OrderEntity();
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getAccountDTO().getUsername(),
				SystemConstant.ACTIVE_STATUS);

		entity.setAddress(dto.getAddress());
		entity.setEmail(dto.getEmail());
		entity.setPhonenumber(dto.getPhonenumber());
		entity.setTotalPay(dto.getTotalPay());
		entity.setCustomerName(accountEntity.getFullName());
		entity.setAccountEntity(accountConverter.toEntity(accountEntity,dto.getAccountDTO()));

		List<ItemDTO> dtos = dto.getItems();
		List<ItemEntity> entities = new ArrayList<>();
		for (ItemDTO idto : dtos) {
			entities.add(itemConverter.toEntity(idto));
		}
		entity.setItems(entities);

		return entity;
	}
}
