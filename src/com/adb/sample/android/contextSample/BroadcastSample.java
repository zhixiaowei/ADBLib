package com.adb.sample.android.contextSample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.BroadcastIntentBuilder;
import com.adb.process.android.context.IContext;

public class BroadcastSample {


    public static void main(String[] args) {

        Broadcast broadcast = new Broadcast(new AndroidCtrl());

        IContext.Intent intent =
                new BroadcastIntentBuilder("com.hxw.fuck")//ACTION
                        .addExtra("数组",new int[]{1,2,3,4})
                        .addExtra("字符串","i am string")
                        .addExtra("在否",false)
                        .addExtra("长数字",Long.valueOf("12433333399"))
                        .build();
        broadcast.send(intent);//发送广播

        broadcast.send(Broadcast.BOOT_COMPLETED);//发送开机广播

        broadcast.send("com.hxw.hi");//发送指定Action的广播
    }
}
