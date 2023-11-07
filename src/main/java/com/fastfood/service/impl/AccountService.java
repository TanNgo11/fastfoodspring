package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.constant.SystemConstant;
import com.fastfood.converter.AccountConverter;
import com.fastfood.dto.AccountDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.RoleEntity;
import com.fastfood.repository.RoleRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountConverter accountConverter;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder BCryptPasswordEncoder;

	@Override
	@Transactional
	public AccountDTO save(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountConverter.toEntity(dto);
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			roles.add(roleRepository.findByName("USER"));
			accountEntity.setRoles(roles);
			accountEntity.setPassword(BCryptPasswordEncoder.encode(dto.getPassword()));
			return accountConverter.toDTO(userRepository.save(accountEntity));
		}
		return null;

	}

	@Override
	public AccountDTO findByUsername(String userName) {
		AccountEntity entity = userRepository.findOneByUserNameAndStatus(userName, SystemConstant.ACTIVE_STATUS);
		AccountDTO dto = accountConverter.toDTO(entity);
		return dto;
	}

	@Override
	@Transactional
	public AccountDTO saveFBAccount(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountConverter.toEntity(dto);
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			roles.add(roleRepository.findByName("USER"));
			accountEntity.setRoles(roles);
			accountEntity.setPassword(BCryptPasswordEncoder.encode("hdhsiuh2"));
			return accountConverter.toDTO(userRepository.save(accountEntity));
		}
		return null;
	}

}
