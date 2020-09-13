package com.adb.process.android;

import com.adb.command.andriodCmd.AndroidLogcatCmd;
import com.adb.process.AndroidCtrl;

public class AndroidLogcat extends IAndroid {

    private AndroidCtrl androidCtrl;
    private AndroidLogcatCmd cmd = new AndroidLogcatCmd();

    public AndroidLogcat(AndroidCtrl androidCtrl) {
        super(androidCtrl);
    }

    public void save2WindowFile(String path){
        try{
            androidCtrl.exec(cmd.logcat2WindowFile(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存日志到Android设备
     * @param path 文件完整路径
     * @param tag 日志过滤的TAG
     */
    public void save2AndroidFile(String path,String tag){
        try {
            androidCtrl.exec(cmd.logcat2AndroidFile(path, tag));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void print(String grep){
        try {
            androidCtrl.execPrint(cmd.logcat(grep));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
