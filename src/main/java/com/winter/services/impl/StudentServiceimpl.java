package com.winter.services.impl;

import com.winter.model.student;
import com.winter.services.StudentService;
import org.springframework.stereotype.Service;

@Service(value = "StudentService")
public class StudentServiceimpl extends BaseServiceimpl<student> implements StudentService {
}
