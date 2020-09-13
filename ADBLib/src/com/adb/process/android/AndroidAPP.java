package com.adb.process.android;

import com.adb.process.AndroidCtrl;
import com.adb.command.andriodCmd.AndroidAppCmd;

public class AndroidAPP extends IAndroid {

    private AndroidAppCmd cmd = new AndroidAppCmd();

    public AndroidAPP(AndroidCtrl androidCtrl) {
        super(androidCtrl);
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
            String msg = androidCtrl.exec(cmd.install(windowPath, isDebugApk, isReInstall));

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
            String msg = androidCtrl.exec(cmd.uninstall(packageName));

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
            androidCtrl.exec(cmd.stopApp(packageName));
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
            return androidCtrl.exec(cmd.listAPP());
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
            return androidCtrl.exec(cmd.listSystemAPP());
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
            return androidCtrl.exec(cmd.list3APP());
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
            androidCtrl.exec(cmd.cleanAPPData(packageName));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
