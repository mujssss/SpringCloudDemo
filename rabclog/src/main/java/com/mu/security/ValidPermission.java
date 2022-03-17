package com.mu.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ValidPermission implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //authentication-当前登录用户   collection-GrantPermission.get
        System.out.println("---开始权限认证---");
        System.out.println("auth" + authentication);
        for(ConfigAttribute attribute:collection){
            if(authentication == null){
                System.out.println("当前未有用户登录,权限认证失败");
                throw new AccessDeniedException("认证失败");
            }
            //获取授权角色名
            String grantName = attribute.getAttribute();
            if(grantName.equals("NOT_NULL")) {
                if(authentication instanceof AnonymousAuthenticationToken){
                    System.out.println("----用户未登录");
                    throw new BadCredentialsException("请先登录");
                }else{
                    System.out.println("----用户已登录");
                    return;
                }
            }
            //获取当前用户的角色列表  account.getAuthorities
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority role:authorities){
                System.out.println("--授权角色: "+grantName+" ,用户角色: "+ role.getAuthority());
                if(grantName.equals(role.getAuthority())){
                    System.out.println("权限认证成功");
                    return;
                }
            }
        }
        System.out.println("权限认证结束");
        System.out.println("权限认证失败");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
