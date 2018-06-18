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

    @Autowired
    CourseClassService courseClassService;

    @Autowired
    TeacherService teacherService;

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


    @RequestMapping(value = "courseclassget")
    @ResponseBody
    public Map<String,Object> getCourseClass(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
        List<course_class> ccList = courseClassService.selectBypage(curr, nums);
        int countNum = courseClassService.selectCount();
        List<CourseClassWithName> retList = getCourseClassWithName(ccList);

        return LayUItableResponse.addData(0,"",countNum,retList);
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

    private List<CourseClassWithName> getCourseClassWithName(List<course_class> list) {
        List<CourseClassWithName> retList = new ArrayList<>();
        for (course_class item :
                list) {
            course courseModel = courseService.selectByPrimaryKey(item.getCourseid());
            String teacherName = teacherService.selectByPrimaryKey(item.getTeacherid()).getTeachername();

            CourseClassWithName obj = new CourseClassWithName();

            obj.setCoursename(courseModel.getCoursename());
            obj.setTeachername(teacherName);
            obj.setCourseclassid(item.getCourseclassid());
            obj.setCourseid(item.getCourseid());
            obj.setMaxclasssize(item.getMaxclasssize());
            obj.setSemesterid(item.getSemesterid());
            obj.setTeacherid(item.getTeacherid());
            obj.setCoursenum(courseModel.getCoursenum());
            obj.setScore(courseModel.getScore());

            retList.add(obj);

        }
        return retList;
    }
}
