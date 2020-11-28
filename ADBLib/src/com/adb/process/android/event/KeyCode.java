package com.adb.process.android.event;

/**
 * 物理/虚拟 按键控制
 *
 * 如电源键：adb shell input keyevent 26
 */
public class KeyCode{
    public final static int KEYCODE_POWER = 26;//电源键
    public final static int KEYCODE_HOME = 3;//HOME键
    public final static int KEYCODE_BACK = 4;//返回键 Android 10测试可行
    public final static int KEYCODE_VOLUME_ADD = 24;//音量增加
    public final static int KEYCODE_VOLUME_REDUCE  = 25;//音量减低
    public final static int KEYCODE_MENU = 82;//菜单

    public final static int KEYCODE_VOLUME_MUTE = 164;//静音

    public final static int KEYCODE_MEDIA_PLAY_PAUSE = 85;//暂停/播放
    public final static int KEYCODE_MEDIA_STOP = 86;//停止播放
    public final static int KEYCODE_MEDIA_NEXT = 87;//下一首
    public final static int KEYCODE_MEDIA_LAST = 88;//上一首

    public final static int KEYCODE_MEDIA_RESUME = 126;//恢复播放
    public final static int KEYCODE_MEDIA_PAUSE = 127;//暂停播放

    public final static int KEYCODE_LIGHT_OFF = 223;//熄灭屏幕
    public final static int KEYCODE_LIGHT_UP = 224;//点亮屏幕
}
