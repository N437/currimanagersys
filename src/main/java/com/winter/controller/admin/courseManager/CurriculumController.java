package com.winter.controller.admin.courseManager;

import com.winter.model.curriculum;
import com.winter.model.education_program;
import com.winter.model.major;
import com.winter.model.modelUtil.CourseWithCategory;
import com.winter.model.modelUtil.CurriWithName;
import com.winter.services.*;
import com.winter.utils.LayUItableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class CurriculumController {

    @Autowired
    MajorService majorService;

    @Autowired
    CurriculumService curriculumService;

    @Autowired
    CourseService courseService;

    @Autowired
    CourseCategoryService courseCategoryService;

    @Autowired
    EducationProgramService educationProgramService;

    /**
     * 返回专业类型
     * @param model
     * @return
     */
    @RequestMapping(value = "curriculum")
    public String curriculumIndex(Model model) {
        //返回专业、培养方案二级联动
        List<major> mojList = majorService.selectAll();
        model.addAttribute("typeList", mojList);
        return "admin/coursemanager/curriculum";
    }

    /**
     * 三级联动
     * 通过专业变动获取培养方案
     * 通过培养方案变动获取对应的课程类别再获取课程？？？
     */
    @RequestMapping(value = "geteduprobymajor")
    @ResponseBody
    public Map<String,String> getEduProByMajor(@RequestParam("majorid") String majorid){
        List<education_program> eduList = educationProgramService.selectByMajorId(majorid);
        Map<String,String> eduMap = new HashMap<>();
        for (education_program item :
                eduList) {
            eduMap.put(item.getEducationprogramid(), item.getName());
        }
        return eduMap;
    }

    /**
     * 获取课程体系列表
     * @param curr
     * @param nums
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getcurri")
    public Map<String,Object> getCurriculum(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
        int countNum = curriculumService.selectCount();
        List<curriculum> curriList = curriculumService.selectBypage(curr, nums);
        //处理课程类型
        List<CurriWithName> retList = getCurriculumWithName(curriList);
        return LayUItableResponse.addData(0, "", countNum, retList);
    }

    private List<CurriWithName> getCurriculumWithName(List<curriculum> list) {
        //获取名称：课程名称，课程类型名称、培养方案名称

        List<CurriWithName> resList = new ArrayList<>();
        for (curriculum item :
                list) {

            CurriWithName obj = new CurriWithName();//数据处理

            //获取课程名称
            String courseName = courseService.selectByPrimaryKey(item.getCourseid()).getCoursename();

            //获取课程类型名称
            String categoryName = courseCategoryService.selectByPrimaryKey(item.getCoursecategoryid()).getCoursecategoryname();

            //获取培养方案名称
            String eduProName = educationProgramService.selectByPrimaryKey(item.getEducationprogramid()).getName();

            obj.setCoursecategoryname(categoryName);
            obj.setCoursename(courseName);
            obj.setEducationprogramname(eduProName);
            obj.setCoursecategoryid(item.getCoursecategoryid());
            obj.setCourseid(item.getCourseid());
            obj.setCurriculumid(item.getCurriculumid());
            obj.setEducationprogramid(item.getEducationprogramid());
            obj.setIsdegree(item.getIsdegree());
            obj.setSemester(item.getSemester());
            obj.setDegreeis(item.getIsdegree()==true?"是":"否");

            resList.add(obj);
        }
        return resList;
    }

}
