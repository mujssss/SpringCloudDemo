package com.mu.mapper;

import com.mu.entity.Account;
import com.mu.entity.AccountVo;
import com.mu.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Mu
 */
@Mapper
@Repository
public interface AccountMapper {
    /**
     * 通过当前账号获取用户
     * @param account
     * @return
     */
    Account getAccountByAccount(@Param("account")String account);
    void accountInsert(@Param("accountVo")AccountVo accountVo);
    void userInfoInsert(@Param("accountVo")AccountVo accountVo);

}
