package com.winter.controller.login;

import com.winter.model.userinfo;
import com.winter.services.RoleInfoService;
import com.winter.services.UserInfoService;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/logindata")
public class LoginDataController {
    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RoleInfoService roleInfoService;

    /**
     * 判断用户是否存在
     * @param userid
     * @return
     */
    @RequestMapping(value = "exist")
    public String exist(@RequestParam("userid") String userid) {
        String result = ResponseCode.error;
        userinfo countInfo = userInfoService.selectByUserid(userid);
        if (countInfo != null) {
            result = ResponseCode.success;
        }
        return result;
    }

    @RequestMapping(value = "loginin")
    public String loginin(HttpSession session, @RequestParam("userid") String userid, @RequestParam("password") String password){
        String result = ResponseCode.error;
        userinfo countInfo = userInfoService.selectByUserid(userid);
        if (countInfo != null) {
            if (countInfo.getUserpwd().equals(password)){
                //有必要进行 MD5 密码明文加密，此处省略

                //存储 session cookie数据
                //session存储用户id
                session.setAttribute(StringUtil.winUserSession,countInfo.getUserinfoid());

                //跳转页面
                result = "/admin/index";
            }else {
                result=ResponseCode.other;
            }
        }
        return result;
    }
}
