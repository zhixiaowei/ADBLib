package com.adb.process.android;

import com.adb.command.andriodCmd.ActivityCmd;
import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.IContext;

public class Activity extends IAndroid {

    public Activity(AndroidCtrl context) {
        super(context);
    }

    private ActivityCmd cmd = new ActivityCmd();

    /**
     * 获取当前正在前台的Activity信息
     * @return
     */
    public String getForegroundActivity(){
        try {
           return context.exec(cmd.getForegroundActivity());
        }catch (Exception e){
            return "";
        }
    }

    /**
     * Android 10测试可行
     * 带参数启动Activity
     * @param intent
     * @return
     */
    public boolean startActivity(IContext.Intent intent) {
        try{
            String msg = context.exec(intent.cmd);
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Android 10测试可行
     *
     * @param packageName 不能为空
     * @param canonicalName activity的完整类地址
     * 注：如果该Activity没有在AndroidManifest中声明intent-filter/Action/category，则无法启动
     * @return
     */
    public boolean startActivity(String packageName,String canonicalName){

        if (!canonicalName.startsWith(packageName)){
            canonicalName = packageName+"."+canonicalName;
        }

        try{
            String msg = context.exec(cmd.startActivity(packageName, canonicalName));
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean startActivity(String packageName){

        try{
            String msg = context.exec(cmd.startActivity(packageName));
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Android 10测试可行
     *
     * 获取Activity启动的时长
     * @param packageName
     * @param canonicalName
     * @return
     *
     * 正常返回内容如：
     * Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.huangxiaowei.annotationtest/.MainActivity }
     * Status: ok //启动成功
     * LaunchState: WARM //热启动：即应用进程还在后台的情况（相对的为冷启动，即第一次启动）
     * Activity: com.huangxiaowei.annotationtest/.TestActivity
     * TotalTime: 137
     * WaitTime: 137
     * Complete
     *
     */
    public String getTimeOfStartActivity(String packageName,String canonicalName) {

        if (!canonicalName.startsWith(packageName)) {
            canonicalName = packageName + "." + canonicalName;
        }

        try {
            return context.exec(cmd.getTimeOfStartActivity(packageName, canonicalName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


}
