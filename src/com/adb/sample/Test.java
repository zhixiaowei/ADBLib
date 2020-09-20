package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidHardware;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidCtrl context = new AndroidCtrl();
        AndroidHardware hardware = new AndroidHardware(context);
        hardware.setScreenRotation(2);
    }
}
