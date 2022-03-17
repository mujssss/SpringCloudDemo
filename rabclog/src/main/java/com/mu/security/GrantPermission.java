package com.mu.security;

import com.mu.entity.Role;
import com.mu.mapper.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @author Mu
 */
@Component
public class GrantPermission implements FilterInvocationSecurityMetadataSource {
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取访问的url路径
        String requestUrl = ((FilterInvocation)o).getRequestUrl();
        //获取有访问该url权限的角色列表
        List<Role> roleList = roleMapper.getRoleListByOperateUrl(requestUrl);
        if (!roleList.isEmpty()){
            String[] grantArray = new String[roleList.size()];
            System.out.println("--授权");
            for(int i =0;i< roleList.size();i++){
            System.out.println();
                grantArray[i] = roleList.get(i).getRoleName();
                System.out.println("--授权角色-"+roleList.get(i).getRoleName());
            }
            System.out.println("--done--");
            // java.util.List<org.springframework.security.access.ConfigAttribute> createList(String... attributeNames)
            return SecurityConfig.createList(grantArray);
        }else{
            return SecurityConfig.createList("NOT_NULL");
        }
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
