package com.mu.security;

import com.mu.mapper.AccountMapper;
import com.mu.security.handler.LoginSuccessHandler;
import com.mu.security.handler.Logout;
import com.mu.security.handler.PermissionErr;
import com.mu.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AccountService accountService;

    @Resource
    private GrantPermission grantPermission;

    @Resource
    private ValidPermission validPermission;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置用户登录方式， 获取当前登录账号的账密， 将用户输入的密码加密 并于数据库中数据进行比对
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置过滤的请求路径
        web.ignoring().antMatchers("/administrator/login","/img/**","/css/**","/js/**","/administrator/register","/administrator/doRegister");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable()
                .and().authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setAccessDecisionManager(validPermission);
                o.setSecurityMetadataSource(grantPermission);
                return o;
            }
        }).and().formLogin().loginPage("/administrator/login")
                .loginPage("/administrator/login")
                .loginProcessingUrl("/administrator/doLogIn")
                .usernameParameter("account")
                .passwordParameter("pass")
                .successHandler(new LoginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/administrator/logout")
                .logoutSuccessHandler(new Logout())
                .permitAll()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedHandler(new PermissionErr());
    }
}
