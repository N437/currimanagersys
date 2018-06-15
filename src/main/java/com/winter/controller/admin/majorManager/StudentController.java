package com.winter.controller.admin.majorManager;

import com.winter.model.classes;
import com.winter.model.major;
import com.winter.model.modelUtil.StudentWithMajor;
import com.winter.model.student;
import com.winter.model.userinfo;
import com.winter.services.ClassesService;
import com.winter.services.MajorService;
import com.winter.services.StudentService;
import com.winter.services.UserInfoService;
import com.winter.utils.FileUtil;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class StudentController {

    @Autowired
    MajorService majorService;

    @Autowired
    StudentService studentService;

    @Autowired
    ClassesService classesService;

    @Autowired
    UserInfoService userInfoService;

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
        //学生的学号自动获取？算了不管了直接 入学年份加随机两位加后面的三位人数
        String result = ResponseCode.error;

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        if (FileUtil.isFileRight(fileName)) {

            String studentCode = getStudentCode(model);

            String filePath = "";
            try {
                filePath = ResourceUtils.getURL("static/userfile/studentphoto/"+studentCode).getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                //fileName = FileUtil.reSetFileName(fileName,)
                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                // TODO: handle exception
            }

            //写入数据库
            model.setStudentid(StringUtil.getUUID());
            model.setStudentscode(studentCode);
            model.setStudentphoto(filePath + "/" +fileName);

            studentService.insert(model);

            //写入用户表
            userinfo usermodel = new userinfo();
            usermodel.setCreatetime(new Date());
            usermodel.setUserid(studentCode);
            usermodel.setUserinfoid(StringUtil.getUUID());
            usermodel.setUserpwd("123456");
            usermodel.setUserrole("c7f02d4b156b4aceb9e004361139bb1a");

            userInfoService.insert(usermodel);

            result = studentCode;
        }
        //返回json
        return result;
    }

    /**
     * 三级联动，根据专业获取班级
     * @param id
     * @return
     */
    @RequestMapping(value = "getclassbymajor")
    @ResponseBody
    public Map<String, String> getClassByMajor(@RequestParam("id") String id) {
        //根据curriculumid从 curriculum 表中查询到所有的课程
        List<classes> list = classesService.selectByMajorId(id);
        Map<String, String> retMap = new HashMap<>();

        for (classes item :
                list) {
//            classes model = classesService.selectByPrimaryKey(item.getClassid());
            retMap.put(item.getClassid(), item.getClassname());
        }

        return retMap;
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


    /**
     * 创建学生的学号
     * @param model
     * @return
     */
    public String getStudentCode(student model){

        classes classesmodel =classesService.selectByPrimaryKey(model.getClassid());

        String year = classesmodel.getEnrollyear().toString();
        String majorCode = majorService.selectByPrimaryKey(classesmodel.getMajorid()).getMajorcode();

        String count = StringUtil.frontCompWithZore(studentService.selectCount(),3);

        return year+majorCode+count;
    }
}
