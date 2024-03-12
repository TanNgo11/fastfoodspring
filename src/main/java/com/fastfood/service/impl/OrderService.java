package com.fastfood.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.MonthlyTotalDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.TopSpenderDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.OrderEntity;
import com.fastfood.entity.PaymentDetail;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.mapper.OrderMapper;
import com.fastfood.repository.OrderRepository;
import com.fastfood.service.IOrderService;
import com.fastfood.utils.MessageUtil;

@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Override
	@Transactional
	public OrderDTO save(OrderDTO dto, PaymentDetail paymentDetail) {
		OrderEntity entity = orderMapper.mapToEntity(dto);
		entity.setPaymentDetail(paymentDetail);
		return orderMapper.mapToDTO(orderRepository.save(entity));
	}

	@Override
	public List<OrderDTO> findAll(Pageable pageable) {
		List<OrderEntity> entities = orderRepository.findAll(pageable).getContent();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public int getTotalOrder() {

		return (int) orderRepository.count();
	}

	@Override
	public List<OrderDTO> findAllByMonthAndYearAndStatus(int month, int year, int status, Pageable pageable) {
		List<OrderEntity> entities = orderRepository.findAllOrdersByMonthAndYearAndStatus(month, year, status,
				pageable);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findAll() {
		List<OrderEntity> entities = orderRepository.findAll();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findAllByMonthAndYear(int month, int year) {
		List<OrderEntity> entities = orderRepository.findAllOrdersByMonthAndYear(month, year);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> findAllByMonthAndYear(int month, int year, Pageable pageable) {
		List<OrderEntity> entities = orderRepository.findAllOrdersByMonthAndYear(month, year, pageable);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(order -> orderMapper.mapToDTO(order)).collect(Collectors.toList());
	}

	@Override
	public Integer countAllOrdersByMonthAndYear(int month, int year) {
		Long count = orderRepository.countAllOrdersByMonthAndYear(month, year);

		if (count != null && count <= Integer.MAX_VALUE) {
			return count.intValue();
		} else {

			throw new IllegalStateException("Count is either null or exceeds the maximum value of an integer.");
		}
	}

	@Override
	public Integer countAllOrdersByMonthAndYearAndStatus(int month, int year, int status) {
		Long count = orderRepository.countAllOrdersByMonthAndYearAndStatus(month, year, status);

		if (count != null && count <= Integer.MAX_VALUE) {
			return count.intValue();
		} else {

			throw new IllegalStateException("Count is either null or exceeds the maximum value of an integer.");
		}
	}

	@Override
	public OrderDTO getOrderbyId(long id) {
		return orderMapper.mapToDTO(orderRepository.getOne(id));
	}

	@Override
	public ApiResponse updateOrderStatus(long orderId, String status) {
		int newStatus = 0;
		switch (status) {

		case "delivery":
			newStatus = SystemConstant.ORDER_STATUS_DELIVERY;
			break;

		case "completed":
			newStatus = SystemConstant.ORDER_STATUS_COMPLETED;
			break;

		case "canceled":
			newStatus = SystemConstant.ORDER_STATUS_CANCEL;
			break;

		default:
			newStatus = SystemConstant.ORDER_STATUS_PENDING;
			break;
		}
		if (orderRepository.updateOrderStatus(orderId, newStatus) != 0) {
			return new ApiResponse(Boolean.TRUE, MessageUtil.SUCCESS_CHANGE, HttpStatus.OK);
		}

		return new ApiResponse(Boolean.TRUE, MessageUtil.ERROR_CHANGE, HttpStatus.OK);

	}

	@Override
	public BigDecimal findTotalSumOfAllOrders() {
		return orderRepository.findTotalSumOfAllOrders();
	}

	@Override
	public BigDecimal findTotalSumForCurrentDate() {
		return orderRepository.findTotalSumForCurrentDate();
	}

	@Override
	public List<TopSpenderDTO> findTopBuyers() {
		Pageable pageable = new PageRequest(0, 10);

		Page<Object[]> topSpenders = orderRepository.findTopBuyers(pageable);
		List<TopSpenderDTO> topSpendersDTO = new ArrayList<>();

		for (Object[] record : topSpenders.getContent()) {
			AccountEntity accountEntity = (AccountEntity) record[0];
			AccountDTO account = accountMapper.mapToDTO(accountEntity);
			Double totalSpent = (Double) record[1];

			TopSpenderDTO dto = new TopSpenderDTO();
			dto.setAccountDTO(account);
			dto.setTotalSpent(totalSpent);

			topSpendersDTO.add(dto);
		}
		return topSpendersDTO;
	}

	@Override
	public Page<OrderDTO> findOrdersByAccount(AccountDTO accountDTO, Pageable pageable) {

		Page<OrderEntity> pageOrderEntity = orderRepository.findByAccountEntity(accountMapper.mapToEntity(accountDTO),
				pageable);
		Page<OrderDTO> pageOrderDTO = pageOrderEntity.map(orderMapper::mapToDTO);
		return pageOrderDTO;
	}

	@Override
	public OrderDTO findByIdAndUserId(long orderId, long userId) {
		OrderEntity order = orderRepository.findOne(orderId);
		if (order == null || order.getAccountEntity().getId() != userId) {

			throw new ResourceNotFoundException("You do not have permission to view this order.");
		}
		return orderMapper.mapToDTO(order);
	}

	@Override
	public List<MonthlyTotalDTO> getTotalPaymentsByMonthForYear(int year) {
		List<Object[]> results = orderRepository.findTotalPaymentsByMonthForYear(year);

		return results.stream()
				.map(result -> new MonthlyTotalDTO((Integer) result[0], ((Number) result[1]).doubleValue()))
				.collect(Collectors.toList());
		
		
	}

}
