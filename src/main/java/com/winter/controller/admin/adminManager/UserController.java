package com.winter.controller.admin.adminManager;

import com.winter.model.modelUtil.UserWithRole;
import com.winter.model.roleinfo;
import com.winter.model.userinfo;
import com.winter.services.RoleInfoService;
import com.winter.services.UserInfoService;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户管理，包括用户和用户的角色添加
 */
@Controller
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    RoleInfoService roleInfoService;

    @RequestMapping(value = "user")
    public String userindex(Model model){
        //返回角色信息，填充下拉框
        List<roleinfo> roleList = roleInfoService.selectAll();
        model.addAttribute("typeList",roleList);
        return "admin/adminmanager/usermanager";
    }

    @RequestMapping(value = "userget")
    @ResponseBody
    public Map<String,Object> userGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums){
        int countNum = userInfoService.selectCount();

        List<userinfo> userList= userInfoService.selectBypage(curr, nums);

        List<UserWithRole> retList = getUserWithRole(userList);

        return LayUItableResponse.addData(0,"",countNum,retList);

    }

    @RequestMapping(value = "useradd")
    @ResponseBody
    public String userAdd(@ModelAttribute userinfo model){
        String result = ResponseCode.error;

        if (!model.getUserid().equals("") && !model.getUserpwd().equals("")){
            model.setUserinfoid(StringUtil.getUUID());
            model.setCreatetime(new Date());

            userInfoService.insert(model);

            result=ResponseCode.success;
        }

        return result;
    }

    /**
     * 获取用户和用户对应的角色
     * @param courList
     * @return
     */
    private List<UserWithRole> getUserWithRole(List<userinfo> List) {
        List<UserWithRole> resList = new ArrayList<>();
        for (userinfo item :
                List) {
            UserWithRole obj = new UserWithRole();//数据处理
            String name = roleInfoService.selectByPrimaryKey(item.getUserrole()).getRolename();

            obj.setRolename(name);
            obj.setCreatetime(item.getCreatetime());
            obj.setUserid(item.getUserid());
            obj.setUserinfoid(item.getUserinfoid());
            obj.setUserpwd(item.getUserpwd());
            obj.setUserrole(item.getUserrole());

            resList.add(obj);
        }
        return resList;
    }
}
