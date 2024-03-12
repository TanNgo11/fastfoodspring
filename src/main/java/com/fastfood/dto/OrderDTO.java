package com.fastfood.dto;

import java.util.ArrayList;
import java.util.List;

import com.fastfood.entity.OrderType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO extends AbstractDTO<OrderDTO> {

	private String customerName;
	private String email;
	private String phonenumber;
	private String address;
	private Double totalPay;
	private OrderType orderType;
	private String statusString;
	private int status;
	private List<ItemDTO> items = new ArrayList<ItemDTO>();
	private AccountDTO accountDTO;
	
	

}
