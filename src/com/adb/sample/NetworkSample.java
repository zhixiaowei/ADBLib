package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.AndroidNetwork;

public class NetworkSample {
    public static void main(String[] args) {
        AndroidNetwork network = new AndroidNetwork(new AndroidCtrl());

        network.ping("www.baidu.com",3,true);

        network.isPreferWifi(false);//当4G和WIFI同时打开，是否WIFI优先

        network.setNetSwitch(true);//打开4G网络
        network.setWifiSwitch(false);//关闭WIFI

        network.getLocalAddress();//获取本地IP地址

        System.out.println(network.connectivity());

        System.out.println(network.netPolicy());

        System.out.println(network.netStatus());

        System.out.println(network.networkManagement());
    }
}
