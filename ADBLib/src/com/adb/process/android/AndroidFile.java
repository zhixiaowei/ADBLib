package com.adb.process.android;

import com.adb.command.android.AndroidFileCmd;
import com.adb.process.Device;

import java.io.IOException;

public class AndroidFile extends IAndroid {

    AndroidFileCmd cmd = new AndroidFileCmd();

    public AndroidFile(Device context) {
        super(context);
    }

    /**
     * 获取sdcard目录
     * @return
     * 在真机上测试返回结果为：/sdcard
     */
    public String getExternalStorageDirectoryPath(){
        try {
            return context.exec(cmd.getExternalStorageDirectoryPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Android 10测试可行
     *
     * 拷贝文件从Android到Window
     * @param androidPath
     * @param windowPath
     * @return 正常情况，执行成功内容为：/sdcard/pic.png: 1 file pulled. 10.0 MB/s (219974 bytes in 0.021s)
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
     * @return 正常的情况下，执行成功后无回复内容
     *
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
