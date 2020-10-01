package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.AndroidAPP;

import java.io.IOException;

public class APPSample {
    public static void main(String[] args) throws IOException {

        Device device = new ADBCtrl().firstDevice();
        if (device == null){
            System.out.println("找不到连接的设备");
        }

        AndroidAPP appCtrl = device.managerOfApp();

        //获取APP列表
        String list = appCtrl.listApp();

        System.out.println(list);

        //卸载应用
        boolean uninstallResult = appCtrl.uninstall("com.taobao.idlefish");

        //安装应用，非debug签名的apk，如果已经安装，则重新安装
        boolean installResult = appCtrl.install("E:\\BaiduNetdiskDownload\\FiddlerSetup.apk",false,true);

        //强制停止应用
        appCtrl.stopApp("com.taobao.idlefish");

        //读取应用的信息
        String msg = appCtrl.readAppInfo("com.xiaomi.youpin");
        System.out.println(msg);

        //如果应用正在运行，获取该进程的ID
        String pid = appCtrl.getPid("com.taobao.idlefish");

    }
}
