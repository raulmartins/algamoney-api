package com.algaworks.algamoneyapi.token;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.algaworks.algamoneyapi.config.property.AlgamoneyApiProperty;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessToken>{
	
	@Autowired
	private AlgamoneyApiProperty algamoneyApiProperty;
	
	@Override
	public OAuth2AccessToken beforeBodyWrite(OAuth2AccessToken body, MethodParameter arg1, MediaType arg2,
			Class<? extends HttpMessageConverter<?>> arg3, ServerHttpRequest request, ServerHttpResponse response) {
		
		String refreshToken = body.getRefreshToken().getValue();
		
		HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
		HttpServletResponse res = ((ServletServerHttpResponse) response).getServletResponse();
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) body;
		
		adicionarRefreshTokenNoCookie(refreshToken, req, res);
		removerRefreshTokenDoBody(token);
		
		return body;
	}

	private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req,
			HttpServletResponse res) {
		
		Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(algamoneyApiProperty.isEnableHttps()); //TODO:migrar para true em produção
		refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(2592000);
	
		res.addCookie(refreshTokenCookie);
		
	}
	
	private void removerRefreshTokenDoBody(DefaultOAuth2AccessToken token) {
		token.setRefreshToken(null);
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		
		return arg0.getMethod().getName().equals("postAccessToken");
	}

	
	
}
