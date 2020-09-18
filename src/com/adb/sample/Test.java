package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidHardware;
import com.adb.process.android.context.Activity;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidCtrl context = new AndroidCtrl();
        AndroidHardware hardware = new AndroidHardware(context);

        String model = hardware.devModel();
        System.out.println("当前设备的型号为"+model);
    }
}
