package com.winter.controller.admin.majorManager;

import com.winter.services.ClassesService;
import com.winter.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    ClassesService classesService;

    @RequestMapping(value = "")
    public String studentIndex(Model model) {

        //获取班级列表
        return "";
    }

}
