package com.winter.controller.admin.majorManager;

import com.winter.model.classes;
import com.winter.model.major;
import com.winter.model.modelUtil.ClassesWithMajor;
import com.winter.services.ClassesService;
import com.winter.services.MajorService;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级管理，包括专业管理
 */
@Controller
@RequestMapping(value = "/admin")
public class ClassesController {

    @Autowired
    ClassesService classesService;

    @Autowired
    MajorService majorService;

    @RequestMapping(value = "classes")
    public String classIndex(Model model) {
        //返回专业数据
        List<major> mojList = majorService.selectAll();
        model.addAttribute("typeList", mojList);
        return "admin/majormanager/classindex";
    }

    /**
     * 获取班级信息，班级信息包括专业信息
     *
     * @param curr
     * @param nums
     * @return
     */
    @RequestMapping(value = "classesget")
    @ResponseBody
    public Map<String, Object> classesGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums) {
        //获取班级列表
        List<classes> classList = classesService.selectBypage(curr, nums);
        int countNum = classesService.selectCount();

        List<ClassesWithMajor> retList = getClassWithMajor(classList);

        return LayUItableResponse.addData(0, "", countNum, retList);
    }

    /**
     * 添加专业
     *
     * @param majorname
     * @return
     */
    @RequestMapping(value = "majoradd")
    @ResponseBody
    public String majorAdd(@RequestParam("majorname") String majorname) {
        String result = ResponseCode.error;
        if (!majorname.equals("")) {
            major model = new major();
            model.setCreatetime(new Date());
            model.setMajorid(StringUtil.getUUID());
            model.setMajorname(majorname);

            majorService.insert(model);

            result = ResponseCode.success;
        }
        return result;
    }

    /**
     * 添加班级
     * @param model
     * @return
     */
    @RequestMapping(value = "classesadd")
    @ResponseBody
    public String classesAdd(@ModelAttribute classes model) {
        String result = ResponseCode.error;

        //添加班级
        if (!model.getClassname().equals("")){
            model.setClassid(StringUtil.getUUID());

            classesService.insert(model);

            result=ResponseCode.success;
        }
        return result;
    }

    /**
     * 获取班级和专业的列表
     *
     * @param list
     * @return
     */
    private List<ClassesWithMajor> getClassWithMajor(List<classes> list) {
        List<ClassesWithMajor> resList = new ArrayList<>();
        for (classes item :
                list) {
            ClassesWithMajor obj = new ClassesWithMajor();//数据处理
            String majorname = majorService.selectByPrimaryKey(item.getMajorid()).getMajorname();

            obj.setClassid(item.getClassid());
            obj.setClassname(item.getClassname());
            obj.setEnrollyear(item.getEnrollyear());
            obj.setMajorid(item.getMajorid());
            obj.setMajorname(majorname);
            resList.add(obj);
        }
        return resList;
    }
}
