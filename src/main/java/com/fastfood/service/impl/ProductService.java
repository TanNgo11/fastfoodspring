package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;
import com.fastfood.dto.ProductSalesDTO;
import com.fastfood.entity.ProductEntity;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mapper.ProductMapper;
import com.fastfood.repository.ProductRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IProductService;
import com.fastfood.utils.MessageUtil;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ProductMapper productMapper;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<ProductDTO> findAll(Pageable pageable) {

		List<ProductEntity> entities = productRepository.findAll(pageable).getContent();
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(product -> productMapper.mapToDTO(product)).collect(Collectors.toList());

	}

	@Override
	public int getTotalItem() {
		return (int) productRepository.count();
	}

	@Override
	public List<ProductDTO> findByCategory_idAndStatus(long category_id, Pageable pageable, int status) {
		List<ProductDTO> models = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findByCategory_idAndStatus(category_id, pageable, status);

		for (ProductEntity item : entities) {
			ProductDTO product = productMapper.mapToDTO(item);

			models.add(product);
		}
		return models;
	}

	@Override
	public ProductDTO findById(long id) {
		ProductEntity product = productRepository.getOne(id);

		if (product == null)
			throw new ResourceNotFoundException(MessageUtil.PRODUCT_ID_NOT_FOUND + id);
		ProductDTO productDTO = productMapper.mapToDTO(product);
		return productDTO;

	}

	@Override
	public int countByCategoryId(long categoryId) {

		return productRepository.countByCategory_id(categoryId);
	}

	@Override
	public ApiResponse softDelete(long productId) {

		ProductEntity entity = productRepository.findByIdAndStatus(productId, SystemConstant.ACTIVE_STATUS)
				.orElseThrow(() -> new ResourceNotFoundException(MessageUtil.PRODUCT_ID_NOT_FOUND + productId));

		if (entity != null) {
			entity.setStatus(SystemConstant.INACTIVE_STATUS);
			productRepository.save(entity);
			return new ApiResponse(Boolean.TRUE, MessageUtil.SUCCESS_DELETE, HttpStatus.OK);
		}

		return new ApiResponse(Boolean.FALSE, MessageUtil.DELETE_FAILURE, HttpStatus.OK);

	}

	@Override
	public Page<ProductDTO> findAllByStatus(Pageable pageable, int status) {
		Page<ProductEntity> entityPage = productRepository.findByStatus(pageable, status);

		if (entityPage.getTotalElements() == 0) {
			throw new ResourceNotFoundException(
					"Cannot find any products with the current status on the requested page.");
		}

		return entityPage.map(productMapper::mapToDTO);
	}

	@Override
	@Transactional
	public ProductDTO save(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();

		entity = productMapper.mapToEntity(dto);

		entity = productRepository.save(entity);

		entity.setSlug(createSlug(entity.getId(), entity.getProductName()));

		return productMapper.mapToDTO(productRepository.save(entity));
	}

	@Override
	public ProductDTO update(ProductDTO dto) {

		return null;
	}

	@Override
	public List<ProductDTO> findByCategory_idAndStatus(long category_id, int status) {
		List<ProductDTO> models = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findByCategory_idAndStatus(category_id, status);

		for (ProductEntity item : entities) {
			ProductDTO product = productMapper.mapToDTO(item);

			models.add(product);
		}
		return models;
	}

	@Override
	public ProductDTO findBySlug(String slug) {

		return productMapper.mapToDTO(productRepository.findBySlug(slug));
	}

	private String createSlug(long id, String title) {

		String lowercaseString = title.toLowerCase();

		String stringWithHyphens = lowercaseString.replace(" ", "-");

		String slug = stringWithHyphens + "-" + id;

		return slug;
	}

	@Override
	public List<ProductDTO> findProductBySearchKeys(String searchQuery) {
		List<ProductDTO> result = new ArrayList<>();

		List<ProductEntity> listEntities = productRepository
				.findByProductNameOrDescriptionContainingIgnoreCase(searchQuery);

		for (ProductEntity productEntity : listEntities) {
			result.add(productMapper.mapToDTO(productEntity));
		}
		return result;
	}

	@Override
	public void decreaseStock(long productId, int amount) {
		int updatedRows = productRepository.decreaseStock(productId, amount);
		if (updatedRows == 0) {
			throw new ResourceNotFoundException(MessageUtil.PRODUCT_ID_NOT_FOUND + productId);
		}
	}

	@Override
	public List<ProductSalesDTO> getTop10ProductsBySales() {
		Pageable pageable = new PageRequest(0, 10);
		List<Object[]> results = productRepository.findTop10ProductsByQuantity(pageable);

		return results.stream().map(result -> {
			ProductEntity product = (ProductEntity) result[0];
			Long totalQuantity = (Long) result[1];

			return new ProductSalesDTO(product.getId(), product.getProductName(), totalQuantity.doubleValue());
		}).collect(Collectors.toList());
	}

	@Override
	public List<ProductDTO> getTop4ProductsBySales(Pageable pageable) {

		List<Object[]> results = productRepository.findTop10ProductsByQuantity(pageable);

		return results.stream().map(result -> {
			ProductEntity product = (ProductEntity) result[0];
			ProductDTO dto = productMapper.mapToDTO(product);

			return dto;
		}).collect(Collectors.toList());

	}

	@Override
	public List<ProductDTO> findAllByStatus(int status) {
		List<ProductEntity> entities = productRepository.findByStatus(status);
		if (entities.isEmpty()) {
			throw new ResourceNotFoundException("Can not found current page");
		}
		return entities.stream().map(product -> productMapper.mapToDTO(product)).collect(Collectors.toList());
	}

	@Override
	public Page<ProductDTO> findDraftAndScheduledProducts(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		List<Integer> statuses = Arrays.asList(SystemConstant.DRAFT, SystemConstant.SCHEDULED);

		Page<ProductEntity> productEntities = productRepository.findByStatusIn(statuses, pageable);
		Page<ProductDTO> productDTOs = productEntities.map(t -> productMapper.mapToDTO(t));

		return productDTOs;
	}

}
