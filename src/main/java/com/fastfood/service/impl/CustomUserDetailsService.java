package com.fastfood.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.dto.MyUser;
import com.fastfood.entity.AccountEntity;
import com.fastfood.entity.PasswordResetToken;
import com.fastfood.entity.RoleEntity;
import com.fastfood.repository.ResetPasswordTokenRepository;
import com.fastfood.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ResetPasswordTokenRepository ResetPasswordTokenRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);

		if (userEntity == null) {
			return null;

		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		MyUser myUser = new MyUser(userEntity.getId(), userEntity.getFullName(), username, userEntity.getPassword(),
				true, true, true, true, authorities);

		return myUser;
	}

	public String generateResetPasswordToken(AccountEntity account) {

		UUID uuid = UUID.randomUUID();
		LocalDateTime currentDateTime = LocalDateTime.now();
		LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setAccountEntity(account);
		passwordResetToken.setToken(uuid.toString());
		passwordResetToken.setExpiryDateTime(expiryDateTime);

		PasswordResetToken token = ResetPasswordTokenRepository.save(passwordResetToken);

		if (token != null) {
			String endPoint = "http://fastfoodspring.com/resetPassword";
			return endPoint + "/" + passwordResetToken.getToken();
		}
		return "";

	}
	public boolean hasExipred(LocalDateTime expiryDateTime) {
		LocalDateTime currentDateTime = LocalDateTime.now();
		return expiryDateTime.isAfter(currentDateTime);
	}

}