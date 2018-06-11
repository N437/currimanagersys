package com.winter.utils;

import java.util.HashMap;
import java.util.Map;

public class LayUItableResponse {

    public static Map<String,Object> addData(int code,String msg,int count,Object data){
        Map<String,Object> obj=new HashMap<>();
        obj.put("code",code);
        obj.put("msg",msg);
        obj.put("count",count);
        obj.put("data",data);
        return obj;
    }
}
