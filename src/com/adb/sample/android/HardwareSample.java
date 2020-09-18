package com.adb.sample.android;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidHardware;

import javax.print.attribute.HashAttributeSet;

public class HardwareSample {

    public static void main(String[] args) {
        AndroidHardware hardware = new AndroidHardware(new AndroidCtrl());

        String model = hardware.devModel();
        System.out.println("当前设备的型号为"+model);

        String brand = hardware.devBrand();
        System.out.println("当前设备品牌为："+brand);

        String sysV = hardware.SystemVersion();
        System.out.println("系统版本为："+sysV);

        String sdkV = hardware.SDKVersion();
        System.out.println("SDK版本为："+sdkV);


    }
}
