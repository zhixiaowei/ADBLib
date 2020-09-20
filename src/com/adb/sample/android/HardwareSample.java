package com.adb.sample.android;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidHardware;

import javax.print.attribute.HashAttributeSet;

public class HardwareSample {

    public static void main(String[] args) {
        AndroidHardware hardware = new AndroidHardware(new AndroidCtrl());

        hardware.screenDensity();//屏幕密度
        hardware.screenSize();//屏幕分辨率
        hardware.screenInfo();//屏幕详细信息

        hardware.getBatteryInfo();//电池信息（总量、余量等）

    }
}
