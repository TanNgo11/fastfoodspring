package com.fastfood.oauth2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fastfood.constant.SystemConstant;
import com.fastfood.dto.AccountDTO;
import com.fastfood.entity.AccountEntity;
import com.fastfood.repository.UserRepository;
import com.fastfood.service.IAccountService;
import com.fastfood.service.impl.CustomUserDetailsService;
import com.fastfood.utils.GooglePojo;

@Component
public class RestGoogle {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private UserRepository userRepository;
	
	public static String GOOGLE_CLIENT_ID = "203434021313-q6ukgr4gjiuc1e0l9a17v6vuk2d1fgt1.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "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";
	public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/login-google";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";

	public String getToken(final String code) throws ClientProtocolException, IOException {
		String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID).add("client_secret", GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code).add("grant_type", GOOGLE_GRANT_TYPE)
						.build())
				.execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		GooglePojo googlePojo = mapper.readValue(response, GooglePojo.class);

		return googlePojo;
	}

	public UserDetails buildUser(GooglePojo googlePojo) {
		AccountEntity GoogleUser = userRepository.findOneByOauth2IdAndStatus(googlePojo.getId(), SystemConstant.ACTIVE_STATUS);
		String username = userRepository.findTopByUserNameStartingWithOrderByUserNameDesc("GG").getUserName();
		String newUserName = generateNextUserId(username);

		if (GoogleUser == null) {
			AccountDTO newGoogleUser = new AccountDTO();
			newGoogleUser.setUsername(newUserName);
			newGoogleUser.setOauth2Id(googlePojo.getId());
			newGoogleUser.setFullName(googlePojo.getEmail());
			newGoogleUser.setEmail(googlePojo.getEmail());
			newGoogleUser.setPassword(SystemConstant.DEFAULT_PASSWORD);

			newGoogleUser = accountService.save(newGoogleUser);
			return customUserDetailsService.loadUserByUsername(newUserName);

		}

		return customUserDetailsService.loadUserByUsername(GoogleUser.getUserName());
	}
	
	public static String generateNextUserId(String existingUserId) {

		String numericalPart = existingUserId.substring(2);

		int nextNumericalPart = Integer.parseInt(numericalPart) + 1;

		String nextUserId = String.format("GG%03d", nextNumericalPart);

		return nextUserId;
	}

}
