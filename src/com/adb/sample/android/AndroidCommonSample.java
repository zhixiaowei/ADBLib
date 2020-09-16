package com.adb.sample.android;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.ActivityIntentBuilder;
import com.adb.process.android.context.BroadcastIntentBuilder;
import com.adb.process.android.context.IContext;
import com.adb.process.android.context.ServiceInentBuilder;

public class AndroidCommonSample {
    public static void main(String[] args) {

        AndroidCtrl androidCtrl = new AndroidCtrl();

        androidCtrl.setCharset("GBK");//设置编码格式，默认为GBK
        androidCtrl.isPrintCmd(true);//是否打印执行的命令


        //获取已连接设备
        String devices = androidCtrl.listDevices();

        //获取正在运行的进程
        String process = androidCtrl.listProcess(null);

        //发送广播
        IContext.Intent intent =
                new BroadcastIntentBuilder("com.hxw.fuck")//ACTION
                        .addExtra("数组",new int[]{1,2,3,4})
                        .addExtra("字符串","i am string")
                        .addExtra("人否",false)
                        .addExtra("长数字",Long.valueOf("12433333399"))
                        .build();

//        broadcast.send(androidCtrl);
        androidCtrl.sendBroadcast(intent);//发送广播

        //不带参数启动Activity
//        androidCtrl.startActivity("com.hxw.fuck","com.hxw.fuck.MainActivity");

        //带参数启动Activity
        IContext.Intent intent2 = new ActivityIntentBuilder("android.intent.action.TestActivity",
                "com.huangxiaowei.annotationtest",
                "com.huangxiaowei.annotationtest.TestActivity")
                .addExtra("KEY","这是一个Value")
                .build();
//        androidCtrl.startActivity(intent2);

        //不带参数启动Service
//        androidCtrl.startService("com.hxw.fuck","com.hxw.fuck.MainService");

        //带参数启动Service
//        IContext.Intent intent3 = new ServiceInentBuilder("com.huangxiaowei.annotationtest",
//                "com.huangxiaowei.annotationtest.TestService")
//                .addExtra("KEY","这是一个Value")
//                .build();
//
//        androidCtrl.startService(intent3);

        //点击指定坐标
        androidCtrl.click(230,449);



        //释放资源
//        androidCtrl.close();





    }
}
