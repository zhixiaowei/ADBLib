package com.adb.sample.window;

import com.adb.process.WindowCtrl;

public class WindowSample {
    public static void main(String[] args) {

        WindowCtrl windowCtrl = new WindowCtrl();
        windowCtrl.setCharset("GBK");

        String tree = windowCtrl.fileTree("E:\\日常");//文件树
//        String dir = windowCtrl.fileDir("E:\\日常");
//
//        windowCtrl.openFileManager("E:\\日常"); //打开资源管理器，并进入指定文件夹
//        windowCtrl.shutdown(30);//30秒后关机
//
//        try {
//            //手动输入cmd命令
//            String cmd = "shutdown -s -t 100";
//            windowCtrl.exec(cmd);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
