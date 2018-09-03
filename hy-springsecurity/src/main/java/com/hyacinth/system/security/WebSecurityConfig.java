/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/25 18:15
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.system.security;

import com.hyacinth.util.SerialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * 定义BCrypt密码加密器bean
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(4);
    }

    /**
     * 登陆成功处理
     */
    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //指定密码加密器，需要将密码加密后写入数据库
        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        //不删除凭据，以便记住用户
        auth.eraseCredentials(false);
    }

    //http://localhost:8080/login 输入正确的用户名密码 并且选中remember-me 则登陆成功，转到 index页面
    //再次访问index页面无需登录直接访问
    //访问http://localhost:8080/home 不拦截，直接访问，
    //访问http://localhost:8080/hello 需要登录验证后，且具备 “ADMIN”权限hasAuthority("ADMIN")才可以访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/home").permitAll()//访问：/home 无需登录认证权限
                .anyRequest().authenticated() //其他所有资源都需要认证，登陆后访问
                .antMatchers("/hello").hasAuthority("ADMIN") //登陆后之后拥有“ADMIN”权限才可以访问/hello方法，否则系统会出现“403”权限不足的提示
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(loginSuccessHandler()) //登录成功后可使用loginSuccessHandler()存储用户信息，可选
                .and()
                .logout()
                .logoutSuccessUrl("/home") //退出登录后的默认网址是/home
                .permitAll()
                .invalidateHttpSession(true)
                .and()
                .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表
                .tokenValiditySeconds(1209600);
    }

    public static void main(String[] args) {
        System.out.println(SerialUtils.uuid());
        String encode = new BCryptPasswordEncoder(4).encode("123456");
        System.out.println(encode);
    }
}
