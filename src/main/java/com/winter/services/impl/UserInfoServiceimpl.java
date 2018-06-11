package com.winter.services.impl;

import com.winter.mapper.userinfoMapper;
import com.winter.model.userinfo;
import com.winter.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userinfoService")
public class UserInfoServiceimpl extends BaseServiceimpl<userinfo> implements UserInfoService {

    @Autowired
    userinfoMapper userinfoMapper;

    @Override
    public userinfo selectByUserid(String userId) {
        return userinfoMapper.selectByUserid(userId);
    }
}
