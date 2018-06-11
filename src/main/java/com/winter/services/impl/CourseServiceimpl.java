package com.winter.services.impl;

import com.winter.model.course;
import com.winter.services.CourseService;
import org.springframework.stereotype.Service;

@Service(value = "courseService")
public class CourseServiceimpl extends BaseServiceimpl<course> implements CourseService {
}
