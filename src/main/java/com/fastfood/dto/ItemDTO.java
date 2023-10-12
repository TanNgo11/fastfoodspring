package com.fastfood.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fastfood.entity.OrderEntity;
import com.fastfood.entity.ProductEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO extends AbstractDTO<ItemDTO> {
	private ProductDTO productDTO;
	private double price;
	private int quantity;
	private double totalPay;
    @JsonIgnore
	private List<OrderDTO> orders;

	
	public void splitImg() {
		this.productDTO.setImg(this.productDTO.getImg().split("_")[0]);

	
		
		
	}

	
}
