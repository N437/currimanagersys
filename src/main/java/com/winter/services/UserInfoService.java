package com.winter.services;

import com.winter.model.userinfo;

public interface UserInfoService extends BaseService<userinfo> {
    userinfo selectByUserid(String userId);

}
