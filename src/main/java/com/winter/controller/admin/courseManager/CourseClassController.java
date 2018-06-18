package com.winter.controller.admin.courseManager;

import com.winter.model.*;
import com.winter.model.modelUtil.CourseClassWithName;
import com.winter.services.*;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class CourseClassController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RoleInfoService roleInfoService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    CourseClassService courseClassService;

    @Autowired
    CourseService courseService;

    String teacherid;

    @RequestMapping(value = "courseclass")
    public String coursseClass(Model model, HttpSession session){
        userinfo userModel = userInfoService.selectByPrimaryKey(session.getAttribute(StringUtil.winUserSession).toString());
        String roleName = roleInfoService.selectByPrimaryKey(userModel.getUserrole()).getRolename();

        if (!roleName.equals("教工")){return "withoutright";}

        teacher tecModel = teacherService.selectByTcode(userModel.getUserid());

        teacherid = tecModel.getTeacherid();

        model.addAttribute("userName",tecModel.getTeachername());
        model.addAttribute("teacherid",tecModel.getTeacherid());
        return "admin/coursemanager/courseclass";
    }

    @RequestMapping(value = "courseclassadd")
    @ResponseBody
    public String courseClassAdd(@ModelAttribute course_class model){
        String result = ResponseCode.error;
        model.setCourseclassid(StringUtil.getUUID());
        courseClassService.insert(model);
        result = ResponseCode.success;
        return result;
    }
}
