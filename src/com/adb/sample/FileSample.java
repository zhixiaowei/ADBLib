package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidFile;

/**
 * 文件操作
 */
public class FileSample {

    public static void main(String[] args) {
        AndroidFile fileCtrl = new AndroidCtrl().managerOfFile();

        //从Android拷贝文件到电脑
        fileCtrl.copyFileAndroid2Window("/mnt/sdcard/image.png","E:\\Download\\image.png");

        //从电脑端拷贝文件到Android
        fileCtrl.copyFileWindow2Android("/mnt/sdcard/image.png","E:\\Download\\image.png");

        //读取Android端的文件文件
        String text = fileCtrl.readTextFile("/mnt/sdcard/log.txt");

        //获取文件夹下的文件列表
        String list = fileCtrl.listDir("/mnt/sdcard/");

        //删除文件夹下以.xml 为结尾的文件
        fileCtrl.delDirFileBySuffix("/mnt/sdcard/",".xml");

        //删除文件夹及文件夹下的所有文件
        fileCtrl.deleteDir(true,"/mnt/sdcard/image/");
    }

}
