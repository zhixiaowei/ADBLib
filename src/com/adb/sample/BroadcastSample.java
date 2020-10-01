package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.BroadcastIntentBuilder;
import com.adb.process.android.context.IContext;

public class BroadcastSample {

    public static void main(String[] args) {

        Broadcast broadcast = new Broadcast(new ADBCtrl().firstDevice());

        IContext.Intent intent =
                new BroadcastIntentBuilder("com.hxw.test")//ACTION
                        .addExtra("数组",new int[]{1,2,3,4})
                        .addExtra("KeyOfString","i am string")
                        .addExtra("KeyOfBoolean",false)
                        .addExtra("KeyOfLong",Long.valueOf("12433333399"))
                        .build();
        broadcast.send(intent);//发送广播

        broadcast.send(Broadcast.BOOT_COMPLETED);//发送开机广播

        broadcast.send("com.hxw.hi");//发送指定Action的广播
    }
}
