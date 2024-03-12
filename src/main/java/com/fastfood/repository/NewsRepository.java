package com.fastfood.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.entity.NewsEntity;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
	NewsEntity findBySlug(String slug);

	List<NewsEntity> findTop2ByStatusOrderByCreatedDateDesc(int status);

	@Transactional
	@Modifying
	@Query("UPDATE NewsEntity p SET p.status = :status, p.publishDate = :publishDate WHERE p.id = :id")
	void updateNewsStatusAndPublishDate(@Param("id") Long id, @Param("status") int status,
			@Param("publishDate") Date publishDate);

	List<NewsEntity> findByPublishDateLessThanEqualAndStatus(Date publishDate, int status);
	
	Page<NewsEntity> findByStatusIn(List<Integer> statuses, Pageable pageable);

}
