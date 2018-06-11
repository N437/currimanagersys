package com.winter.services;

import com.winter.model.User;

import java.util.List;

public interface UserService {

     User findById(int id);

     List<User> selectAll();
}
