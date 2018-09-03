/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/26 0:16
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.system.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * spring session 管理
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Component
public class SpringSessionRegistry implements SessionRegistry,
        ApplicationListener<SessionDestroyedEvent> {

    private SessionRegistry session; //授权的session

    //必须有一个无参构造器
    public SpringSessionRegistry() {
        session = new SessionRegistryImpl();
    }

    /**
     * 监听session销毁事件
     * @param event session销毁事件
     */
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        String sessionId = event.getId();
        removeSessionInformation(sessionId);
    }

    /**
     * 从session中获取所有登录用户信息
     */
    @Override
    public List<Object> getAllPrincipals() {
        return session.getAllPrincipals();
    }

    /**
     * 获取session信息的集合
     * @param principal              登陆的用户信息
     * @param includeExpiredSessions 是否包含过期的session
     * @return session信息的集合
     */
    @Override
    public List<SessionInformation> getAllSessions(Object principal,
                                                   boolean includeExpiredSessions) {
        return session.getAllSessions(principal, includeExpiredSessions);
    }

    /**
     * 获取session信息
     * @param sessionId sessionId
     * @return SessionInformation
     */
    @Override
    public SessionInformation getSessionInformation(String sessionId) {
        return session.getSessionInformation(sessionId);
    }

    /**
     * 刷新最后一次请求
     * @param sessionId sessionId
     */
    @Override
    public void refreshLastRequest(String sessionId) {
        session.refreshLastRequest(sessionId);
    }

    /**
     * 注册新的session
     * @param sessionId sessionId
     * @param principal 登陆用户信息
     */
    @Override
    public void registerNewSession(String sessionId, Object principal) {
        session.registerNewSession(sessionId, principal);
    }

    /**
     * 根据sessionId删除session信息
     * @param sessionId sessionId
     */
    @Override
    public void removeSessionInformation(String sessionId) {
        session.removeSessionInformation(sessionId);
    }
}
