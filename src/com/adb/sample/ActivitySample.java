package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.ActivityIntentBuilder;
import com.adb.process.android.context.IContext;

public class ActivitySample {
    public static void main(String[] args) {
        Device context = new ADBCtrl().firstDevice();
        Activity activity = new Activity(context);

        //根据包名启动应用（进入默认Activity）
        activity.start("com.huangxiaowei.annotationtest");

        //启动指定Activity
        activity.start("com.hxw.test","com.hxw.test.MainActivity");

        //带参数启动Activity
        IContext.Intent intent = new ActivityIntentBuilder("android.intent.action.TestActivity",
                "com.huangxiaowei.annotationtest",
                "com.huangxiaowei.annotationtest.TestActivity")
                .addExtra("KEY","这是一个Value")
                .build();
        activity.start(intent);

    }
}
