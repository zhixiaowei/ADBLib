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
    public String memoryOfSystem() {
        return "adb shell cat /proc/meminfo";
    }

    /**
     * 获取资源占用前 num 的进程
     * @param num
     * @return
     */
    public String memoryTop(int num) {
        return "adb shell top "+num;
    }

    /**
     * 各个进程的内存占用情况，根据高到低排序
     * @return
     */
    public String memoryOfProcessList(){
        return "adb shell dumpsys meminfo";
    }

    public String memoryOfApp(String packageName){
        return "adb shell dumpsys meminfo "+packageName;
    }
}
