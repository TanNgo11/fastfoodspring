package com.fastfood.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long>{
	NewsEntity findBySlug(String slug);
	List<NewsEntity> findTop2ByOrderByCreatedDateDesc();

}
