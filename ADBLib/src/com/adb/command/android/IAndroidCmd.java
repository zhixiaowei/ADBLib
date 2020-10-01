package com.adb.command.android;

import com.adb.process.Device;

public abstract class IAndroidCmd{
    private String deviceName = null;

    public IAndroidCmd(Device device){
        this.deviceName = device.deviceName;
    }
}
