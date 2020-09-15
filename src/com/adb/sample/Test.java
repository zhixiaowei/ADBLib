package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.WindowCtrl;
import com.adb.process.android.AndroidLogcat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

//        AndroidCtrl android = new AndroidCtrl();
//        android.isPrintCmd(true);
//
//        AndroidLogcat logcat = new AndroidLogcat(android);
//        logcat.save2WindowFile("E:\\人人\\log.txt");
//
        Runtime p = Runtime.getRuntime();

        Process process = p.exec("cmd /c start");

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), "GBK"));

        Thread.sleep(3000);
    }
}
