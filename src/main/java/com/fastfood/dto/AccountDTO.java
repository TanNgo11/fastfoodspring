
package com.fastfood.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO extends AbstractDTO<AccountDTO> {

	private String username;
	private String password;
	private String fullName;
	private String phoneNumber;
	private String address;
	private String email;
	private int status;
	private String oauth2Id;
	private String slug;
	@JsonIgnore
	private List<RoleDTO> listRole;
	private String roleName;

}
