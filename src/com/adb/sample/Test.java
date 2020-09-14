package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.WindowCtrl;
import com.adb.process.android.AndroidLogcat;

public class Test {
    public static void main(String[] args) {

        AndroidCtrl android = new AndroidCtrl();
        android.isPrintCmd(true);

        AndroidLogcat logcat = new AndroidLogcat(android);
        logcat.save2WindowFile("E:\\人人\\log.txt");

    }
}
