package com.fastfood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastfood.entity.AccountEntity;


public interface UserRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findOneByUserNameAndStatus(String name, int status);
}
