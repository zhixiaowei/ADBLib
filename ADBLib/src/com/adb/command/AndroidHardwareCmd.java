package com.adb.command;

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
     * 获取电池信息
     * @return
     */
    public String getBatteryInfo() {
        return "adb shell dumpsys battery";
    }


    /**
     * 设置屏幕方向
     * Android 10测试无效，暂未在其他设备上测试
     * @param i
     * @return
     */
    @Deprecated
    public String setScreenRotation(int i) {
        return "adb shell content insert --uri content://settings/system --bind name:s:user_rotation --bind value:i:"+i;
    }

    /**
     * 是否锁定方向
     * Android 10测试有效（小米10）
     *
     * @param isLock
     * @return
     */
    public String setLockRotation(boolean isLock){
        return "adb shell content insert --uri content://settings/system --bind name:s:accelerometer_rotation --bind value:i:"+(isLock?0:1);
    }
}
