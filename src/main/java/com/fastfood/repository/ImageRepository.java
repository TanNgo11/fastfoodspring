package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity, Long>{

}
