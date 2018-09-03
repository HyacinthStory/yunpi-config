/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/25 22:29
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.system.security;

import com.hyacinth.module.base.entity.User;
import com.hyacinth.util.WebUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理类
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        User userDetails =(User)authentication.getPrincipal();
        log.info("管理员 "+userDetails.getUserName()+" 登录成功，IP:"+ WebUtils.getRemoteAddr(request));
        // 记录登陆日志 TODO
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
