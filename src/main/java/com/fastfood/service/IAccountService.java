package com.fastfood.service;

import com.fastfood.dto.AccountDTO;

public interface IAccountService {
	AccountDTO save(AccountDTO dto);

	AccountDTO findByUsername(String userName);
	
	AccountDTO findByOauth2Id(String oauth2Id);

	AccountDTO findById(long id);


	AccountDTO update(AccountDTO dto);

}
