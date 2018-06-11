package com.winter.controller.admin.courseManager;

import com.winter.model.course;
import com.winter.model.course_category;
import com.winter.model.modelUtil.CourseWithCategory;
import com.winter.services.CourseCategoryService;
import com.winter.services.CourseService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 每门课程属于某个类别，课程可能包含课件、实验案例等学习资源。
 */
@Controller
@RequestMapping(value = "/admin")
public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    CourseCategoryService courseCategoryService;

    /**
     * 课程管理首页,返回课程类型信息
     *
     * @param model
     * @return
     */
    @RequestMapping("courseindex")
    public String CourseIndex(Model model) {
        List<course_category> ccList = courseCategoryService.selectAll();

        model.addAttribute("typeList", ccList);
        return "admin/coursemanager/courseindex";
    }

    /**
     * 获取课程列表
     * 基本信息和类别信息
     *
     * @param curr
     * @param nums
     * @return
     */
    @RequestMapping(value = "courseget")
    @ResponseBody
    public Map<String, Object> CourseGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums) {
        int countNum = courseService.selectCount();
        List<course> courList = courseService.selectBypage(curr, nums);
        //处理课程类型
        List<CourseWithCategory> retList = getCourseWithCategory(courList);
        return LayUItableResponse.addData(0, "", countNum, retList);
    }

    /**
     * 添加课程
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "courseadd")
    @ResponseBody
    public String CourseAdd(@ModelAttribute course model) {
        String result = ResponseCode.error;
        model.setCourseid(StringUtil.getUUID());
        if (model.getCoursename() != "") {
            model.setCreatetime(new Date());
            courseService.insert(model);
            result = ResponseCode.success;
        }
        return result;
    }

    /**
     * 添加课程类别
     *
     * @param coursecategoryName
     * @return
     */
    @RequestMapping(value = "coursecategoryAdd")
    @ResponseBody
    public String CourseCategoryAdd(@RequestParam("coursecategoryName") String coursecategoryName) {
        String result = ResponseCode.error;
        course_category model = new course_category();
        if (!coursecategoryName.equals("")) {
            model.setCoursecategoryid(StringUtil.getUUID());
            model.setCoursecategoryname(coursecategoryName);
            model.setCreatetime(new Date());

            courseCategoryService.insert(model);

            result = ResponseCode.success;
        }
        return result;
    }

    private List<CourseWithCategory> getCourseWithCategory(List<course> courList) {
        List<CourseWithCategory> resList = new ArrayList<>();
        for (course item :
                courList) {
            CourseWithCategory obj = new CourseWithCategory();//数据处理
            String categoryName = courseCategoryService.selectByPrimaryKey(item.getCoursecategoryid()).getCoursecategoryname();

            obj.setCoursename(item.getCoursename());
            obj.setCoursecategoryid(item.getCoursecategoryid());
            obj.setCoursenum(item.getCoursenum());
            obj.setEnname(item.getEnname());
            obj.setScore(item.getScore());
            obj.setChour(item.getChour());
            obj.setLhour(item.getLhour());
            obj.setTchour(item.getTchour());
            obj.setTlhour(item.getTlhour());
            obj.setCourseid(item.getCourseid());
            obj.setCoursecategoryname(categoryName);

            resList.add(obj);
        }
        return resList;
    }
}
