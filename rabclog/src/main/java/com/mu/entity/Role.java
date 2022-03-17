package com.mu.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Mu
 */
@Data
public class Role implements GrantedAuthority {
    private int roleId;
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }

}
