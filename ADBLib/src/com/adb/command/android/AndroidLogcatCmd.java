package com.adb.command.android;

import com.adb.process.Device;

public class AndroidLogcatCmd{

    /**
     * 打印日志
     * @param grep
     * @return
     */
    public String logcat(String grep){
        if (grep == null||grep.isEmpty()){
            return "adb shell logcat -v time";
        }else{
            return "adb shell logcat -v time | grep \""+grep+"\"";
        }
    }

    /**
     * 保存日志至指定文件
     *
     * @param path
     * @return
     */
    @Deprecated
    public String logcat2WindowFile(String path){
        return "adb logcat -v time > "+path;
    }

    /**
     * 保存日志到Android设备中（这里的过滤会影响该程序的所有日志的输出，包括崩溃日志：）
     * @param path
     * @param tag
     * @return
     */
    public String logcat2AndroidFile(String path,String tag){
        if (tag == null||tag.isEmpty()){
            tag = "*";//不过滤TAG
        }

        return "adb shell logcat -v time -f /sdcard/test.txt -s TAG:"+tag;
    }

    public String logcatByPid(String pid) {
        return "adb shell logcat --pid "+pid;
    }
}
