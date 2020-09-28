package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidSystem;
import com.adb.process.android.context.Activity;

public class CommonSample {
    public static void main(String[] args) {

        AndroidCtrl androidCtrl = new AndroidCtrl();

        androidCtrl.setCharset("GBK");//设置编码格式，默认为GBK
        androidCtrl.isPrintCmd(true);//是否打印执行的命令

        //获取已连接设备
        String devices = androidCtrl.listDevices();

        //点击指定坐标
        androidCtrl.click(230,449);

        //滑动
        androidCtrl.swipe(230,449,200,440);

        AndroidSystem system = androidCtrl.managerOfSystem();
        system.listProcess(null);//获取系统进程等
        //详情可看 com.adb.sample.AndroidSystemSample

        Activity activity = androidCtrl.managerOfActivity();
        activity.start("com.hxw.test");
        //详情可看 com.adb.sample.ActivitySample

        //其他API可阅其他Sample
    }
}
