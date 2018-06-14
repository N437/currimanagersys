package com.winter.controller.admin.courseManager;

import com.winter.model.course;
import com.winter.model.course_category;
import com.winter.model.curriculum;
import com.winter.model.modelUtil.CourseWithCategory;
import com.winter.services.CourseCategoryService;
import com.winter.services.CourseService;
import com.winter.services.CurriculumService;
import com.winter.services.EducationProgramService;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class PushCourToEduProController {
    @Autowired
    EducationProgramService educationProgramService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseCategoryService courseCategoryService;

    @Autowired
    CurriculumService curriculumService;

    @RequestMapping(value = "coursetoedupro",method = RequestMethod.GET)
    public String courseEduPro(Model model,String educationprogramid){
        String educationprogramname = educationProgramService.selectByPrimaryKey(educationprogramid).getName();
        List<course_category> ccList = courseCategoryService.selectAll();

        model.addAttribute("typeList", ccList);
        model.addAttribute("educationprogramid",educationprogramid);
        model.addAttribute("educationprogramname",educationprogramname);
        return "admin/coursemanager/coursetoedupro";
    }

    /**
     * 将专业添加到课程体系中
     * @param educationprogramid
     * @param courseid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "addcoursetoedupro")
    public String pushCourseToEduPro(@ModelAttribute curriculum model) {
        String result = ResponseCode.error;

        course coursemodel = courseService.selectByPrimaryKey(model.getCourseid());

        String coursecateid = courseCategoryService.selectByPrimaryKey(coursemodel.getCoursecategoryid()).getCoursecategoryid();

        model.setCurriculumid(StringUtil.getUUID());
        model.setCoursecategoryid(coursecateid);

        curriculumService.insert(model);
        result = ResponseCode.success;

        return result;
    }
}
