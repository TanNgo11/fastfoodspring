package com.fastfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastfood.converter.OrderConverter;
import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.OrderEntity;
import com.fastfood.repository.OrderRepository;
import com.fastfood.service.IOrderService;

@Service
public class OrderService implements IOrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderConverter orderConverter;

	@Override
	public OrderDTO save(OrderDTO dto) {
		OrderEntity entity = orderConverter.toEntity(dto);
		return orderConverter.toDTO(orderRepository.save(entity));
	}

}
