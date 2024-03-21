package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.NewsDTO;
import com.fastfood.dto.ProductDTO;

public interface INewsService {
	List<NewsDTO> findAllByPage(Pageable pageable);

	int getTotalNews();

//	NewsDTO findByAuthor(AccountDTO accountDTO); 
	NewsDTO save(NewsDTO newsDTO);

	NewsDTO findByID(Long id);

	List<NewsDTO> findTop2ByOrderByCreatedDateDesc();

	NewsDTO findBySlug(String slug);

	ApiResponse softDeleteNews(Long id);

	ApiResponse activeNews(Long id);
	
	Page<NewsDTO> findDraftAndScheduledNews(int page, int size);
	
	Page<NewsDTO> findActiveNews(int page, int size);
	
	Page<NewsDTO> findActiveNews(Pageable pageable);

}
