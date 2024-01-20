package com.fastfood.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.repository.ImageRepository;
import com.fastfood.service.IImageService;

@Service
public class ImageService implements IImageService {
	@Autowired
	private ImageRepository imageReopisotry;

	@Override
	public void deleteById(Long id) {
		imageReopisotry.delete(id);

	}

}
