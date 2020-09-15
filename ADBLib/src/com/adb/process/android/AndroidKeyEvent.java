package com.adb.process.android;

import com.adb.process.AndroidCtrl;

/**
 * 物理/虚拟 按键控制
 *
 * 如电源键：adb shell input keyevent 26
 */
public class AndroidKeyEvent extends IAndroid {
    public final static int POWER = 26;//电源键
    public final static int HOME = 3;//HOME键
    public final static int BACK = 4;//返回键 Android 10测试可行
    public final static int VOLUME_ADD = 24;//音量增加
    public final static int VOLUME_REDUCE  = 25;//音量减低
    public final static int MENU = 82;//菜单

    public final static int VOLUME_MUTE = 164;//静音

    public final static int MEDIA_PLAY_PAUSE = 85;//暂停/播放
    public final static int MEDIA_STOP = 86;//停止播放
    public final static int MEDIA_NEXT = 87;//下一首
    public final static int MEDIA_LAST = 88;//上一首

    public final static int MEDIA_PAUSE = 126;//暂停播放
    public final static int MEDIA_RESUME = 126;//恢复播放

    public final static int LIGHT_UP = 224;//点亮屏幕
    public final static int LIGHT_OFF = 223;//熄灭屏幕

    public AndroidKeyEvent(AndroidCtrl androidCtrl) {
        super(androidCtrl);
    }

    public void input(int key){
       androidCtrl.inputKeyEvent(key);
    }
}
