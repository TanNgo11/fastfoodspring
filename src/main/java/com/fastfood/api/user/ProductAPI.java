package com.fastfood.api.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ProductDTO;
import com.fastfood.service.IProductService;

@RestController(value = "productAPIofUser")
@RequestMapping("/api/v1/")
public class ProductAPI {
	@Autowired
	private IProductService productService;

	@GetMapping("/products")
	public ProductDTO loadMoreProduct(@RequestParam(name = "page") int page,
			@RequestParam(name = "typeOfProduct") String typeOfProduct) {
		ProductDTO result = new ProductDTO();
		Pageable pageable = new PageRequest(page, 4);
	
		int category_id = 1;
		List<ProductDTO> listResult = productService.findByCategory_idAndStatus(category_id, pageable, SystemConstant.ACTIVE_STATUS);
		listResult.forEach(t->t.setTypeOfProduct("food"));
		if (typeOfProduct.equals("drink")) {
			category_id = 2;
			listResult = productService.findByCategory_idAndStatus(category_id, pageable, SystemConstant.ACTIVE_STATUS);
			listResult.forEach(t->t.setTypeOfProduct("drink"));
		} else if(typeOfProduct.equals("topSales")) {
			listResult = productService.getTop4ProductsBySales(pageable);
			listResult= listResult.stream().filter(s->s.getStatus()==SystemConstant.ACTIVE_STATUS).collect(Collectors.toList());
			listResult.forEach(t->t.setTypeOfProduct("topSales"));
		}

		result.setListResult(listResult);

		return result;
	}

	@GetMapping("/products/category/{typeOfProduct}")
	public ProductDTO loadAllProductByCategory(@PathVariable String typeOfProduct) {

		int category_id = 1;
		if (typeOfProduct.equals("drink")) {
			category_id = 2;
		}

		ProductDTO result = new ProductDTO();

		result.setListResult(productService.findByCategory_idAndStatus(category_id, SystemConstant.ACTIVE_STATUS));

		return result;
	}

	@GetMapping("/products/search")
	public List<ProductDTO> searchProductByKey(@RequestParam String q) {
		List<ProductDTO> result = productService.findProductBySearchKeys(q);

		return result;
	}

}
