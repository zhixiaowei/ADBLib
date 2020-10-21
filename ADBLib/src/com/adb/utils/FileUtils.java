package com.adb.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class FileUtils {

    /**
     * 返回当前项目的地址（如果为库，则为该库地址）
     *
     * 如果为jar包，则返回jar包的地址
     * 如果未编译为Jar包，则返回当前库（JBaseLib）的地址
     *
     */
    public static String getLocalPath(String chartsName){

        String path = FileUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (chartsName == null||chartsName.isEmpty()){
            chartsName = "UTF-8";
        }
        try {
            return URLDecoder.decode(path,chartsName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return path;
    }

    /**
     * 删除文件（夹）
     * @param path
     * @return
     */
    public static void delete(String path){
        File file = new File(path);

        if (file.exists()){
            if (file.isDirectory()){

                File[] list = file.listFiles();
                for (File f:list){
                    delete(f.getAbsolutePath());
                }
            }

            file.delete();
        }
    }

}
