package com.adb.process.android;

import com.adb.command.andriodCmd.AndroidHardwareCmd;
import com.adb.process.AndroidCtrl;

import java.io.IOException;

public class AndroidHardware extends IAndroid {

    AndroidHardwareCmd cmd = new AndroidHardwareCmd();

    public AndroidHardware(AndroidCtrl context) {
        super(context);
    }

     /* 获取屏幕分辨率
     * @return
      */
    public String screenSize(){
        try {
            return context.exec(cmd.screenSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Physical density: 480
     * Override density: 160 //修改后
     * @return
     */
    public String screenDensity(){
        try {
            return context.exec(cmd.screenDensity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
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
        try {
            return context.exec(cmd.screenInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取电池信息
     *
     * Current Battery Service state:
     *   AC powered: false
     *   USB powered: true
     *   Wireless powered: false
     *   status: 2
     *   health: 2
     *   present: true
     *   level: 44 //剩余电量
     *   scale: 100 //最大电量
     *   voltage: 3872
     *   temperature: 280
     *   technology: Li-poly
     *
     * @return
     */
    public String  getBatteryInfo(){
        try {
            return context.exec(cmd.getBatteryInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 由于在Android 10上测试无效，且暂未在其他设备上测试过
     * 设置屏幕方向，
     * @param i 参数范围为 0 - 3
     * @return
     */
    @Deprecated
    public String setScreenRotation(int i) {
        try {
            return context.exec(cmd.setScreenRotation(i));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
