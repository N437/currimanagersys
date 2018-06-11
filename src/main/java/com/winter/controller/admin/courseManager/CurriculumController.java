package com.winter.controller.admin.courseManager;

import com.winter.model.education_program;
import com.winter.model.major;
import com.winter.services.CourseService;
import com.winter.services.CurriculumService;
import com.winter.services.EducationProgramService;
import com.winter.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
