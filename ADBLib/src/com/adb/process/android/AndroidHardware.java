package com.adb.process.android;

import com.adb.command.android.AndroidHardwareCmd;
import com.adb.process.Device;
import com.sun.glass.ui.Size;

import java.io.IOException;

public class AndroidHardware extends IAndroid {

    AndroidHardwareCmd cmd = new AndroidHardwareCmd();

    public AndroidHardware(Device context) {
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
     * 如果获取大小失败则返回空
     * @return
     */
    public Size getScreenSize(){
        try {
            String[] msg = screenSize().substring(15).trim().split("x");

            Size size = new Size();
            size.width = Integer.parseInt(msg[0]);
            size.height = Integer.parseInt(msg[1]);

            return size;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
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

    /**
     * 设置是否锁定屏幕方向
     * @param isLock
     * @return
     */
    public String isLockRotation(boolean isLock){
        try {
            return context.exec(cmd.setLockRotation(true));
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



    public String getCPUInfo(){
        try {
            return context.exec(cmd.cpuInfo());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
