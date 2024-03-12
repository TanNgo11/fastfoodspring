package com.fastfood.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.OrderDTO;
import com.fastfood.service.IAccountService;
import com.fastfood.service.IOrderService;
import com.fastfood.utils.SecurityUtils;

@RestController(value = "orderAPIOfUser")
@RequestMapping("/api/v1/orders")
public class OrderAPI {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private IAccountService accountService;

	@GetMapping
	public ResponseEntity<?> getAllOrdersByUser(
			@RequestParam(name = "page", required = false, defaultValue = "1") int page,
			@RequestParam(name = "limit", required = false, defaultValue = "10") int limit) {
		Sort sort = new Sort(Sort.Direction.DESC, "createdDate");
		Pageable pageable = new PageRequest(page - 1, limit, sort);

		if (SecurityUtils.getPrincipal() == null) {
			ApiResponse response = new ApiResponse();
			response.setHttp(HttpStatus.UNAUTHORIZED);
			response.setMessage("User is not authenticated");
			response.setSuccess(false);
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}

		AccountDTO accountDTO = accountService.findById(SecurityUtils.getPrincipal().getId());

		Page<OrderDTO> listOrder = orderService.findOrdersByAccount(accountDTO, pageable);

		int totalItem = (int) listOrder.getTotalElements();

		OrderDTO result = new OrderDTO();
		result.setPage(page);
		result.setLimit(limit);

		result.setListResult(listOrder.getContent());
		result.setTotalItem(totalItem);
		result.setTotalPage(listOrder.getTotalPages());

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
