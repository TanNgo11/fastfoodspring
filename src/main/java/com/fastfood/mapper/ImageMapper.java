package com.fastfood.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ImageDTO;
import com.fastfood.entity.ImageEntity;

@Component
public class ImageMapper {
	@Autowired
	private ModelMapper modelMapper;

	public ImageDTO mapToDTO(ImageEntity imageEntity) {
		return modelMapper.map(imageEntity, ImageDTO.class);
	}

	public ImageEntity mapToEntity(ImageDTO imageDTO) {
		return modelMapper.map(imageDTO, ImageEntity.class);
	}
	
	public List<ImageDTO> mapListToDTO(List<ImageEntity> imageEntities) {
	   
	    List<ImageDTO> imageDTOs = new ArrayList<>();
	    for (ImageEntity imageEntity : imageEntities) {
	        ImageDTO dto = new ImageDTO();
	       
	        dto.setId(imageEntity.getId());
	        dto.setImageURL(imageEntity.getImageURL());
	     
	        imageDTOs.add(dto);
	    }
	    return imageDTOs;
	}

}
