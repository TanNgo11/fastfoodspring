package com.fastfood.api.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;
import com.fastfood.service.impl.ProductService;

@RestController(value = "productAPIOfAdmin")
@RequestMapping("admin/api/v1/products")
public class ProductAPI {

	@Autowired
	IProductService productService;

	@GetMapping
	public ResponseEntity<ProductDTO> getAllProducts(@RequestParam("page") int page, @RequestParam("limit") int limit) {

		ProductDTO result = new ProductDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(productService.findAll(pageable));
		result.setTotalItem(productService.getTotalItem());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
		result.splitImg();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponse> deleteProductById(@PathVariable("productId") long productId) {

		ApiResponse response = productService.delete(productId);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
