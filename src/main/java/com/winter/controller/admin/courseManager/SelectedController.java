package com.winter.controller.admin.courseManager;

import com.winter.model.course;
import com.winter.model.student;
import com.winter.model.userinfo;
import com.winter.services.CourseService;
import com.winter.services.RoleInfoService;
import com.winter.services.StudentService;
import com.winter.services.UserInfoService;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class SelectedController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RoleInfoService roleInfoService;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "getselected")
    public String selectedCourse(Model model, HttpSession session) {
        userinfo userModel = userInfoService.selectByPrimaryKey(session.getAttribute(StringUtil.winUserSession).toString());
        String roleName = roleInfoService.selectByPrimaryKey(userModel.getUserrole()).getRolename();
        if (!roleName.equals("学生")){return "withoutright";}
        student stuModel = studentService.selectByScode(userModel.getUserid());
        model.addAttribute("userName",stuModel.getStudentname());
        model.addAttribute("studentid",stuModel.getStudentid());
        return "admin/coursemanager/selectcourse";
    }

    @RequestMapping(value = "getselectedlist")
    @ResponseBody
    public Map<String, Object> getList(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
        int countNum = courseService.selectCount();
        List<course> courList = courseService.selectBypage(curr, nums);
        return LayUItableResponse.addData(0,"",countNum,courList);
    }
}
