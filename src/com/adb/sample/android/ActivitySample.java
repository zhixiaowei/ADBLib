package com.adb.sample.android;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.Activity;

public class ActivitySample {
    public static void main(String[] args) {
        AndroidCtrl context = new AndroidCtrl();
        Activity activity = new Activity(context);

        activity.start("com.huangxiaowei.annotationtest");

    }
}
