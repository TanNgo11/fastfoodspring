package com.fastfood.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageDTO extends AbstractDTO<ImageDTO>{
	
	private String imageURL;
	
	private ProductDTO productDTO;

}
