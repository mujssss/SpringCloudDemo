package com.mu.service;

import com.mu.entity.AccountVo;
import com.mu.mapper.AccountMapper;
import com.mu.mapper.RoleUserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private AccountMapper accountMapper;

    @Resource
    private RoleUserMapper roleUserMapper;


    public void userInsert(AccountVo accountVo){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        accountVo.setLoginPass(encoder.encode(accountVo.getLoginPass()));
        accountMapper.accountInsert(accountVo);
        if(accountVo.getRoleId() != 1)
            accountVo.setUserStatus("1");
        accountMapper.userInfoInsert(accountVo);
        roleUserMapper.roleUserInsert(accountVo.getUserId(),accountVo.getRoleId());
    }
}
