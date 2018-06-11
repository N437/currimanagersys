package com.winter.controller;

import com.winter.model.User;
import com.winter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all")
    public String selectOne(){
        int id = 1;
        User u = userService.findById(id);
        String m=u.getUserName();
        return u.getUserName();
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("Hello","weer");
        return "IndexController";
    }

    @RequestMapping("test")
    public String test(Model model){
        model.addAttribute("Hello","weer");
        model.addAttribute("as","uuuuuuuu");
        return "user/test";
    }
}
