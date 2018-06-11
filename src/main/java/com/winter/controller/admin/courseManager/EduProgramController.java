package com.winter.controller.admin.courseManager;

import com.winter.model.education_program;
import com.winter.model.major;
import com.winter.model.modelUtil.EduProWithMajor;
import com.winter.services.EducationProgramService;
import com.winter.services.MajorService;
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

@Controller
@RequestMapping(value = "/admin")
public class EduProgramController {

    @Autowired
    EducationProgramService educationProgramService;

    @Autowired
    MajorService majorService;

    /**
     * 返回专业培养方案主页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "eduprograme")
    public String eduProIndex(Model model) {
        //返回专业填充下拉框
        List<major> mojList = majorService.selectAll();
        model.addAttribute("typeList", mojList);
        return "admin/coursemanager/eduprograme";
    }

    @RequestMapping(value = "eduprogrameget")
    @ResponseBody
    public Map<String, Object> eduProgrameGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums) {
        //返回培养方案和专业list集合
        List<education_program> eduList = educationProgramService.selectBypage(curr, nums);

        List<EduProWithMajor> retList =getEduProWithMajor(eduList);

        int countNum = educationProgramService.selectCount();

        return LayUItableResponse.addData(0, "", countNum, retList);
    }

    @RequestMapping(value = "eduproadd")
    @ResponseBody
    public String eduProAdd(@ModelAttribute education_program model) {
        String result= ResponseCode.error;
        //判断名称不为空
        if (!model.getName().equals("")){
            model.setCreatetime(new Date());
            model.setEducationprogramid(StringUtil.getUUID());

            educationProgramService.insert(model);

            result=ResponseCode.success;
        }
        return result;
    }

    /**
     * 获取培养方案和专业的列表
     *
     * @param list
     * @return
     */
    private List<EduProWithMajor> getEduProWithMajor(List<education_program> list) {
        List<EduProWithMajor> resList = new ArrayList<>();
        for (education_program item :
                list) {
            EduProWithMajor obj = new EduProWithMajor();//数据处理
            String majorname = majorService.selectByPrimaryKey(item.getMajorid()).getMajorname();

            obj.setMajorname(majorname);

            obj.setCreatetime(item.getCreatetime());
            obj.setDegree(item.getDegree());
            obj.setDuration(item.getDuration());
            obj.setEducationprogramid(item.getEducationprogramid());
            obj.setMajorid(item.getMajorid());
            obj.setMincredit(item.getMincredit());
            obj.setName(item.getName());
            obj.setObjective(item.getObjective());
            obj.setPublishyear(item.getPublishyear());
            obj.setSpecification(item.getSpecification());

            resList.add(obj);
        }
        return resList;
    }
}
