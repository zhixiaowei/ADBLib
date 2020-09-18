package com.adb.command.andriodCmd;

public class AndroidMemoryCmd {

    /**
     * 向进程，发出 level=RUNNING_LOW 的收紧内存命令。
     * @param pid
     * @param trimLevel
     * @return
     */
    public String sendTrimMemory(String pid, String trimLevel) {
        return "adb shell am send-trim-memory "+pid+" "+trimLevel;
    }

    /**
     * 获取内存信息
     * @return
     */
    public String memoryInfo() {
        return "adb shell cat /proc/meminfo";
    }
}
