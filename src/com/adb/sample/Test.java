package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.Activity;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidCtrl android = new AndroidCtrl();
        android.isPrintCmd(true);
        Activity activity = new Activity(android);

        String a = activity.foregroundActivity();
        System.out.println(a);

    }
}
