package com.adb.sample.android;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.logcat.AndroidLogcat;
import com.adb.process.android.logcat.LogcatConfig;
import com.adb.process.android.logcat.LogcatConfigBuilder;

public class AndroidLogcatSample {
    public static void main(String[] args) {

        AndroidCtrl android = new AndroidCtrl();
        AndroidLogcat logcat = android.managerOfLogcat();

        String pid = android.managerOfApp().getPid("com.huangxiaowei.annotationtest");

        //将日志中TAG为"Test"的日志保存至设备
        logcat.save2AndroidFile("/mnt/sdcard/log.txt",pid);

       //将日志保存至window
        logcat.save2WindowFile2(true,"E:\\日志.log",pid);

        //输出日志，过滤字为"Test"
        logcat.print("Test");

        //配合进程ID过滤日志
        logcat.printByPid(pid);//将Pid作为过滤条件


        //多参数日志过滤
        LogcatConfig config = new LogcatConfigBuilder()
                .filterTAG("BindView")//设置TAG
                .showFormat(LogcatConfig.FORMAT_BRIEF)//输出格式
                .filterLevel(LogcatConfig.LEVEL_W)//设置输出的日志等级
                .filter("3")//关键字过滤
                .build();

        logcat.print(config);


    }
}
