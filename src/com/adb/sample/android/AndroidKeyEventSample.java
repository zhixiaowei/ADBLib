package com.adb.sample.android;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidKeyEvent;

/**
 * 物理/虚拟 按键控制
 */
public class AndroidKeyEventSample {
    public static void main(String[] args) {

        AndroidCtrl android = new AndroidCtrl();
        android.inputKeyEvent(AndroidKeyEvent.BACK);//返回键
        android.inputKeyEvent(AndroidKeyEvent.VOLUME_ADD);//音量增加键

        //下面这种方法也可
//        AndroidKeyEvent androidKeyEvent = new AndroidKeyEvent(new AndroidCtrl());
//
//        androidKeyEvent.input(AndroidKeyEvent.BACK);//返回键
//
//        androidKeyEvent.input(AndroidKeyEvent.POWER);//电源键
//
//        androidKeyEvent.input(AndroidKeyEvent.LIGHT_UP);//点亮屏幕
//        androidKeyEvent.input(AndroidKeyEvent.LIGHT_OFF);//熄屏
//
//        androidKeyEvent.input(AndroidKeyEvent.VOLUME_ADD);//音量增加键
//        androidKeyEvent.input(AndroidKeyEvent.VOLUME_REDUCE);//音量减少键
//        androidKeyEvent.input(AndroidKeyEvent.VOLUME_MUTE);//静音


    }
}
