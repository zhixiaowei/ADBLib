package com.adb.process.android.logcat;

import com.adb.command.andriodCmd.AndroidLogcatCmd;
import com.adb.process.AndroidCtrl;
import com.adb.process.android.IAndroid;

import java.io.IOException;

public class AndroidLogcat extends IAndroid {

    private AndroidLogcatCmd cmd = new AndroidLogcatCmd();

    public AndroidLogcat(AndroidCtrl context) {
        super(context);
    }

    /**
     * 在命令行窗口正常
     * adb logcat -v time > [path]
     *
     * （小米）Android 10测试无效
     *暂时没有找到无法正常执行的原因
     * @param path
     */
    @Deprecated
    public void save2WindowFile(String path){
        try{
            context.exec(cmd.logcat2WindowFile(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 为了替代adb指令无法正常指令的方案。
     * @param isPrint 保存的同时是否打印输出
     * @param path 保存路径
     * @param grep 日志关键字过滤
     */
    public void save2WindowFile2(boolean isPrint,String path,String grep){
        try{
            context.execASave(cmd.logcat(grep),path,isPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存日志到Android设备
     * @param path 文件完整路径
     * @param tag 日志过滤的TAG
     */
    public void save2AndroidFile(String path,String tag){
        try {
            context.exec(cmd.logcat2AndroidFile(path, tag));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 打印日志
     * @param grep 过滤，为空时不过滤
     */
    public void print(String grep){
        try {
            context.execAPrint(cmd.logcat(grep));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据PID打印日志和可以配合getPid使用
     * @param packageName
     */
    public void printByPid(String packageName){
        try {
            context.execAPrint(cmd.logcatByPid(packageName));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void print(LogcatConfig config){
        try {
            context.execAPrint(config.cmd,config.grep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save2WindowFile(LogcatConfig config,String path,boolean isPrint){
        try {
            context.execASave(config.cmd,path,isPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}