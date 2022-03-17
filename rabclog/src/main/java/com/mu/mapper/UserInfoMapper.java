package com.mu.mapper;

import com.mu.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Mu
 */
@Mapper
@Repository
public interface UserInfoMapper {
    /**
     * 获取当前用户的相关信息
     * @param userId
     * @return
     */
    UserInfo getUserInfo(@Param("userId")int userId);
}
