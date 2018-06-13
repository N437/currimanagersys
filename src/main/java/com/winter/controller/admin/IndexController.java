package com.winter.controller.admin;

import com.winter.model.roleinfo;
import com.winter.model.userinfo;
import com.winter.services.RoleInfoService;
import com.winter.services.UserInfoService;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RoleInfoService roleInfoService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        String result = "login";
        Object userinfoid = session.getAttribute(StringUtil.winUserSession);

        if (userinfoid != null) {
            userinfo user = userInfoService.selectByPrimaryKey(userinfoid.toString());
            if (user != null) {
                roleinfo userRole = roleInfoService.selectByPrimaryKey(user.getUserrole());
                model.addAttribute("userName", userRole.getRolename());
                result = "admin/index";
            }
        }
        return result;
    }
}
