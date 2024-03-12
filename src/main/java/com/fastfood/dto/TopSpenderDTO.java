package com.fastfood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TopSpenderDTO {
	private AccountDTO accountDTO;
	private Double totalSpent;
}
