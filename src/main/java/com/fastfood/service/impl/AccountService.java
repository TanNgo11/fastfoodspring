package com.fastfood.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.RoleEntity;
import com.fastfood.mapper.AccountMapper;
import com.fastfood.repository.RoleRepository;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.utils.MessageUtil;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder BCryptPasswordEncoder;

	@Autowired
	private ModelMapper mapper;

	@Override
	@Transactional
	public AccountDTO save(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountMapper.mapToEntity(dto);
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			roles.add(roleRepository.findByName("USER"));
			accountEntity.setRoles(roles);
			if (dto.getOauth2Id() == null)
				accountEntity.setOauth2Id(SystemConstant.WEB_USER);
			accountEntity.setStatus(SystemConstant.ACTIVE_STATUS);
			accountEntity.setPassword(BCryptPasswordEncoder.encode(dto.getPassword()));
			return accountMapper.mapToDTO(userRepository.save(accountEntity));
		}
		return null;

	}

	@Override
	public AccountDTO findByUsername(String userName) {
		AccountEntity entity = userRepository.findOneByUserNameAndStatus(userName, SystemConstant.ACTIVE_STATUS);
		AccountDTO dto = accountMapper.mapToDTO(entity);
		return dto;
	}

	@Override
	public AccountDTO findById(long id) {
		AccountEntity accountEntity = userRepository.findOne(id);

		return accountMapper.mapToDTO(userRepository.save(accountEntity));
	}

	@Override
	@Transactional
	public AccountDTO update(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOne(dto.getId());
		if (accountEntity == null) {
			throw new UsernameNotFoundException(MessageUtil.USER_ID_NOT_FOUND + dto.getId());
		}
		List<RoleEntity> listRole = new ArrayList<>();
		if (dto.getRoleName() != null && dto.getRoleName().equals(SystemConstant.ROLE_STAFF)) {
			listRole.add(roleRepository.findByName(SystemConstant.ROLE_STAFF));
			accountEntity.setRoles(listRole);
		} else if (dto.getRoleName() != null && dto.getRoleName().equals(SystemConstant.ROLE_ADMIN)) {
			listRole.add(roleRepository.findByName(SystemConstant.ROLE_ADMIN));
			accountEntity.setRoles(listRole);
		}

		accountEntity.setAddress(dto.getAddress());
		accountEntity.setEmail(dto.getEmail());
		accountEntity.setFullName(dto.getFullName());
		accountEntity.setPhoneNumber(dto.getPhoneNumber());

		return mapper.map(userRepository.save(accountEntity), AccountDTO.class);

	}

	@Override
	public AccountDTO findByOauth2Id(String oauth2Id) {
		return accountMapper
				.mapToDTO(userRepository.findOneByOauth2IdAndStatus(oauth2Id, SystemConstant.ACTIVE_STATUS));
	}

	@Override
	public List<AccountDTO> findByPage(Pageable pageable) {
		Page<AccountEntity> listAccountEntity = userRepository.findAll(pageable);
		List<AccountDTO> result = new ArrayList<>();
		for (AccountEntity item : listAccountEntity.getContent()) {
			AccountDTO dto = accountMapper.mapToDTO(item);

			result.add(dto);
		}
		return result;
	}

	@Override
	public int getTotalAccount() {
		return (int) userRepository.count();
	}

	@Override
	public Page<AccountDTO> findAllByRole(String roleName, Pageable pageable) {
		Page<AccountEntity> listAccountEntity = userRepository.findByRoleName(roleName, pageable);
		Page<AccountDTO> listAccountDTO = listAccountEntity.map(s -> accountMapper.mapToDTO(s));
		return listAccountDTO;
	}

	@Override
	public int getTotalAccountByRole(String roleName) {
		return (int) userRepository.countByRoleName(roleName);
	}

	@Override
	public Optional<AccountDTO> findByIdAndRoleName(long accountId, String roleName) {
		return userRepository.findByIdAndRoleName(accountId, roleName).map(s -> accountMapper.mapToDTO(s));

	}

	@Override
	@Transactional
	public AccountDTO saveAccountStaffOrAdmin(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountMapper.mapToEntity(dto);
			

			List<RoleEntity> listRole = new ArrayList<>();
			if (dto.getRoleName() != null && dto.getRoleName().equals(SystemConstant.ROLE_STAFF)) {
				listRole.add(roleRepository.findByName(SystemConstant.ROLE_STAFF));
				accountEntity.setRoles(listRole);
			} else if (dto.getRoleName() != null && dto.getRoleName().equals(SystemConstant.ROLE_ADMIN)) {
				listRole.add(roleRepository.findByName(SystemConstant.ROLE_ADMIN));
				accountEntity.setRoles(listRole);
			}

			if (dto.getOauth2Id() == null)
				accountEntity.setOauth2Id(SystemConstant.WEB_USER);
			accountEntity.setStatus(SystemConstant.ACTIVE_STATUS);
			accountEntity.setPassword(BCryptPasswordEncoder.encode(dto.getPassword()));
			return accountMapper.mapToDTO(userRepository.save(accountEntity));
		}
		return null;
	}

}
