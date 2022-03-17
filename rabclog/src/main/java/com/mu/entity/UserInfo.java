package com.mu.entity;

import lombok.Data;

/**
 * @author Mu
 */

@Data
public class UserInfo {
    private int infoId;
    private int userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userPhone;
    private String userStatus;
}
