package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.logcat.AndroidLogcat;
import com.adb.process.android.logcat.LogcatConfig;
import com.adb.process.android.logcat.LogcatConfigBuilder;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {

        AndroidCtrl android = new AndroidCtrl();
        android.isPrintCmd(true);
        LogcatConfig config = new LogcatConfigBuilder()
                .filterTAG("BindView")
                .showFormat(LogcatConfig.FORMAT_BRIEF)
                .filterLevel(LogcatConfig.LEVEL_W)
                .filter("3")
                .build();

        AndroidLogcat logcat = new AndroidLogcat(android);
        logcat.print(config);

    }
}
