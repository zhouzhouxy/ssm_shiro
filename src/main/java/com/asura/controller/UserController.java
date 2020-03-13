package com.asura.controller;

import com.asura.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shiro")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam("uname")String uname,@RequestParam("upass") String upass){
        //1.获取当前的Subject，通过调用SecurityUtils.getSubject();
        Subject currentUser = SecurityUtils.getSubject();
        //2.判断当前用户是否进行认证，即是否登录
        if(!currentUser.isAuthenticated()){
            //把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(uname, upass, false);
            //--->这个时候他会进入Realm去验证 参数就是UsernamePasswordToken true为记住密码
            try{
                //认证登录
                currentUser.login(token);  //这个时候会调用Realm
            }catch (IncorrectCredentialsException ae){
                // 密码错误
                System.out.println("密码不正确");
                ae.printStackTrace();
                return "/login.html";
            }catch(UnknownAccountException uae){
                //账户不存在
                uae.printStackTrace();
                System.out.println("账户不存在");
                return "/login.html";
            }catch(LockedAccountException lae){
                lae.printStackTrace();
                System.out.println("账户被锁定");
                return "/login.html";
            }catch (AuthenticationException ae){
                // 认证（登录）异常
            }
        }
        return "redirect:/success.html";
    }


    @RequestMapping("/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/login.html";

    }
}
