package com.mu.entity;

import lombok.Data;

@Data
public class AccountVo {
    private String loginAccount;
    private String loginPass;
    private int userId;
    private String userName;
    private String userEmail;
    private String userGender;
    private String userPhone;
    private String userStatus;
    private int roleId;
}
