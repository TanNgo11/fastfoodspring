package com.fastfood.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findByStatus(Pageable pageable, int status);

	List<ProductEntity> findByStatus(int status);

	List<ProductEntity> findByCategory_idAndStatus(long category_id, Pageable pageable, int status);

	int countByCategory_id(long category_id);

	List<ProductEntity> findByCategory_idAndStatus(long category_id, int status);

	Optional<ProductEntity> findByIdAndStatus(long id, int status);

	ProductEntity findBySlug(String slug);

	@Query("SELECT p FROM ProductEntity p WHERE " + "LOWER(p.productName) LIKE LOWER(CONCAT('%',:keyword,'%'))")
	List<ProductEntity> findByProductNameOrDescriptionContainingIgnoreCase(@Param("keyword") String keyword);

	@Modifying
	@Transactional
	@Query("UPDATE ProductEntity p SET p.inStock = p.inStock - :amount WHERE p.id = :productId")
	int decreaseStock(@Param("productId") Long productId, @Param("amount") int amount);

	@Query("SELECT i.productEntity AS product, SUM(i.quantity) AS totalQuantity " + "FROM ItemEntity i "
			+ "GROUP BY i.productEntity " + "ORDER BY totalQuantity DESC")
	List<Object[]> findTop10ProductsByQuantity(Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO product_relations (product_id, related_product_id) VALUES (?1, ?2)", nativeQuery = true)
	void insertProductRelation(Long productId, Long relatedProductId);

	@Transactional
	@Modifying
	@Query("UPDATE ProductEntity p SET p.status = :status, p.publishDate = :publishDate WHERE p.id = :id")
	void updateProductStatusAndPublishDate(@Param("id") Long id, @Param("status") int status,
			@Param("publishDate") Date publishDate);

	List<ProductEntity> findByPublishDateLessThanEqualAndStatus(Date publishDate, int status);

	Page<ProductEntity> findByStatusIn(List<Integer> statuses, Pageable pageable);
}
