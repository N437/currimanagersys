package com.winter.services.impl;

import com.winter.model.course_class;
import com.winter.services.CourseClassService;
import org.springframework.stereotype.Service;

@Service(value = "CourseClassService")
public class CourseClassServiceimpl extends BaseServiceimpl<course_class> implements CourseClassService {
}
