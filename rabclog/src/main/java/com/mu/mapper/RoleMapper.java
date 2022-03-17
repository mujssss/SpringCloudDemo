package com.mu.mapper;

import com.mu.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Mu
 */
@Mapper
@Repository
public interface RoleMapper {
    /**
     * 获取当前用户的所有角色
     * @param userId
     * @return
     */
    List<Role> getRoleListByUserId(@Param("userId")int userId);
    /**
     * 通过url获取有权限的角色列表
     */
    List<Role> getRoleListByOperateUrl(@Param("operateUrl")String operateUrl);
    /**
     * 获取角色列表
     */
    List<Role> getAllRole();

}
