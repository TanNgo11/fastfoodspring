package com.fastfood.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "payment_detail")
public class PaymentDetail extends BaseEntity {

	@OneToOne(mappedBy = "paymentDetail")
	private OrderEntity orderEntity;

	@Column
	private String totalPrice;
	
	@Column
	private String provider;
	
	@Column
	private String paymentTime;
	
	@Column
	private String transactionId;

	@Column
	private String orderInfo;

}
