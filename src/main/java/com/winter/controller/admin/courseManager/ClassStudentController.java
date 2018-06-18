package com.winter.controller.admin.courseManager;

import com.winter.model.class_student;
import com.winter.model.teacher;
import com.winter.model.userinfo;
import com.winter.services.*;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 展示本教师班上的所有学生，教师为学生添加成绩
 * 下拉列表展示当前教师所开的的所有课程
 */
@Controller
@RequestMapping(value = "/admin")
public class ClassStudentController {

    @Autowired
    CourseClassService courseClassService;

    @Autowired
    ClassStudentService classStudentService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    RoleInfoService roleInfoService;

    @RequestMapping(value = "coursestudent")
    public String classStudent(Model model, HttpSession session){
        //获取登录身份信息
        userinfo userModel = userInfoService.selectByPrimaryKey(session.getAttribute(StringUtil.winUserSession).toString());

        String roleName = roleInfoService.selectByPrimaryKey(userModel.getUserrole()).getRolename();

        if (!roleName.equals("教工")){return "withoutright"; }

        teacher tecModel = teacherService.selectByTcode(userModel.getUserid());

        //返回所有这个教师的课程


        model.addAttribute("userName",tecModel.getTeachername());
        model.addAttribute("teacherid",tecModel.getTeacherid());

        return "admin/coursemanager/coursestudent";
    }

//    @RequestMapping(value = "getcoursestudent")
//    public String getCourseStudent(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
//        //返回学生的列表
//        List<class_student> csList = classStudentService.
//    }
}
