package com.fastfood.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.AccountDTO;
import com.fastfood.service.IAccountService;
import com.fastfood.service.impl.AccountService;

@RestController(value = "userAPI")
public class UserAPI {
	@Autowired
	private IAccountService accountService;
	
	@PostMapping("/api/user")
	public AccountDTO createUser(@RequestBody AccountDTO accountDTO) {
		return accountService.save(accountDTO);
	}

}
