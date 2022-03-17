package com.mu.service;

import com.mu.entity.Account;
import com.mu.entity.UserInfo;
import com.mu.mapper.AccountMapper;
import com.mu.mapper.UserInfoMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Mu
 * 实现UserDetailsService接口
 * 实现loadUserByUserName 通过登录号获取登录用户
 */
@Service
public class AccountService implements UserDetailsService {

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Account  account = accountMapper.getAccountByAccount(s);
        if(account == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        UserInfo userInfo = userInfoMapper.getUserInfo(account.getId());
        if(userInfo.getUserStatus().equals("1"))
            throw new UsernameNotFoundException("账号不可用");

        return account;
    }
}
