package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll(Pageable pageable);
	List<ProductDTO> findByCategory_id(long category_id, Pageable pageable);
	ProductDTO findById(long id);
	int countByCategoryId(long categoryId);
	ApiResponse delete(long productId);
	int getTotalItem();
	
}
