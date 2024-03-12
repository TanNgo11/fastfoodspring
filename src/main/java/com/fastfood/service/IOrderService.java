package com.fastfood.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.MonthlyTotalDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.TopSpenderDTO;
import com.fastfood.entity.PaymentDetail;

public interface IOrderService {
	OrderDTO save(OrderDTO order, PaymentDetail paymentDetail);

	List<OrderDTO> findAll();

	List<OrderDTO> findAll(Pageable pageable);

	int getTotalOrder();

	Integer countAllOrdersByMonthAndYear(int month, int year);

	Integer countAllOrdersByMonthAndYearAndStatus(int month, int year, int status);

	List<OrderDTO> findAllByMonthAndYearAndStatus(int month, int year, int status, Pageable pageable);

	List<OrderDTO> findAllByMonthAndYear(int month, int year, Pageable pageable);

	List<OrderDTO> findAllByMonthAndYear(int month, int year);

	OrderDTO getOrderbyId(long id);

	ApiResponse updateOrderStatus(long orderId, String status);

	BigDecimal findTotalSumOfAllOrders();

	BigDecimal findTotalSumForCurrentDate();

	List<TopSpenderDTO> findTopBuyers();

	Page<OrderDTO> findOrdersByAccount(AccountDTO accountDTO, Pageable pageable);

	OrderDTO findByIdAndUserId(long orderId, long userId);

	List<MonthlyTotalDTO> getTotalPaymentsByMonthForYear(int year);

}
