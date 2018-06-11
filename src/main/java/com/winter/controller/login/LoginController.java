package com.winter.controller.login;

import com.winter.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")//匹配请求
public class LoginController {

    @Autowired
    private UserInfoService userinfoService;

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(Model model){
        return "login";
    }
}