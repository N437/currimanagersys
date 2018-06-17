package com.winter.controller.admin.courseManager;

import com.winter.model.*;
import com.winter.services.*;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    PlanStudyCourseService planStudyCourseService;

    @RequestMapping(value = "courseplan")
    public String coursePlan(Model model,HttpSession session){
        String result = "withoutright";

        //id为空不允许访问
        userinfo userModel = userInfoService.selectByPrimaryKey(session.getAttribute(StringUtil.winUserSession).toString());
        String userRole = roleInfoService.selectByPrimaryKey(userModel.getUserrole()).getRolename();
        if (userRole.equals("学生")){
            student stuModel = studentService.selectByScode(userModel.getUserid());
            model.addAttribute("userName",stuModel.getStudentname());
            List<course_category> ccList = courseCategoryService.selectAll();

            model.addAttribute("typeList", ccList);

            model.addAttribute("studentid", stuModel.getStudentid());

            result="admin/coursemanager/courseplan";
        }

        return result;
    }


    @RequestMapping(value = "studentselectcourse")
    @ResponseBody
    public String selectCourse(@ModelAttribute plan_study_course model){
        String result = ResponseCode.error;

        course coursemodel = courseService.selectByPrimaryKey(model.getCourseid());

        String coursecateid = courseCategoryService.selectByPrimaryKey(coursemodel.getCoursecategoryid()).getCoursecategoryid();

        model.setPlanstudycourseid(StringUtil.getUUID());

        planStudyCourseService.insert(model);
        result = ResponseCode.success;

        return result;
    }
}
