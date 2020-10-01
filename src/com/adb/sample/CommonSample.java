package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.AndroidSystem;
import com.adb.process.android.KeyCode;
import com.adb.process.android.context.Activity;

public class CommonSample {
    public static void main(String[] args) {

        //如果已经配置过ADB环境变量，则可以
        ADBCtrl adb = new ADBCtrl();

        //在未配置ADB环境变量的情况下可以通过调用
        ADBCtrl adb1 = new ADBCtrl("E:\\tool\\adbDir\\");

        adb.setCharset("GBK");//设置编码格式，默认为GBK，可不设置
        adb.isPrintCmd(true);//是否打印执行的命令

        //获取已连接的设备
        Device[] device = adb.listDevices();

        //当然如果我们确定连接的设备只有一个，也可以直接调用
        Device device1 = adb.firstDevice();

        //点击指定坐标
        device1.click(230,449);

        //滑动
        device1.swipe(230,449,200,440);

        device1.inputKeyEvent(KeyCode.KEYCODE_BACK);//执行返回键

        AndroidSystem system = device1.managerOfSystem();
        system.listProcess(null);//获取系统进程等
        //详情可看 com.adb.sample.AndroidSystemSample

        Activity activity = device1.managerOfActivity();
        activity.start("com.hxw.test");
        //详情可看 com.adb.sample.ActivitySample

        //其他API可阅其他Sample
    }
}
