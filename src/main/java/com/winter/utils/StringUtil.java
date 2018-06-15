package com.winter.utils;

import com.winter.services.MajorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class StringUtil {

    @Autowired
    MajorService majorService;

    public final static String winUserSession = "winUserSession";

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
     *
     * @param sourceDate
     * @param formatLength
     * @return 重组后的数据
     */
    public static String frontCompWithZore(int sourceDate, int formatLength) {
        /*
         * 0 指前面补充零
         * formatLength 字符总长度为 formatLength
         * d 代表为正数。
         */
        String newString = String.format("%0" + formatLength + "d", sourceDate);
        return newString;
    }

}
