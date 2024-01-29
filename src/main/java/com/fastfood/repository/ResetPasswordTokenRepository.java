package com.fastfood.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.PasswordResetToken;



public interface ResetPasswordTokenRepository extends JpaRepository<PasswordResetToken, Long>{

	PasswordResetToken findByToken(String token);

}