package com.fastfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fastfood.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findByType(String type);

	@Query("SELECT c.type, SUM(i.price * i.quantity) FROM OrderEntity o JOIN o.items i JOIN i.productEntity p JOIN p.category c GROUP BY c.type")
	List<Object[]> findRevenueByCategory();

	

}
