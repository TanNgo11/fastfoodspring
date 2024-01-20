package com.fastfood.oauth2;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

@Component
public class RestFB {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private UserRepository userRepository;

	public static String FACEBOOK_APP_ID = "3549214752035027";
	public static String FACEBOOK_APP_SECRET = "27af78d2ad059aa4458d6b2494ac16ec";
	public static String FACEBOOK_REDIRECT_URL = "https://localhost:8443/AccessFacebook/login-facebook";
	public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

	public String getToken(final String code) throws ClientProtocolException, IOException {
		String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET,
				FACEBOOK_REDIRECT_URL, code);
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public com.restfb.types.User getUserInfo(final String accessToken) {
		FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
		return facebookClient.fetchObject("me", com.restfb.types.User.class);
	}

	public UserDetails buildUser(com.restfb.types.User user) {

		AccountEntity fbUser = userRepository.findOneByOauth2IdAndStatus(user.getId(), SystemConstant.ACTIVE_STATUS);
		String username = userRepository.findTopByUserNameStartingWithOrderByUserNameDesc("FB").getUserName();
		String newUserName = generateNextUserId(username);

		if (fbUser == null) {
			AccountDTO newFbUser = new AccountDTO();
			newFbUser.setUsername(newUserName);
			newFbUser.setOauth2Id(user.getId());
			newFbUser.setFullName(user.getName());
			newFbUser.setPassword(SystemConstant.DEFAULT_PASSWORD);

			newFbUser = accountService.save(newFbUser);
			return customUserDetailsService.loadUserByUsername(newUserName);

		}

		return customUserDetailsService.loadUserByUsername(fbUser.getUserName());
	}

	public static String generateNextUserId(String existingUserId) {

		String numericalPart = existingUserId.substring(2);

		int nextNumericalPart = Integer.parseInt(numericalPart) + 1;

		String nextUserId = String.format("FB%03d", nextNumericalPart);

		return nextUserId;
	}

}
