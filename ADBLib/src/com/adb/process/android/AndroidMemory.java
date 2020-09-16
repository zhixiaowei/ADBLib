package com.adb.process.android;

import com.adb.command.andriodCmd.AndroidMemoryCmd;
import com.adb.process.AndroidCtrl;

import java.io.IOException;

public class AndroidMemory extends IAndroid{

    public static final String TRIM_HIDDEN = "HIDDEN";
    public static final String TRIM_RUNNING_MODERATE = "RUNNING_MODERATE";
    public static final String TRIM_BACKGROUND = "BACKGROUND";
    public static final String TRIM_RUNNING_LOW = "RUNNING_LOW";
    public static final String TRIM_MODERATE = "MODERATE";
    public static final String TRIM_RUNNING_CRITICAL = "RUNNING_CRITICAL";
    public static final String TRIM_COMPLETE = "COMPLETE";

    private AndroidMemoryCmd cmd = new AndroidMemoryCmd();

    public AndroidMemory(AndroidCtrl context) {
        super(context);
    }

    /**
     * adb shell am send-trim-memory  <pid> <level>
     *
     *向进程，发出 level=RUNNING_LOW 的收紧内存命令。
     * @param packageName
     */
    public boolean sendTrimMemory(String packageName,String trimLevel){
        String pid = context.managerOfApp().getPid(packageName);

        if (pid.isEmpty()){
            return false;
        }

        try {
            context.exec(cmd.sendTrimMemory(pid,trimLevel));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }
}
