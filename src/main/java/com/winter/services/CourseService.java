package com.winter.services;

import com.winter.model.course;
import org.springframework.stereotype.Service;

@Service(value = "courseService")
public interface CourseService extends BaseService<course> {
}
