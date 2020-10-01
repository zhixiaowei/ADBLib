package com.adb.command.android;

public class AndroidSystemCmd{

    /**
     * 获取进程列表
     * @return
     */
    public String listProcess(String grep){
        if (grep == null||grep.isEmpty()){
            return "adb shell ps";
        }else{
            return "adb shell ps|grep "+grep;
        }
    }

    /**
     * Android 5.0以下
     * @return
     */
    public String devIMEI() {
        return "adb shell dumpsys iphonesubinfo";
    }

    /**
     * 型号
     * @return
     */
    public String devModel() {
        return "adb shell getprop ro.product.model";
    }

    /**
     * 品牌
     * @return
     */
    public String devBrand() {
        return "adb shell getprop ro.product.brand";
    }

    /**
     * 获取系统时间
     * @return
     */
    public String date() {
        return "adb shell date";
    }

    public String locationInfo() {
        return "adb shell dumpsys location";
    }

    public String SDKVersion() {
        return "adb shell getprop ro.build.version.sdk";
    }

    public String systemVersion() {
        return "adb shell getprop ro.build.version.release";
    }
}
