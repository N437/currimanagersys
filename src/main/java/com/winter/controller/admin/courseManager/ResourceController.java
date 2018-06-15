package com.winter.controller.admin.courseManager;

import com.winter.model.*;
import com.winter.model.modelUtil.ResWithCourse;
import com.winter.services.CourseService;
import com.winter.services.CurriculumService;
import com.winter.services.MajorService;
import com.winter.services.ResourceService;
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

    /**
     * 获取资源列表
     *
     * @param curr
     * @param nums
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resourceget")
    public Map<String, Object> resourceGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums) {

        int countNum = resourceService.selectCount();

        List<resource> resourceList = resourceService.selectBypage(curr, nums);
        //添加课程名称
        List<ResWithCourse> retList = getResourceWithCourse(resourceList);

        return LayUItableResponse.addData(0, "", countNum, retList);
    }

    /**
     * 添加资源，上传文件
     *
     * @param model
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "resourceadd")
    @ResponseBody
    public String addResource(@ModelAttribute resource model, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String result = ResponseCode.error;

        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        if (FileUtil.isFileRight(fileName)) {
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
            model.setResourceurl(filePath + "/" + fileName);

            resourceService.insert(model);
            result = ResponseCode.success;
        }
        //返回json
        return result;
    }

    /**
     * 获取资源以及课程名称
     *
     * @param list
     * @return
     */
    private List<ResWithCourse> getResourceWithCourse(List<resource> list) {
        List<ResWithCourse> retList = new ArrayList<>();
        for (resource item :
                list) {
            ResWithCourse obj = new ResWithCourse();

            String courseName = courseService.selectByPrimaryKey(item.getCourseid()).getCoursename();

            obj.setCoursename(courseName);
            obj.setCreatetime(item.getCreatetime());
            obj.setResourceid(item.getResourceid());
            obj.setResourceurl(item.getResourceurl());
            obj.setCourseid(item.getCourseid());
            obj.setName(item.getName());

            retList.add(obj);
        }

        return retList;
    }
}
