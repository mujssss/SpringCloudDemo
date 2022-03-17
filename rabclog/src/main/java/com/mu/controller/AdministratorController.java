package com.mu.controller;

import com.mu.entity.AccountVo;
import com.mu.service.RoleService;
import com.mu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.jws.WebParam;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    /**
     * security 默认登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login(){
        return "log";
    }

    /*
    这里使用security的登录规则     如果注释去掉  就得自己定义登录规则
    @RequestMapping("/doLogIn")
    public String doLogin(){
        return null;
    }
    */

    /**
     * 注册
     */

    @RequestMapping("/register")
    public String register(Model model){
        model.addAttribute("roleList",roleService.allRole());
        return "register";
    }

    @RequestMapping("/doRegister")
    public String doRegister(AccountVo vo){
        userService.userInsert(vo);
        return "/log";
    }
}
