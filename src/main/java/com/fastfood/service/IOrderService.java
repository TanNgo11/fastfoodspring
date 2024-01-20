package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.OrderDTO;
import com.fastfood.entity.PaymentDetail;

public interface IOrderService {
	OrderDTO save(OrderDTO order, PaymentDetail paymentDetail);

	List<OrderDTO> findAll();

	List<OrderDTO> findAll(Pageable pageable);

	int getTotalOrder();

	List<OrderDTO> findAllByMonthAndYear(int month, int year, Pageable pageable);
	
	List<OrderDTO> findAllByMonthAndYear(int month, int year);

}
