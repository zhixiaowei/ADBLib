package com.adb.process.android;

import com.adb.process.ACtrl;
import com.adb.process.ADBCtrl;
import com.adb.process.Device;

public class IAndroid extends ACtrl {
    protected Device context;

    public IAndroid(Device context){
        this.context = context;
    }
}
