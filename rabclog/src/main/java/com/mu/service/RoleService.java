package com.mu.service;

import com.mu.entity.Role;
import com.mu.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> allRole(){
        return roleMapper.getAllRole();
    }
}
