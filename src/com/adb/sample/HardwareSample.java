package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.android.AndroidHardware;

public class HardwareSample {

    public static void main(String[] args) {
        AndroidHardware hardware = new ADBCtrl().firstDevice().managerOfHardware();

        hardware.screenDensity();//屏幕密度
        hardware.screenSize();//屏幕分辨率
        hardware.screenInfo();//屏幕详细信息

        hardware.getBatteryInfo();//电池信息（总量、余量等）

        hardware.getCPUInfo();//CPU信息

    }
}
