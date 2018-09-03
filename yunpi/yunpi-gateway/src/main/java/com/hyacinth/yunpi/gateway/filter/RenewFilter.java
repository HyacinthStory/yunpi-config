/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：RenewFilter.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.hyacinth.yunpi.gateway.filter;

import com.hyacinth.yunpi.common.enums.ErrorCodeEnum;
import com.hyacinth.yunpi.common.exception.BusinessException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token续租过滤器
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Component
@Slf4j
public class RenewFilter extends ZuulFilter {

    private static final String HEADER_RENEW = "Renew-Header";
    private static final String TRUE = "true";
    private static final String JWT_SIGNING_KEY = "yunpi";

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWT_SIGNING_KEY);
        return converter;
    }
    private static final int EXPIRES_IN = 60 * 20;

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 10;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		log.info("RenewFilter - token续租...");
		RequestContext requestContext = RequestContext.getCurrentContext();
		try {
			doSomething(requestContext);
		} catch (Exception e) {
			log.error("RenewFilter - token续租. [FAIL] EXCEPTION={}", e.getMessage(), e);
			throw new BusinessException(ErrorCodeEnum.UAC10011041);
		}
		return null;
	}

	private void doSomething(RequestContext requestContext) {
		HttpServletRequest request = requestContext.getRequest();
		String token = StringUtils.substringAfter(request.getHeader(HttpHeaders.AUTHORIZATION), "bearer ");
		if (StringUtils.isEmpty(token)) {
			return;
		}
		OAuth2AccessToken oAuth2AccessToken = jwtTokenStore().readAccessToken(token);
		int expiresIn = oAuth2AccessToken.getExpiresIn();

		if (expiresIn < EXPIRES_IN) {
			HttpServletResponse servletResponse = requestContext.getResponse();
			servletResponse.addHeader(HEADER_RENEW, TRUE);
		}
	}

}