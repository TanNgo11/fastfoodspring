package com.fastfood.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	

	List<ProductEntity> findByCategory_id(long category_id, Pageable pageable);
	int countByCategory_id(long category_id);
	
	

}
