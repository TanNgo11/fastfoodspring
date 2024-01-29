package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.ApiResponse;
import com.fastfood.dto.CategoryDTO;
import com.fastfood.entity.CategoryEntity;
import com.fastfood.exception.ResourceNotFoundException;
import com.fastfood.mapper.CategoryMapper;
import com.fastfood.repository.CategoryRepository;
import com.fastfood.service.ICategoryService;
import com.fastfood.utils.MessageUtil;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> findAll() {
		return categoryRepository.findAll().stream().map(categoryMapper::mapToDTO).collect(Collectors.toList());
	}

	@Override
	public CategoryDTO findByType(String type) {
		CategoryEntity categoryEntity = categoryRepository.findByType(type);
		CategoryDTO dto = categoryMapper.mapToDTO(categoryEntity);
		return dto;
	}

	@Override
	public List<CategoryDTO> findAllbyPage(Pageable pageable) {
		Page<CategoryEntity> cate = categoryRepository.findAll(pageable);

		List<CategoryDTO> categories = new ArrayList<>();
		for (CategoryEntity categoryEntity : cate.getContent()) {
			categories.add(categoryMapper.mapToDTO(categoryEntity));
		}

		return categories;
	}

	@Override
	public ApiResponse softDeleteCategory(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findOne(id);

		if (categoryEntity != null) {
			CategoryDTO dto = categoryMapper.mapToDTO(categoryEntity);
			dto.setStatus(SystemConstant.INACTIVE_STATUS);
			categoryRepository.save(categoryMapper.mapToEntity(dto));
			return new ApiResponse().builder().success(true).http(HttpStatus.OK).message("Disable category success")
					.build();
		}
		return new ApiResponse().builder().success(false).http(HttpStatus.ALREADY_REPORTED)
				.message("Disable category failed!").build();

	}

	@Override
	public ApiResponse activeCategory(Long id) {
		CategoryEntity categoryEntity = categoryRepository.findOne(id);

		if (categoryEntity != null) {
			CategoryDTO dto = categoryMapper.mapToDTO(categoryEntity);
			dto.setStatus(SystemConstant.ACTIVE_STATUS);
			categoryRepository.save(categoryMapper.mapToEntity(dto));
			return new ApiResponse().builder().success(true).http(HttpStatus.OK).message("Active category success")
					.build();
		}
		return new ApiResponse().builder().success(false).http(HttpStatus.ALREADY_REPORTED)
				.message("Active category failed!").build();
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		CategoryEntity entity = categoryMapper.mapToEntity(categoryDTO);
		return categoryMapper.mapToDTO(categoryRepository.save(entity));
	}

	@Override
	public int getTotalCate() {
		return (int) categoryRepository.count();
	}

	@Override
	public CategoryDTO findByID(Long id) {
		if(categoryRepository.findOne(id)==null)
			throw new ResourceNotFoundException(MessageUtil.CATEGORY_ID_NOT_FOUND+id);
		return categoryMapper.mapToDTO(categoryRepository.findOne(id));
	}

}
