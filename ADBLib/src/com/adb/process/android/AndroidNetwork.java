package com.adb.process.android;

import com.adb.command.andriodCmd.AndroidNetworkCmd;
import com.adb.process.AndroidCtrl;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.SocketHandler;

public class AndroidNetwork extends IAndroid {

    AndroidNetworkCmd cmd = new AndroidNetworkCmd();

    public AndroidNetwork(AndroidCtrl androidCtrl) {
        super(androidCtrl);
    }

    public String ping(String url,int count,boolean isSynPrint){
        try {
            String c = cmd.ping(url,count);

            if (isSynPrint){
                context.execAPrint(c);
            }else{
                context.exec(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 网络连接状态
     * @return
     */
    public String connectivity(){
        try {
            return context.exec(cmd.connectivity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 网络策略
     * @return
     */
    public String netPolicy(){
        try {
            return  context.exec(cmd.netPolicy());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 网络状态
     * @return
     */
    public String netStatus(){
        try {
            return context.exec(cmd.netStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 网络管理
     * @return
     */
    public String networkManagement(){
        try {
            return context.exec(cmd.networkManagement());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String netcfg(){
        try {
            return context.exec(cmd.netcfg());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 打开/关闭 WIFI
     * @param isOpen
     * @return
     */
    public String setWifiSwitch(boolean isOpen){
        try {
            return context.exec(cmd.setWifiSwitch(isOpen));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * （如4G通讯）流量开关
     * @param isOpen
     * @return
     */
    public String setNetSwitch(boolean isOpen) {
        try {
            return context.exec(cmd.setNetSwitch(isOpen));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 通讯模块及WIFI均为正常打开状态下，是否WIFI优先
     * @param isPreferWifi true：Wifi优先，false：4G通讯优先
     * @return
     */
    public String isPreferWifi(boolean isPreferWifi){
        try {
            return context.exec(cmd.isPreferWifi(isPreferWifi));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取设备IP
     * @return
     */
    public String getLocalAddress() {
        try {
            return context.exec(cmd.getLocalAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
