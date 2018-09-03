/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/25 21:06
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.system.security;

import com.hyacinth.common.constant.IDict;
import com.hyacinth.module.base.entity.Role;
import com.hyacinth.module.base.entity.User;
import com.hyacinth.module.repository.RoleRepository;
import com.hyacinth.module.repository.UserRepository;
import com.hyacinth.util.XaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Slf4j
@Component
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpringSessionRegistry sessionRegistry;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,
                                    RoleRepository roleRepository,
                                    SpringSessionRegistry sessionRegistry) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.sessionRegistry = sessionRegistry;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录信息：username|"+username);
        // 根据username获取用户信息，不存在抛出异常，登陆失败
        User user = userRepository.findByUserNameAndDataFlag(username, IDict.dataFlag_OK);
        if (user == null) {
            throw new UsernameNotFoundException("username " + username + " not found!");
        }
        // 查询spring session，如果存在该用户的session，将其设置为过期失效
        List<Object> objects = sessionRegistry.getAllPrincipals();
        if (XaUtils.isNotEmpty(objects)) {
            for (Object obj : objects) {
                User authUser = (User) obj;
                if (username.equals(authUser.getUserName())) {
                    // 查询为失效的会话
                    List<SessionInformation> sessions = sessionRegistry.getAllSessions(authUser,
                            false);
                    if (sessions != null) {
                        for(SessionInformation session:sessions){
                            //设置session过期
                            session.expireNow();
                            //如果已经过期，则删除该用户的session信息
                            if(session.isExpired()){
                                sessionRegistry.removeSessionInformation(session.getSessionId());
                                log.info(authUser.getUserName() + "重新登录到系统！");
                            }
                        }
                    } else {
                        log.info("该用户（"+user.getUserName()+"）没有失效的会话！");
                    }
                    break;
                }
            }
        }
        Iterable<Role> roleList = roleRepository.findRoleByUserId(user.getId());
        // 查询该用户的角色列表
        Set<String> roles = new HashSet<>();
        if (roleList != null && roleList.iterator().hasNext()) {
            for (Role r : roleList) {
                roles.add(r.getRoleCode());
            }
        }
        return new SecurityUser(user, new ArrayList<>(roles));
    }
}
