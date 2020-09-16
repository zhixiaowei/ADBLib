package com.adb.process.android;

import com.adb.process.AndroidCtrl;
import com.adb.command.andriodCmd.AndroidFileCmd;

import java.io.IOException;

public class AndroidFile extends IAndroid {

    AndroidFileCmd cmd = new AndroidFileCmd();

    public AndroidFile(AndroidCtrl context) {
        super(context);
    }

    /**
     * Android 10测试可行
     *
     * 拷贝文件从Android到Window
     * @param androidPath
     * @param windowPath
     * @return
     */
    public String copyFileAndroid2Window(String androidPath,String windowPath){
        try {
            return context.exec(cmd.copyFileAndroid2Window(androidPath, windowPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android 10亲测可行
     *
     * 拷贝文件从Window到Android
     * @param androidPath
     * @param windowPath
     * @return
     */
    public String copyFileWindow2Android(String androidPath,String windowPath){
        try {
            return context.exec(cmd.copyFileWindow2Android(androidPath, windowPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android 10测试可行
     *
     * 读取终端文本文件，读取其他的就基本是乱码
     * @param path
     * @return
     */
    public String readTextFile(String path){
        try {
            return context.exec(cmd.readTextFile(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android 10 亲测可行 测试地址为根目录：/storage/emulated/0/
     *
     * 获取文件夹目录
     * @return
     */
    public String listDir(String path){
        try {
            return context.exec(cmd.listDir(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android亲测可行，测试地址为：/storage/emulated/0/新建文件夹（里面有一个文件为 1.png
     *
     * 删除文件夹
     * @param isDelSelf 是否删除自身，为false时，只删除文件夹下的所有文件
     * @return
     */
    public String deleteDir(boolean isDelSelf,String path){
        try {
            return context.exec(cmd.delDir(isDelSelf,path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android 10测试可行 测试地址为：/storage/emulated/0/新建文件夹，删除条件为 .log
     *
     * 删除文件夹下的指定后缀的文件
     * @param dir
     * @param suffix
     * @return
     */
    public String delDirFileBySuffix(String dir,String suffix){
        try {
            return context.exec(cmd.delDirFileBySuffix(dir, suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
