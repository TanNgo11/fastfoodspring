package com.fastfood.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastfood.dto.AccountDTO;
import com.fastfood.entity.AccountEntity;

@Component
public class AccountMapper {

	@Autowired
	private  ModelMapper modelMapper;


//	public AccountMapper(ModelMapper modelMapper) {
//		this.modelMapper = modelMapper;
////
////		modelMapper.createTypeMap(AccountEntity.class, AccountDTO.class).addMapping(src -> src.getRoles(),
////				AccountDTO::setListRole);
////
////		modelMapper.createTypeMap(AccountDTO.class, AccountEntity.class).addMapping(scr -> scr.getListRole(),
////				AccountEntity::setRoles);
//
//	}

	public AccountDTO mapToDTO(AccountEntity accountEntity) {
		AccountDTO result = modelMapper.map(accountEntity, AccountDTO.class);
		result.setAddress(accountEntity.getAddress());
		result.setEmail(accountEntity.getEmail());
		result.setUsername(accountEntity.getUserName());
		result.setFullName(accountEntity.getFullName());
		result.setOauth2Id("");
		result.setPassword("");
		result.setStatus(accountEntity.getStatus());
		
		return result;
	}

	public AccountEntity mapToEntity(AccountDTO accountDTO) {
		AccountEntity result =  modelMapper.map(accountDTO, AccountEntity.class);
		result.setAddress(accountDTO.getAddress());
		result.setEmail(accountDTO.getEmail());
		result.setUserName(accountDTO.getUsername());
		result.setFullName(accountDTO.getFullName());
		result.setOauth2Id(accountDTO.getOauth2Id());
		result.setPassword(accountDTO.getPassword());
		result.setStatus(accountDTO.getStatus());
		
		return result;
	}

}
