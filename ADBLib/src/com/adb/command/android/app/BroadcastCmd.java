package com.adb.command.android.app;

public class BroadcastCmd {
    public String send(String action){
        return "adb shell am broadcast -a "+action;
    }
}
