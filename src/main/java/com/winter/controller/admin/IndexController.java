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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
                if (userRole.getRolename().equals("管理员")){
                    return "admin/index";
                } else if (userRole.getRolename().equals("教工")) {
                    return "admin/index_tec";
                } else if (userRole.getRolename().equals("学生")) {
                    return "admin/index_stu";
                }
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "exit")
    public void exit(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute(StringUtil.winUserSession)!=null){
            session.removeAttribute(StringUtil.winUserSession);
            response.sendRedirect("/login");
        }
    }
}
