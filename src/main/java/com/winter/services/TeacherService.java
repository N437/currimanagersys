package com.winter.services;

import com.winter.model.teacher;

public interface TeacherService extends BaseService<teacher> {

    teacher selectByTcode(String code);
}
