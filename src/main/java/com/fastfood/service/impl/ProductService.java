package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fastfood.converter.ProductConverter;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ProductEntity;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.repository.ProductRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IProductService;
import com.fastfood.utils.MessageUtil;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductConverter productConverter;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<ProductDTO> findAll(Pageable pageable) {

		List<ProductEntity> entities = productRepository.findAll(pageable).getContent();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(product -> productConverter.toDTO(product)).collect(Collectors.toList());

	}

	@Override
	public int getTotalItem() {
		return (int) productRepository.count();
	}

	@Override
	public List<ProductDTO> findByCategory_id(long category_id, Pageable pageable) {
		List<ProductDTO> models = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findByCategory_id(category_id, pageable);

		for (ProductEntity item : entities) {
			ProductDTO product = productConverter.toDTO(item);
			models.add(product);
		}
		return models;
	}

	@Override
	public ProductDTO findById(long id) {
		ProductDTO product = productConverter.toDTO(productRepository.findOne(id));
		return product;
	}

	@Override
	public int countByCategoryId(long categoryId) {

		return productRepository.countByCategory_id(categoryId);
	}

	@Override
	public ApiResponse delete(long productId) {
		if (productRepository.exists(productId)) {
			productRepository.delete(productId);
			return new ApiResponse(Boolean.TRUE, MessageUtil.DELETE_SUCCESS, HttpStatus.OK);
			
		}
		return new ApiResponse(Boolean.FALSE, MessageUtil.DELETE_FAILURE, HttpStatus.OK);

	}

}
