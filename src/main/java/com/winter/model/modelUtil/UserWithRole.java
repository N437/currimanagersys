package com.winter.model.modelUtil;

import com.winter.model.userinfo;

public class UserWithRole extends userinfo {
    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    private String rolename;
}
