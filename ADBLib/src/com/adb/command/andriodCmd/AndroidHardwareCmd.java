package com.adb.command.andriodCmd;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.IAndroid;

public class AndroidHardwareCmd{

    /**
     * Physical size: 1080x1920
     *-----
     * Physical size: 1080x1920
     * Override size: 480x1024
     *
     * 获取屏幕分辨率
     * @return
     */
    public String screenSize(){
        return "adb shell wm size";
    }

    /**
     * Physical density: 480
     * Override density: 160 //修改后
     * @return
     */
    public String screenDensity(){
        return "adb shell wm density";
    }

    /**
     * 屏幕信息
     * @return
     *
     * WINDOW MANAGER DISPLAY CONTENTS (dumpsys window displays)
     *   Display: mDisplayId=0
     *     init=1080x1920 420dpi cur=1080x1920 app=1080x1794 rng=1080x1017-1810x1731
     *     deferred=false layoutNeeded=false
     */
    public String screenInfo(){
        return "adb shell dumpsys window displays";
    }

    /**
     * Android 5.0以下
     * @return
     */
    public String devIMEI() {
        return "adb shell dumpsys iphonesubinfo";
    }

    public String systemVersion() {
        return "adb shell getprop ro.build.version.release";
    }

    public String SDKVersion() {
        return "adb shell getprop ro.build.version.sdk";
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
}
