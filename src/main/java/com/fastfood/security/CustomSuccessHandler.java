package com.fastfood.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fastfood.utils.SecurityUtils;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public CustomSuccessHandler() {
		super();
		setUseReferer(true);
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(request, authentication);
		if (response.isCommitted()) {
			return;
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	private String determineTargetUrl(HttpServletRequest request, Authentication authentication) {

		HttpSession session = request.getSession();
		String redirectUrl = (String) session.getAttribute("redirectUrlAfterLogin");

		session.removeAttribute("redirectUrlAfterLogin");

		

		String url = "";
		List<String> roles = SecurityUtils.getAuthorities();
		if (isAdmin(roles)) {
			url = "/admin/home";
		} else if (isUser(roles)) {
			if (redirectUrl != null) {
				return redirectUrl;
			}
			url = "/home";
		}
		return url;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ADMIN") || roles.contains("STAFF") || roles.contains("SALES")) {
			return true;
		}
		return false;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains("USER")) {
			return true;
		}
		return false;
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

}
