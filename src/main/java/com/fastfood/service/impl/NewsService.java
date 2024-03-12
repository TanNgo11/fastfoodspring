package com.fastfood.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.NewsDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.NewsEntity;
import com.fastfood.entity.ProductEntity;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mapper.NewsMapper;
import com.fastfood.repository.NewsRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.INewsService;
import com.fastfood.utils.MessageUtil;
import com.fastfood.utils.SecurityUtils;

@Service
public class NewsService implements INewsService {
	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private NewsMapper newsMapper;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<NewsDTO> findAllByPage(Pageable pageable) {

		Page<NewsEntity> news = newsRepository.findAll(pageable);

		List<NewsDTO> result = new ArrayList<>();
		for (NewsEntity newsEntity : news.getContent()) {
			NewsDTO dto = newsMapper.mapToDTO(newsEntity);
			dto.setDateFormat(formatDate(newsEntity.getCreatedDate()));
			result.add(dto);
		}
		return result;
	}

//	@Override
//	public NewsDTO findByAuthor(AccountDTO accountDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public NewsDTO save(NewsDTO newsDTO) {
		NewsEntity newsEntity = newsMapper.mapToEntity(newsDTO);

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		newsEntity.setAccountEntity(accountEntity);
		newsEntity.setSlug(createSlug(newsEntity.getId(), newsEntity.getTitle()));
		return newsMapper.mapToDTO(newsRepository.save(newsEntity));
	}

	@Override
	public ApiResponse softDeleteNews(Long id) {
		NewsEntity newsEntity = newsRepository.findOne(id);

		if (newsEntity != null) {
			NewsDTO dto = newsMapper.mapToDTO(newsEntity);
			dto.setStatus(SystemConstant.INACTIVE_STATUS);
			newsRepository.save(newsMapper.mapToEntity(dto));
			return new ApiResponse().builder().success(true).http(HttpStatus.OK).message("Disable news successed")
					.build();
		}
		return new ApiResponse().builder().success(false).http(HttpStatus.OK).message("Disable news failed").build();

	}

	@Override
	public ApiResponse activeNews(Long id) {
		NewsEntity newsEntity = newsRepository.findOne(id);

		if (newsEntity != null) {
			NewsDTO dto = newsMapper.mapToDTO(newsEntity);
			dto.setStatus(SystemConstant.ACTIVE_STATUS);
			newsRepository.save(newsMapper.mapToEntity(dto));
			return new ApiResponse().builder().success(true).http(HttpStatus.OK).message("Active news successed")
					.build();
		}
		return new ApiResponse().builder().success(false).http(HttpStatus.OK).message("Active news failed").build();

	}

	@Override
	public int getTotalNews() {

		return (int) newsRepository.count();
	}

	private String createSlug(long id, String title) {

		String lowercaseString = title.toLowerCase();

		String stringWithHyphens = lowercaseString.replace(" ", "-");

		String slug = stringWithHyphens + "-" + id;

		return slug;
	}

	@Override
	public NewsDTO findByID(Long id) {
		NewsEntity newsEntity = newsRepository.findOne(id);
		if (newsEntity == null)
			throw new ResourceNotFoundException(MessageUtil.NEWS_ID_NOT_FOUND + id);

		return newsMapper.mapToDTO(newsEntity);
	}

	@Override
	public NewsDTO findBySlug(String slug) {
		return newsMapper.mapToDTO(newsRepository.findBySlug(slug));

	}

	@Override
	public List<NewsDTO> findTop2ByOrderByCreatedDateDesc() {
		List<NewsEntity> listEntity = newsRepository.findTop2ByStatusOrderByCreatedDateDesc(SystemConstant.PUBLIC);
		List<NewsDTO> result = new ArrayList<>();

		for (NewsEntity item : listEntity) {
			
			NewsDTO dto = newsMapper.mapToDTO(item);
			dto.setDateFormat(formatDate(item.getCreatedDate()));
			result.add(dto);
		}

		return result;
	}

	private static String formatDate(Date inputDate)  {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDateString = inputFormat.format(inputDate);
		

		return formattedDateString;
	}

	@Override
	public Page<NewsDTO> findDraftAndScheduledNews(int page, int size) {
		Pageable pageable = new PageRequest(page - 1, size);
		List<Integer> statuses = Arrays.asList(SystemConstant.DRAFT, SystemConstant.SCHEDULED);

		Page<NewsEntity> newsEntities = newsRepository.findByStatusIn(statuses, pageable);
		Page<NewsDTO> newsDTOs = newsEntities.map(t -> newsMapper.mapToDTO(t));

		return newsDTOs;
	}

}
