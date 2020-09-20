package com.adb.process.android.context;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.IAndroid;

import java.io.IOException;

public class Broadcast extends IAndroid {

    public final static String CONNECTIVITY_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE";//网络连接发生变化
    public final static String SCREEN_ON = "android.intent.action.SCREEN_ON";//屏幕点亮
    public final static String SCREEN_OFF = "android.intent.action.SCREEN_OFF";//屏幕熄灭
    public final static String BATTERY_LOW = "android.intent.action.BATTERY_LOW";//电量低，会弹出电量低提示框
    public final static String BATTERY_OKAY = "android.intent.action.BATTERY_OKAY";//电量恢复了
    public final static String BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED";//设备启动完毕,开机广播
    public final static String DEVICE_STORAGE_LOW = "android.intent.action.DEVICE_STORAGE_LOW";//存储空间过低
    public final static String DEVICE_STORAGE_OK = "android.intent.action.DEVICE_STORAGE_OK";//存储空间恢复
    public final static String PACKAGE_ADDED = "android.intent.action.PACKAGE_ADDED";//安装了新的应用
    public final static String WIFI_STATE_CHANGE = "android.net.wifi.STATE_CHANGE";//WiFi 连接状态发生变化
    public final static String STATE_CHANGE = "android.net.wifi.WIFI_STATE_CHANGED";//WiFi 状态变为启用/关闭/正在启动/正在关闭/未知
    public final static String BATTERY_CHANGED = "android.intent.action.BATTERY_CHANGED";	//电池电量发生变化
    public final static String INPUT_METHOD_CHANGED ="android.intent.action.INPUT_METHOD_CHANGED";//系统输入法发生变化
    public final static String ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED";//外部电源连接
    public final static String ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED";	//外部电源断开连接
    public final static String DREAMING_STARTED = "android.intent.action.DREAMING_STARTED";	//系统开始休眠
    public final static String DREAMING_STOPPED = "android.intent.action.DREAMING_STOPPED";	//系统停止休眠
    public final static String WALLPAPER_CHANGED = "android.intent.action.WALLPAPER_CHANGED";//壁纸发生变化
    public final static String HEADSET_PLUG = "android.intent.action.HEADSET_PLUG";//插入耳机
    public final static String MEDIA_UNMOUNTED = "android.intent.action.MEDIA_UNMOUNTED";//卸载外部介质
    public final static String MEDIA_MOUNTED = "android.intent.action.MEDIA_MOUNTED";//挂载外部介质
    public final static String POWER_SAVE_MODE_CHANGED = "android.os.action.POWER_SAVE_MODE_CHANGED";//省电模式开启

    public Broadcast(AndroidCtrl androidCtrl) {
        super(androidCtrl);
    }

    /**
     *
     * 发送广播
     * @param
     * @return
     */
    public boolean send(IContext.Intent intent) {
        try{
            String msg = context.exec(intent.cmd);
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     *
     * 发送广播
     * @param
     * @return
     */
    public boolean send(String action){
        try {
            context.exec("adb shell am broadcast -a "+action);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
