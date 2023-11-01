package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.OrderDTO;

public interface IOrderService {
	OrderDTO save(OrderDTO order);
	List<OrderDTO> findAll(Pageable pageable);
	int getTotalOrder();

}
