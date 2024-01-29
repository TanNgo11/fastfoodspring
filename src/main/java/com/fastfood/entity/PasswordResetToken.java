package com.fastfood.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "passwordresettoken")
public class PasswordResetToken extends BaseEntity {

	private String token;
	
	private LocalDateTime expiryDateTime;
	
	@ManyToOne()
	@JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
	private AccountEntity accountEntity;

}
