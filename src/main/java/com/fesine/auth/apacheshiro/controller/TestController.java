package com.fesine.auth.apacheshiro.controller;

import com.fesine.auth.apacheshiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @description: 测试shiro拦截
 * @author: Fesine
 * @createTime:2018/1/10
 * @update:修改内容
 * @author: Fesine
 * @updateTime:2018/1/10
 */
@Controller
public class TestController {


    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "unauthorized";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "admin success";
    }

    @GetMapping("/edit")
    @ResponseBody
    public String edit(){
        return "edit success";
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestParam String username, @RequestParam String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user",user);
            return "index";
        } catch (AuthenticationException e) {
            return "login";
        }
    }


}
