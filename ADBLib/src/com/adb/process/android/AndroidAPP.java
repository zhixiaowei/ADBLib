package com.adb.process.android;

import com.adb.process.AndroidCtrl;
import com.adb.command.andriodCmd.AndroidAppCmd;

import java.io.IOException;

public class AndroidAPP extends IAndroid {

    private AndroidAppCmd cmd = new AndroidAppCmd();

    public AndroidAPP(AndroidCtrl context) {
        super(context);
    }
    
    /**
     * 安装应用
     * @param windowPath 电脑端的apk文件地址
     * @param isDebugApk 是否为debug签名的应用
     * @param isReInstall 如果已经安装，是否重新安装
     * @return
     */
    public boolean install(String windowPath,boolean isDebugApk,boolean isReInstall){
        try{
            String msg = context.exec(cmd.install(windowPath, isDebugApk, isReInstall));

            if (msg.toLowerCase().contains("success")){
                return true;
            }else{
                System.out.println(msg);
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 卸载应用
     * @param packageName 包名
     * @return
     */
    public boolean uninstall(String packageName){
        try{
            String msg = context.exec(cmd.uninstall(packageName));

            if (msg.toLowerCase().contains("success")){
                return true;
            }else{
                System.out.println(msg);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Android 10测试可行
     *
     * 强制停止APP
     * @param packageName
     * @return
     */
    public boolean stopApp(String packageName){
        try {
            context.exec(cmd.stopApp(packageName));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Android 10测试可行
     *
     * 获取APP列表
     * @return
     */
    public String listApp(){
        try{
            return context.exec(cmd.listAPP());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Android 10测试可行
     *
     * 系统APP
     * @return
     */
    public String listSystemApp(){
        try{
            return context.exec(cmd.listSystemAPP());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Android 10测试可行
     *
     * 获取第三方APP
     * @return
     */
    public String list3App(){
        try{
            return context.exec(cmd.list3APP());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 根据包名获取PID
     * @param packageName
     * @return
     */
    public String getPid(String packageName) {
        try {
            String[] msg = context.exec(context.listProcess(packageName)).split(" ");

            for (int i = 1;i<msg.length;i++){
                String temp = msg[i];

                if (!temp.isEmpty()){
                    return temp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 读取应用信息
     * @param packageName
     * @return
     */
    public String readAppInfo(String packageName){
        try{
            return context.exec(cmd.readAppInfo(packageName));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 清空APP数据
     * @param packageName
     * @return
     */
    public boolean cleanAPPData(String packageName){
        try{
            context.exec(cmd.cleanAPPData(packageName));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取应用安装路径
     * @param packageName 不能为空
     * @return
     */
    public String readAppInstallPath(String packageName){

        try {
           return context.exec(cmd.readAppInstallPath(packageName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
