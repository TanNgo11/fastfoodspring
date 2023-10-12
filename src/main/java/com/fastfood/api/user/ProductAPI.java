package com.fastfood.api.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.ItemDTO;
import com.fastfood.dto.OrderDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;

@RestController(value = "productAPIofUser")
public class ProductAPI {
	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/api/product", method = RequestMethod.GET)
	public ProductDTO loadMoreProduct(@RequestParam(name = "page") int page,
			@RequestParam(name = "typeOfProduct") String typeOfProduct) {

		int category_id = 1;
		if (typeOfProduct.equals("drink")) {
			category_id = 2;
		}

		ProductDTO result = new ProductDTO();
		Pageable pageable = new PageRequest(page, 4);
		result.setListResult(productService.findByCategory_id(category_id, pageable));

		return result;
	}

	

}
