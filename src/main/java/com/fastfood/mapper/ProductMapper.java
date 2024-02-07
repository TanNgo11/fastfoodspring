package com.fastfood.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.ProductDTO;
import com.fastfood.entity.ImageEntity;
import com.fastfood.entity.ProductEntity;

@Component
public class ProductMapper {

	private final ModelMapper modelMapper;

	@Autowired
	private ImageMapper imageMapper;

	@Autowired
	public ProductMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		configureModelMapper();
//		modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
//				.addMapping(src -> src.getCategory(), ProductDTO::setCategoryDTO)
//				.addMapping(src -> src.getImageEntities(), ProductDTO::setListImage)
//				.addMapping(src -> src.getRelatedProducts(), ProductDTO::setRelatedProducts);
//

	}

	private void configureModelMapper() {
		Converter<List<ProductEntity>, List<ProductDTO>> relatedProductConverter = context -> {
			List<ProductEntity> source = context.getSource();
			if (source == null)
				return null;
			List<ProductDTO> dest = new ArrayList<>();
			for (ProductEntity product : source) {

				ProductDTO dto = new ProductDTO();
				dto.setId(product.getId());
				dto.setProductName(product.getProductName());
				dto.setListImage(imageMapper.mapListToDTO(product.getImageEntities()));
				dto.setPrice(product.getPrice());
				dto.setSalePrice(product.getSalePrice());
				dest.add(dto);
			}
			return dest;
		};

		modelMapper.createTypeMap(ProductEntity.class, ProductDTO.class)
				.addMappings(mapper -> mapper.using(relatedProductConverter).map(ProductEntity::getRelatedProducts,
						ProductDTO::setRelatedProducts))
				.addMapping(src -> src.getCategory(), ProductDTO::setCategoryDTO)
				.addMapping(src -> src.getImageEntities(), ProductDTO::setListImage);
		
		modelMapper.createTypeMap(ProductDTO.class, ProductEntity.class)
		.addMapping(scr -> scr.getCategoryDTO(), ProductEntity::setCategory)
		.addMapping(scr -> scr.getListImage(), ProductEntity::setImageEntities);
		

	}

	public ProductDTO mapToDTO(ProductEntity productEntity) {
		return modelMapper.map(productEntity, ProductDTO.class);
	}

	public ProductEntity mapToEntity(ProductDTO productDTO) {
		ProductEntity result = modelMapper.map(productDTO, ProductEntity.class);

		if (result.getImageEntities() != null) {
			List<ImageEntity> listImage = result.getImageEntities();
			for (ImageEntity imageEntity : listImage) {
				imageEntity.setProductEntity(result);
			}
		}

		return result;
	}

}
