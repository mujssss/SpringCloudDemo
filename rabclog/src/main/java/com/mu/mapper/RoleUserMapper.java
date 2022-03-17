package com.mu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RoleUserMapper {

    void roleUserInsert(@Param("userId")int userId,@Param("roleId")int roleId);
}
