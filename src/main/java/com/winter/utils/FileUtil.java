package com.winter.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+"/"+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 判断文件的类型
     * @param fileName
     * @return
     */
    public static Boolean isFileRight(String fileName) {
        Boolean result = false;
        //数据存于数据字典中，此处直接省略
        List<String> includefile = new ArrayList<>();

        includefile.add("png");
        includefile.add("jpg");
        includefile.add("gif");
        includefile.add("jpeg");

        String[] uploadFile = fileName.split("\\.");

        String name = "";
        String ext = "";
        if (uploadFile.length>1){
            name = uploadFile[0];
            ext = uploadFile[1];
        }

        if (includefile.contains(ext)) {
            result = true;
        }
        return result;
    }

    /**
     * 替换文件的名字
     * @param sourName
     * @param newName
     * @return
     */
    public static String reSetFileName(String sourName,String newName){
        String[] uploadFile = sourName.split("\\.");

        String name = "";
        String ext = "";
        if (uploadFile.length>1){
            name = uploadFile[0];
            ext = uploadFile[1];
        }
        name = newName;
        return name + ext;
    }
}
