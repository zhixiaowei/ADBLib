package com.adb.command.android;

public class AndroidDeviceCmd{

    /**
     * 截屏并保存到window，部分设备不支持（6.0以上设备不可行）
     * @param windowPath
     * @return
     *
     * 由于部分设备不支持，建议采用screenshot2AndroidFile然后再复制到电脑端
     */
    @Deprecated
    public String screenshot2WindowFile(String windowPath){
        return "adb exec-out screencap -p > "+windowPath;
    }

    /**
     * 截屏并保存到android
     * @param androidPath
     * @return
     */
    public String screenshot2AndroidFile(String androidPath){
        return "adb shell screencap -p "+androidPath;
    }
}
