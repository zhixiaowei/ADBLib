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

    /**
     * 获取内存信息
     * @return
     *
     * MemTotal:        7806540 kB //设备总内存
     * MemFree:          613952 kB //空闲内存
     * MemAvailable:    2290648 kB
     * Buffers:            1904 kB
     * Cached:          1927524 kB
     * SwapCached:         9432 kB
     * Active:          3154940 kB
     * Inactive:        1491692 kB
     * Active(anon):    2269868 kB
     * Inactive(anon):   629908 kB
     * Active(file):     885072 kB
     * Inactive(file):   861784 kB
     * Unevictable:      156752 kB
     * Mlocked:          156752 kB
     * SwapTotal:       2097148 kB
     * SwapFree:        1332944 kB
     * Dirty:               736 kB
     * Writeback:             0 kB
     * AnonPages:       2867976 kB
     * Mapped:          1076824 kB
     * Shmem:             27512 kB
     * Slab:             564204 kB
     * SReclaimable:     173456 kB
     * SUnreclaim:       390748 kB
     * KernelStack:       88832 kB
     * PageTables:       142600 kB
     * NFS_Unstable:          0 kB
     * Bounce:                0 kB
     * WritebackTmp:          0 kB
     * CommitLimit:     6000416 kB
     * Committed_AS:   145256200 kB
     * VmallocTotal:   263061440 kB
     * VmallocUsed:      119732 kB
     * VmallocChunk:          0 kB
     * Percpu:            19968 kB
     * CmaTotal:         335872 kB
     * CmaFree:               0 kB
     *
     */
    public String memoryOfSystem(){
        try {
            return context.exec(cmd.memoryOfSystem());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取内存占用大的前 num 个进程
     * @return
     */
    public String memoryTop(int num){
        try{
            return context.exec(cmd.memoryTop(num));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
