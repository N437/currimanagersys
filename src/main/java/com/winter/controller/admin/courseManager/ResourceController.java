package com.winter.controller.admin.courseManager;

import com.winter.model.*;
import com.winter.services.CourseService;
import com.winter.services.CurriculumService;
import com.winter.services.MajorService;
import com.winter.services.ResourceService;
import com.winter.utils.FileUtil;
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
public class ResourceController {

    @Autowired
    CourseService courseService;

    @Autowired
    MajorService majorService;

    @Autowired
    CurriculumService curriculumService;

    @Autowired
    ResourceService resourceService;

    /**
     * 三级联动 ：专业-》课程体系-》课程
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "resourse")
    public String resourceIndex(Model model) {
        //返回专业列表填充
        List<major> mojList = majorService.selectAll();
        model.addAttribute("typeList", mojList);
        return "admin/coursemanager/resource";
    }

    /**
     * 三级联动，根据培养方案获取课程名称
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getcoursebyedupro")
    public Map<String, String> getCourseByCurri(@RequestParam("id") String id) {
        //根据curriculumid从 curriculum 表中查询到所有的课程
        List<curriculum> currList = curriculumService.selectByEduProId(id);
        Map<String, String> retMap = new HashMap<>();

        for (curriculum item :
                currList) {
            course model = courseService.selectByPrimaryKey(item.getCourseid());
            retMap.put(model.getCourseid(), model.getCoursename());
        }

        return retMap;
    }

    @RequestMapping(value = "resourceadd")
    @ResponseBody
    public String addResource(@ModelAttribute resource model, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String result = ResponseCode.error;

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        if (isFileRight(fileName)) {
            String filePath = "";
            try {
                filePath = ResourceUtils.getURL("static/userfile/tmp").getPath();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                //将文件以
                filePath += "/" + model.getName();

                FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            } catch (Exception e) {
                // TODO: handle exception
            }
            //写入数据库
            model.setResourceid(StringUtil.getUUID());
            model.setCreatetime(new Date());
            model.setResourceurl(filePath + "/" +fileName);

            resourceService.insert(model);
            result = ResponseCode.success;
        }
        //返回json
        return result;
    }

    /**
     * 判断文件的类型
     * @param fileName
     * @return
     */
    private Boolean isFileRight(String fileName) {
        Boolean result = false;

        //数据存于数据字典中，此处直接省略
        String[] includeUrls = new String[]{"png", "jpg", "gif"};

        List<String> includefile = new ArrayList<>();

        includefile.add("png");
        includefile.add("jpg");
        includefile.add("gif");
        includefile.add("jpeg");

//        String trim =fileName.replaceAll(" ","");

        String[] uploadFile = fileName.split("\\.");

        String name = "";
        String ext = "";
        if (uploadFile.length>1){
            name = uploadFile[0];
            ext = uploadFile[1];
        }

        if (includefile.contains(ext)) {
            result = true;
        }
        return result;
    }

}