package com.winter.mapper;

import com.winter.model.userinfo;

public interface userinfoMapper extends BaseMapper<userinfo> {
    userinfo selectByUserid(String userId);
}