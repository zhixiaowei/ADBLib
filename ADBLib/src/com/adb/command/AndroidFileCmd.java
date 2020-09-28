package com.adb.command;

public class AndroidFileCmd {
    /**
     * 获取文件夹目录
     * @param path
     * @return
     */
    public static String listDir(String path){
        return "adb shell ls "+path;
    }

    /**
     *
     * @param isDelSelf 是否删除自身，为false的清空下仅删除文件夹下所有文件
     * @return
     */
    public static String delDir(boolean isDelSelf,String path){
        if(isDelSelf){
            return "adb shell rm -r "+path;
        }else{
            return "adb shell rm "+path+"/*.*";
        }
    }

    /**
     * 删除文件夹下符合后缀的文件
     * @param dir
     * @param suffix
     * @return
     */
    public static String delDirFileBySuffix(String dir,String suffix){
        if (suffix == null||suffix.trim().isEmpty()){
            suffix = "";
        }

        return "adb shell rm "+dir+"/*"+suffix;
    }


    /**
     * 拷贝文件从Android到Window
     * @param androidPath
     * @param windowPath
     * @return
     */
    public static String copyFileAndroid2Window(String androidPath,String windowPath){
        return "adb pull "+androidPath+" "+windowPath;
    }

    /**
     * 拷贝文件从Window到Android
     * @param androidPath
     * @param windowPath
     * @return
     */
    public static String copyFileWindow2Android(String androidPath,String windowPath){
        return "adb push "+windowPath+" "+androidPath;
    }

    /**
     * 读取终端文本文件，读取其他的就基本是乱码
     * @param path
     * @return
     */
    public static String readTextFile(String path){
        return "adb shell cat "+path;
    }


}
