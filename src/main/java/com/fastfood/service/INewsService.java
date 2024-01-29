package com.fastfood.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.NewsDTO;

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

}
