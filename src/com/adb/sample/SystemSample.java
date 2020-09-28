package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidSystem;
import com.adb.process.android.KeyCode;

public class SystemSample {

    public static void main(String[] args) {
        AndroidCtrl context = new AndroidCtrl();
        AndroidSystem system = new AndroidSystem(context);

        system.location();//位置信息

        system.date();//系统时间

        system.devBrand();//品牌

        system.devIMEI();//IMEI

        system.devModel();//型号

        system.SDKVersion();//SDK版本

        system.SystemVersion();//系统版本

        system.listProcess(null);//获取当前正在执行的进程

        system.inputKeyEvent(KeyCode.KEYCODE_BACK);//执行返回键

    }
}
