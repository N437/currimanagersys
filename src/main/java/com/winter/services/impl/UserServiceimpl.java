package com.winter.services.impl;

import com.winter.mapper.UserMapper;
import com.winter.model.User;
import com.winter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceimpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User findById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAll() {

        return userMapper.selectAll();
    }
}
