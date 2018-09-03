/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/25 22:47
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.system.security;

import com.hyacinth.module.base.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 权限控制用户
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
public class SecurityUser extends User implements UserDetails{

    private static final long serialVersionUID = 1L;

    /**
     * 声明放置用户角色的集合
     **/
    private List<String> roles;

    /**
     * 记住登录的用户
     *
     * @param user  用戶
     * @param roles 角色編碼集合（ROLE_TRUSTED_CLIENT）
     */
    SecurityUser(User user, List<String> roles) {
        if (null != user) {
            this.setId(user.getId());
            this.setUserName(user.getUserName());
            this.setPassWord(user.getPassWord());
            this.roles = roles;
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 根据自定义逻辑来返回用户权限，如果用户权限返回空
        // 或者和拦截路径对应权限不同，验证不通过
        List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
        if (roles != null && roles.size() > 0) {
            for (String role : roles) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassWord();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
