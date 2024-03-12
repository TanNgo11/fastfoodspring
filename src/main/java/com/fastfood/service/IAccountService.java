package com.fastfood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fastfood.dto.AccountDTO;

public interface IAccountService {
	AccountDTO save(AccountDTO dto);

	AccountDTO findByUsername(String userName);

	AccountDTO findByOauth2Id(String oauth2Id);

	AccountDTO findById(long id);

	List<AccountDTO> findByPage(Pageable pageable);

	AccountDTO update(AccountDTO dto);

	int getTotalAccount();

	Page<AccountDTO> findAllByRole(String roleName, Pageable pageable);

	int getTotalAccountByRole(String roleName);
	
	Optional<AccountDTO> findByIdAndRoleName(long accountId, String roleName);
	
	AccountDTO saveAccountStaffOrAdmin(AccountDTO dto);

	
}
