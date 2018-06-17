package com.winter.controller.admin.courseManager;

import com.winter.model.course_category;
import com.winter.model.student;
import com.winter.model.userinfo;
import com.winter.services.*;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 学生选择课程，
 */
@Controller
@RequestMapping(value = "/admin")
public class CoursePlanController {

    @Autowired
    RoleInfoService roleInfoService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseCategoryService courseCategoryService;

    @RequestMapping(value = "courseplan")
    public String coursePlan(Model model,HttpSession session){
        String result = "withoutright";

        //id为空不允许访问
        userinfo userModel = userInfoService.selectByPrimaryKey(session.getAttribute(StringUtil.winUserSession).toString());
        String userRole = roleInfoService.selectByPrimaryKey(userModel.getUserrole()).getRolename();
        if (userRole=="学生"){
            student stuModel = studentService.selectByScode(userModel.getUserid());
            model.addAttribute("userName",stuModel.getStudentname());
            List<course_category> ccList = courseCategoryService.selectAll();

            model.addAttribute("typeList", ccList);

            model.addAttribute("studentid", stuModel.getStudentid());

            result="admin/coursemanager/courseplan";
        }

        return result;
    }


}
