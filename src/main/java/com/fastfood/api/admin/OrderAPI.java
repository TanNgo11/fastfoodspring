package com.fastfood.api.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IOrderService;
import com.fastfood.service.IProductService;
import com.fastfood.utils.OrderExcelExporter;

@RestController(value = "BillAPIOfAdmin")
@RequestMapping("admin/api/v1/orders")
public class OrderAPI {
	
	@Autowired
	IOrderService orderService;

	
	@GetMapping
	public ResponseEntity<OrderDTO> getAllOrders(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		OrderDTO result = new OrderDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(orderService.findAll(pageable));
		result.setTotalItem(orderService.getTotalOrder());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
		

		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=orders_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		Pageable pageable = new PageRequest(0, 10);
		List<OrderDTO> listUsers = orderService.findAll(pageable);

		OrderExcelExporter excelExporter = new OrderExcelExporter(listUsers);
		

		excelExporter.export(response);
	}
	
}
