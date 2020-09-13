package com.adb.sample;

import com.adb.process.WindowCtrl;

public class Test {
    public static void main(String[] args) {

//        AndroidCtrl android = new AndroidCtrl();

//        IContext.Intent intent2 = new ActivityIntentBuilder("android.intent.action.TestActivity",
//                "com.huangxiaowei.annotationtest",
//                "com.huangxiaowei.annotationtest.TestActivity")
//                .addExtra("KEY","这是一个Value")
//                .build();
//        android.startActivity(intent2);

//        IContext.Intent intent3 = new ServiceInentBuilder("com.huangxiaowei.annotationtest",
//                "com.huangxiaowei.annotationtest.TestService")
//                .addExtra("KEY","这是一个Value")
//                .build();
//
//        android.startService(intent3);

//        android.startService("com.huangxiaowei.annotationtest","com.huangxiaowei.annotationtest.TestService");

//       String msg =  android.listProcess();
//       System.out.println(msg);
//        android.inputKeyEvent(AndroidKeyEvent.BACK);

//        AndroidAPP app = new AndroidAPP(android);
//        String msg = app.list3App();
//        System.out.println(msg);
//        System.out.println("----------------------");
//        String sys = app.listSystemApp();
//        System.out.println(sys);

//        AndroidFile file = new AndroidFile(android);
//        String list = file.delDirFileBySuffix("/storage/emulated/0/新建文件夹",".log");
//        String list = file.readTextFile("/storage/emulated/0/新/
//        String msg = file.copyFileWindow2Android("/storage/emulated/0/新建文件夹/1.txt","E:\\日常\\test.txt");
//        System.out.println(msg);

        WindowCtrl windowCtrl = new WindowCtrl();
        String msg = windowCtrl.fileDir("E:\\日常");
        System.out.println(msg);
    }
}
