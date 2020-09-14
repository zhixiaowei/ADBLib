package com.adb.command.andriodCmd;

public class AndroidAppCmd {


    /**
     * 安装Window上的APK文件
     * @param windowPath 文件路径
     * @param isDebugApk 是否为debug签名的APK
     * @param isReInstall 如果已安装，是否重新安装
     * @return
     */
    public String install(String windowPath,boolean isDebugApk,boolean isReInstall){
        return "adb install "+(isReInstall?"-r ":"")+(isDebugApk?"-t ":"")+windowPath;
    }

    /**
     * 卸载应用
     * @param packageName
     * @return
     */
    public String uninstall(String packageName){
        return "adb uninstall "+packageName;
    }


    /**
     * APP列表
     * @return
     */
    public String listAPP(){
        return "adb shell pm list packages";
    }

    /**
     * 系统APP列表
     * @return
     */
    public String listSystemAPP(){
        return "adb shell pm list packages -s";
    }

    /**
     * 所有第三方应用
     * @return
     */
    public String list3APP(){
        return "adb shell pm list packages -3";
    }


    /**
     * 强制停止应用
     * @param packageName 包名
     * @return
     */
    public String stopApp(String packageName){
        return "adb shell am force-stop "+packageName;
    }


    /**
     * 清空APP数据
     * @return
     */
    public String cleanAPPData(String packageName){
        return "adb shell pm clear "+packageName;
    }

    /**
     * 读取应用的基本信息
     * @param packageName
     * @return
     */
    public String readAppInfo(String packageName) {
        return "adb shell dumpsys package "+packageName;
    }
}
