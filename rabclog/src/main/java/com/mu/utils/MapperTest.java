package com.mu.utils;

import com.mu.mapper.AccountMapper;
import com.mu.mapper.RoleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class MapperTest {

    @Resource
    private RoleMapper mapper;

    @Resource
    private AccountMapper accountMapper;

    @Test
    public void test(){
//        System.out.println(mapper.getRoleListByUserId(1));

        System.out.println(accountMapper.getAccountByAccount("admin"));

    }
}
