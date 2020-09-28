package com.adb.command;

public class AndroidNetworkCmd {
    /**
     *  Android 10测试可行
     * 如：adb shell ping -c 4 www.baidu.com
     * @param url
     * @param count
     * @return
     */
    public String ping(String url, int count) {
        return "adb shell ping -c "+count+" "+url;
    }

    /**
     * 打开/关闭 WIFI
     * @param isOpen
     * @return
     */
    public String setWifiSwitch(boolean isOpen){
        return "adb shell svc wifi "+(isOpen?"enable":"disable");
    }

    /**
     * （如4G通讯）流量开关
     * @param isOpen
     * @return
     */
    public String setNetSwitch(boolean isOpen){
        return "adb shell svc data "+(isOpen?"enable":"disable");
    }

    /**
     * 通讯模块及WIFI均为正常打开状态下，是否WIFI优先
     * @param isPreferWifi true：Wifi优先，false：4G通讯优先
     * @return
     */
    public String isPreferWifi(boolean isPreferWifi){
        if (isPreferWifi){
            return "adb shell svc wifi prefer";
        }else{
            return "adb shell svc data prefer";
        }
    }

    /**
     * 获取设备IP
     * @return
     */
    public String getLocalAddress() {
        return "adb shell ip -f inet addr show wlan0";
    }


    /**
     *  Android 10测试可行
     * 网络连接状态
     * @return
     */
    public String connectivity(){
        return "adb shell dumpsys connectivity";
    }

    /**
     * Android 10测试可行
     * 网络策略
     * @return
     */
    public String netPolicy(){
        return "adb shell dumpsys netpolicy";
    }

    /**
     *  Android 10测试可行
     * 网络状态
     * @return
     */
    public String netStatus(){
        return "adb shell adb shell dumpsys netstats";
    }

    /**
     *  Android 10测试可行
     * 网络管理
     * @return
     */
    public String networkManagement(){
        return "adb shell dumpsys network_management";
    }


    /**
     * Android 10测试无可用
     *  查看端口信息
     * @return
     *
     */
    @Deprecated
    public String netcfg(){
        return "adb shell netcfg";
    }


}
