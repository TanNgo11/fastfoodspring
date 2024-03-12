package com.fastfood.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ImageDTO;
import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ImageEntity;
import com.fastfood.entity.ProductEntity;

@Component
public class ProductMapper {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	private CategoryMapper categoryMapper;

//	public ProductMapper() {
//	
//		configureModelMapper();
////		modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
////				.addMapping(src -> src.getCategory(), ProductDTO::setCategoryDTO)
////				.addMapping(src -> src.getImageEntities(), ProductDTO::setListImage)
////				.addMapping(src -> src.getRelatedProducts(), ProductDTO::setRelatedProducts);
////
//
//	}

//	private void configureModelMapper() {
//		Converter<List<ProductEntity>, List<ProductDTO>> relatedProductConverter = context -> {
//			List<ProductEntity> source = context.getSource();
//			if (source == null)
//				return null;
//			List<ProductDTO> dest = new ArrayList<>();
//			for (ProductEntity product : source) {
//
//				ProductDTO dto = new ProductDTO();
//				dto.setId(product.getId());
//				dto.setProductName(product.getProductName());
//				dto.setListImage(imageMapper.mapListToDTO(product.getImageEntities()));
//				dto.setPrice(product.getPrice());
//				dto.setSalePrice(product.getSalePrice());
//				dest.add(dto);
//			}
//			return dest;
//		};

//		modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
//				.addMappings(mapper -> mapper.using(relatedProductConverter).map(ProductEntity::getRelatedProducts,
//						ProductDTO::setRelatedProducts))
//				.addMapping(src -> src.getCategory(), ProductDTO::setCategoryDTO)
//				.addMapping(src -> src.getImageEntities(), ProductDTO::setListImage);
//		
//		modelMapper.createTypeMap(ProductDTO.class, ProductEntity.class)
//		.addMapping(scr -> scr.getCategoryDTO(), ProductEntity::setCategory)
//		.addMapping(scr -> scr.getListImage(), ProductEntity::setImageEntities);
//		
//
//	}

	public ProductDTO mapToDTO(ProductEntity productEntity) {
		if (productEntity == null) {
			return null;
		}

		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(productEntity.getId());
		productDTO.setPrice(productEntity.getPrice());
		productDTO.setSalePrice(productEntity.getSalePrice());
		productDTO.setProductName(productEntity.getProductName());
		productDTO.setDescription(productEntity.getDescription());
		productDTO.setStatus(productEntity.getStatus());
		productDTO.setSlug(productEntity.getSlug());
		productDTO.setInStock(productEntity.getInStock());
		productDTO.setPublishDate(productEntity.getPublishDate());

		// Manually map images (without setting product back-reference)
		if (productEntity.getImageEntities() != null) {
			productDTO.setListImage(productEntity.getImageEntities().stream().map(imageEntity -> {
				ImageDTO imageDTO = new ImageDTO();
				imageDTO.setId(imageEntity.getId());
				imageDTO.setImageURL(imageEntity.getImageURL());

				return imageDTO;
			}).collect(Collectors.toList()));
		}

		productDTO.setCategoryDTO(categoryMapper.mapToDTO(productEntity.getCategory()));

		// Manually handle related products to avoid recursion.
		// You can map the necessary fields only to minimize the data load.
		productDTO.setRelatedProducts(productEntity.getRelatedProducts().stream().map(relatedProduct -> {
			ProductDTO dto = new ProductDTO();
			dto.setId(relatedProduct.getId());
			dto.setProductName(relatedProduct.getProductName());
			dto.setPrice(relatedProduct.getPrice());
			dto.setSalePrice(relatedProduct.getSalePrice());
			dto.setCategoryDTO(categoryMapper.mapToDTO(relatedProduct.getCategory()));
			dto.setDescription(relatedProduct.getDescription());
			dto.setStatus(relatedProduct.getStatus());
			dto.setSlug(relatedProduct.getSlug());
			dto.setInStock(relatedProduct.getInStock());
			dto.setPublishDate(relatedProduct.getPublishDate());
			
			if (relatedProduct.getImageEntities() != null) {
				dto.setListImage(relatedProduct.getImageEntities().stream().map(imageEntity -> {
					ImageDTO imageDTO = new ImageDTO();
					imageDTO.setId(imageEntity.getId());
					imageDTO.setImageURL(imageEntity.getImageURL());

					return imageDTO;
				}).collect(Collectors.toList()));
			}
			
			return dto;
		}).collect(Collectors.toList()));

		return productDTO;
	}

	public ProductEntity mapToEntity(ProductDTO productDTO) {
		if (productDTO == null) {
			return null;
		}

		ProductEntity productEntity = new ProductEntity();
		if (productDTO.getId() != null) {
			productEntity.setId(productDTO.getId());
		}
		productEntity.setPrice(productDTO.getPrice());
		productEntity.setSalePrice(productDTO.getSalePrice());
		productEntity.setProductName(productDTO.getProductName());
		productEntity.setDescription(productDTO.getDescription());
		productEntity.setStatus(productDTO.getStatus());
		productEntity.setSlug(productDTO.getSlug());
		productEntity.setInStock(productDTO.getInStock());
		productEntity.setPublishDate(productDTO.getPublishDate());

		// Manually map images (without setting product back-reference)
		if (productDTO.getListImage() != null) {
			productEntity.setImageEntities(productDTO.getListImage().stream().map(image -> {
				ImageEntity imageEntity = new ImageEntity();
				if (image.getId() != null)
					imageEntity.setId(image.getId());
				imageEntity.setImageURL(image.getImageURL());
				imageEntity.setProductEntity(productEntity);

				return imageEntity;
			}).collect(Collectors.toList()));
		}

		productEntity.setCategory(categoryMapper.mapToEntity(productDTO.getCategoryDTO()));

		// Manually handle related products to avoid recursion.
		// You can map the necessary fields only to minimize the data load.
		productEntity.setRelatedProducts(productDTO.getRelatedProducts().stream().map(relatedProduct -> {
			ProductEntity entity = new ProductEntity();
			entity.setId(relatedProduct.getId());
			entity.setProductName(relatedProduct.getProductName());
			entity.setPrice(relatedProduct.getPrice());
			entity.setSalePrice(relatedProduct.getSalePrice());
			entity.setCategory(categoryMapper.mapToEntity(relatedProduct.getCategoryDTO()));
			entity.setDescription(relatedProduct.getDescription());
			entity.setStatus(relatedProduct.getStatus());
			entity.setSlug(relatedProduct.getSlug());
			entity.setInStock(relatedProduct.getInStock());
			entity.setPublishDate(relatedProduct.getPublishDate());
			return entity;
		}).collect(Collectors.toList()));

		return productEntity;

	}

}
