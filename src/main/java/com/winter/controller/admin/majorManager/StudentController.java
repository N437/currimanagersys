package com.winter.controller.admin.majorManager;

import com.winter.model.classes;
import com.winter.model.major;
import com.winter.model.modelUtil.StudentWithMajor;
import com.winter.model.student;
import com.winter.services.ClassesService;
import com.winter.services.MajorService;
import com.winter.services.StudentService;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class StudentController {

    @Autowired
    MajorService majorService;

    @Autowired
    StudentService studentService;

    @Autowired
    ClassesService classesService;

    @RequestMapping(value = "student")
    public String studentIndex(Model model) {

        //获取获取专业和班级的二级联动，首先返回专业
        List<major> majorList = majorService.selectAll();

        //获取班级列表
        model.addAttribute("typeList",majorList);
        return "admin/majormanager/studentindex";
    }

    @RequestMapping(value = "studentget")
    @ResponseBody
    public Map<String,Object> getStudent(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
        int countNum = studentService.selectCount();
        List<student> stuList = studentService.selectBypage(curr, nums);
        //获取学生的班级和专业
        List<StudentWithMajor> retList = getStudentList(stuList);
        return LayUItableResponse.addData(0,"",countNum,retList);
    }

    /**
     * 添加学生
     * @param model
     * @return
     */
    @RequestMapping(value = "studentadd")
    @ResponseBody
    public String studentAdd(@RequestParam("file") MultipartFile file, @ModelAttribute student model, HttpServletRequest request){
        //处理上传文件
        //将学生添加到用户表中
        //学生的学号自动获取？算了不管了
        String result = ResponseCode.error;

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");

        return result;
    }

    /**
     * 获取学生的班级专业信息列表
     * @param list
     * @return
     */
    private List<StudentWithMajor> getStudentList(List<student> list) {
        List<StudentWithMajor> resList = new ArrayList<>();
        for (student item :
                list) {
            StudentWithMajor obj = new StudentWithMajor();//数据处理
            classes thisclasses = classesService.selectByPrimaryKey(item.getClassid());
            major thismajor = majorService.selectByPrimaryKey(thisclasses.getMajorid());
            String classname = thisclasses.getClassname();
            String majorname = thismajor.getMajorname();

            obj.setStudentid(item.getStudentid());
            obj.setStudentname(item.getStudentname());
            obj.setStudentphoto(item.getStudentphoto());
            obj.setStudentscode(item.getStudentscode());
            obj.setStudentgender(item.getStudentgender());
            obj.setClassid(item.getClassid());
            obj.setClassname(classname);
            obj.setMajorname(majorname);
            resList.add(obj);
        }
        return resList;
    }

}
