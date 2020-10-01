package com.adb.command;

public class ADBCmd {

    public String version(){
        return "cmd /c adb version";
    }

    /**
     * 查询连接设备
     * @return
     *
     * 正常执行命令应答
     * List of devices attached
     * c447b522        device
     * ea446ade        devic
     *
     */
    public String devices(){
        return "cmd /c adb devices";
    }
}
