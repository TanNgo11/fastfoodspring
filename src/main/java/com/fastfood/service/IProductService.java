package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.ProductDTO;
import com.fastfood.dto.ProductSalesDTO;

public interface IProductService {
	List<ProductDTO> findAll(Pageable pageable);

	Page<ProductDTO> findAllByStatus(Pageable pageable, int status);
	
	List<ProductDTO> findAllByStatus(int status);

	List<ProductDTO> findByCategory_idAndStatus(long category_id, Pageable pageable, int status);

	List<ProductDTO> findByCategory_idAndStatus(long category_id, int status);
	
	ProductDTO findBySlug(String slug);

	ProductDTO findById(long id);

	int countByCategoryId(long categoryId);

	ApiResponse softDelete(long productId);

	ProductDTO save(ProductDTO dto);

	int getTotalItem();

	ProductDTO update(ProductDTO dto);
	
	List<ProductDTO> findProductBySearchKeys(String searchQuery);
	
	void decreaseStock(long productId, int newStock);
	
	List<ProductSalesDTO> getTop10ProductsBySales();
	
	List<ProductDTO> getTop4ProductsBySales(Pageable pageable);
	
	Page<ProductDTO> findDraftAndScheduledProducts(int page, int size);


}
