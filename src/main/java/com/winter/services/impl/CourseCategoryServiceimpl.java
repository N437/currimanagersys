package com.winter.services.impl;

import com.winter.model.course_category;
import com.winter.services.CourseCategoryService;
import org.springframework.stereotype.Service;

@Service(value = "courseCategoryService")
public class CourseCategoryServiceimpl extends BaseServiceimpl<course_category> implements CourseCategoryService {
}
