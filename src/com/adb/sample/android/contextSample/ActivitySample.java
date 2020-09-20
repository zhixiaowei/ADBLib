package com.adb.sample.android.contextSample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.ActivityIntentBuilder;
import com.adb.process.android.context.IContext;

public class ActivitySample {
    public static void main(String[] args) {
        AndroidCtrl context = new AndroidCtrl();
        Activity activity = new Activity(context);

        //根据包名启动应用（进入默认Activity）
        activity.start("com.huangxiaowei.annotationtest");

        //启动指定Activity
        activity.start("com.hxw.fuck","com.hxw.fuck.MainActivity");

        //带参数启动Activity
        IContext.Intent intent = new ActivityIntentBuilder("android.intent.action.TestActivity",
                "com.huangxiaowei.annotationtest",
                "com.huangxiaowei.annotationtest.TestActivity")
                .addExtra("KEY","这是一个Value")
                .build();
        activity.start(intent);

    }
}
