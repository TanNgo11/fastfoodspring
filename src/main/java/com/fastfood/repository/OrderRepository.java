package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
