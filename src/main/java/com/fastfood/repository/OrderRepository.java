package com.fastfood.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	@Query("SELECT o FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year " + "AND o.status = :status")
	List<OrderEntity> findAllOrdersByMonthAndYearAndStatus(@Param("month") int month, @Param("year") int year,
			@Param("status") int status, Pageable pageable);

	@Query("SELECT o FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year")
	List<OrderEntity> findAllOrdersByMonthAndYear(@Param("month") int month, @Param("year") int year);

	@Query("SELECT o FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year")
	List<OrderEntity> findAllOrdersByMonthAndYear(@Param("month") int month, @Param("year") int year,
			Pageable pageable);

	@Query("SELECT COUNT(o) FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year")
	Long countAllOrdersByMonthAndYear(@Param("month") int month, @Param("year") int year);

	@Query("SELECT COUNT(o) FROM OrderEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year " + "AND o.status = :status")
	Long countAllOrdersByMonthAndYearAndStatus(@Param("month") int month, @Param("year") int year,
			@Param("status") int status);

	@Transactional
	@Modifying
	@Query("update OrderEntity o set o.status = :status where o.id = :orderId")
	int updateOrderStatus(@Param("orderId") long id, @Param("status") int status);

	@Query("SELECT SUM(o.totalPay) FROM OrderEntity o")
	BigDecimal findTotalSumOfAllOrders();

	@Query("SELECT SUM(o.totalPay) FROM OrderEntity o WHERE DATE(o.createdDate) = CURRENT_DATE")
	BigDecimal findTotalSumForCurrentDate();

	@Query("SELECT o.accountEntity AS user, SUM(o.totalPay) AS totalSpent FROM OrderEntity o "
			+ "GROUP BY o.accountEntity " + "ORDER BY totalSpent DESC")
	Page<Object[]> findTopBuyers(Pageable pageable);

	Page<OrderEntity> findByAccountEntity(AccountEntity accountEntity, Pageable pageable);

	@Query(value = "SELECT EXTRACT(MONTH FROM o.createddate) AS month, SUM(o.totalpay) AS total " + "FROM orders o "
			+ "WHERE EXTRACT(YEAR FROM o.createddate) = :year " + "GROUP BY EXTRACT(MONTH FROM o.createddate) "
			+ "ORDER BY month ASC", nativeQuery = true)
	List<Object[]> findTotalPaymentsByMonthForYear(@Param("year") int year);

}
