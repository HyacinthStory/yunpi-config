/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/9/2 15:22
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.yunpi.gateway.filter;

import com.hyacinth.yunpi.common.enums.ErrorCodeEnum;
import com.hyacinth.yunpi.common.exception.BusinessException;
import com.hyacinth.yunpi.utils.PublicUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权请求头校验过滤器
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Slf4j
@Component
public class AuthHeaderFilter extends ZuulFilter {

    private static final String BEARER_TOKEN_TYPE = "bearer ";
    private static final String OPTIONS = "OPTIONS";
    private static final String AUTH_PATH = "/auth";
    private static final String LOGOUT_URI = "/oauth/token";
    private static final String HEADER_LABEL = "x-label";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("AuthHeaderFilter - 开始鉴权...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        try {
            doSomething(requestContext);
        } catch (Exception e) {
            log.error("AuthHeaderFilter - [FAIL] EXCEPTION={}", e.getMessage(), e);
            throw new BusinessException(ErrorCodeEnum.UAC10011041);
        }
        return null;
    }

    private void doSomething(RequestContext context) throws ZuulException {
        HttpServletRequest request = context.getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        log.info("requestURI={} | method={}", requestURI, method);
        if (OPTIONS.equalsIgnoreCase(method)
                || !requestURI.contains(AUTH_PATH)
                || !requestURI.contains(LOGOUT_URI)) {
            return;
        }
        String authHeader = getAuthHeader(request);
        if (PublicUtil.isEmpty(authHeader)) {
            throw new ZuulException("刷新页面重试", 403, "check token fail");
        }
        if (authHeader.startsWith(BEARER_TOKEN_TYPE)) {
            context.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authHeader);
            log.info("authHeader={} ", authHeader);
            // 传递给后续微服务
            context.addZuulRequestHeader(HEADER_LABEL, authHeader);
        }
    }

    /**
     * Gets auth header.
     * @param request the request
     * @return the auth header
     */
    private static String getAuthHeader(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isBlank(authHeader)) {
            throw new BusinessException(ErrorCodeEnum.UAC10011040);
        }
        return authHeader;
    }
}
