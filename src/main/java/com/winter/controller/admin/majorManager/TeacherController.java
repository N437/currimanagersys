package com.winter.controller.admin.majorManager;

import com.winter.model.dictionary;
import com.winter.model.modelUtil.TeacherWithInfo;
import com.winter.model.teacher;
import com.winter.model.userinfo;
import com.winter.services.DictionaryService;
import com.winter.services.TeacherService;
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
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    DictionaryService dictionaryService;

    //字典表：学位
    String degreeTypeId="7a0e855c99324f0ebc3c3856ad01941e";

    //字典表：职称
    String professionalTitle="e8c1c80e6a4f4d97bcc076340779356b";

    @RequestMapping(value = "teacher")
    public String teacherIndex(Model model){
        //获取学位和职称列
        List<dictionary> degList = dictionaryService.selectByType(degreeTypeId);

        List<dictionary> proList = dictionaryService.selectByType(professionalTitle);

        model.addAttribute("degList",degList);
        model.addAttribute("proList",proList);
        return "admin/majormanager/teacherindex";
    }

    @RequestMapping(value = "teacherget")
    @ResponseBody
    public Map<String, Object> teacherGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
        //获取教师的资料直接返回
        int countNum = teacherService.selectCount();
        List<teacher> teacherList = teacherService.selectBypage(curr, nums);
        List<TeacherWithInfo> retList = getTeacherWithInfo(teacherList);
        return LayUItableResponse.addData(0,"",countNum,retList);
    }

    @ResponseBody
    @RequestMapping(value = "teacheradd")
    public String teacherAdd(@RequestParam("file") MultipartFile file, @ModelAttribute teacher model, HttpServletRequest request){
        String result = ResponseCode.error;
        //添加教师数据，添加文件，添加用户表
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        if (FileUtil.isFileRight(fileName)) {

            String teacherCode = getTeacherCode();

            String filePath = "";
            try {
                filePath = ResourceUtils.getURL("static/userfile/teacherphoto/"+teacherCode).getPath();
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
            model.setTeacherid(StringUtil.getUUID());
            model.setTeachertcode(teacherCode);
            model.setTeacherphoto(filePath + "/" +fileName);

            teacherService.insert(model);

            //写入用户表
            userinfo usermodel = new userinfo();
            usermodel.setCreatetime(new Date());
            usermodel.setUserid(teacherCode);
            usermodel.setUserinfoid(StringUtil.getUUID());
            usermodel.setUserpwd("123456");
            usermodel.setUserrole("5c9ac4be42bc40cab5690496cfe3c161");

            userInfoService.insert(usermodel);

            result = teacherCode;
        }


        return result;
    }

    /**
     * 获取教师和学位职称
     * @param list
     * @return
     */
    private List<TeacherWithInfo> getTeacherWithInfo(List<teacher> list) {
        List<TeacherWithInfo> retList = new ArrayList<>();
        for (teacher item :
                list) {
            String degreeName = dictionaryService.selectByPrimaryKey(item.getTeacherdegree()).getDictionaryname();
            String titleName = dictionaryService.selectByPrimaryKey(item.getTeachertitle()).getDictionaryname();

            TeacherWithInfo obj = new TeacherWithInfo();

            obj.setDegreename(degreeName);
            obj.setTitlename(titleName);
            obj.setTeacherdegree(item.getTeacherdegree());
            obj.setTeachergender(item.getTeachergender());
            obj.setTeacherid(item.getTeacherid());
            obj.setTeacherintro(item.getTeacherintro());
            obj.setTeachername(item.getTeachername());
            obj.setTeacherphoto(item.getTeacherphoto());
            obj.setTeachertcode(item.getTeachertcode());
            obj.setTeachertitle(item.getTeachertitle());

            retList.add(obj);
        }
        return retList;
    }

    /**
     * 获取教师编号 年份前三位加人数四位
     * @return
     */
    private String getTeacherCode() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));

        String trim = year.substring(1, year.length());
        String count = StringUtil.frontCompWithZore(teacherService.selectCount(),3);
        return trim+count;
    }
}
