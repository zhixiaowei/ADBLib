package com.adb.process;

import com.adb.command.andriodCmd.AndroidSystemCmd;
import com.adb.process.android.AndroidAPP;
import com.adb.process.android.AndroidFile;
import com.adb.process.android.AndroidLogcat;
import com.adb.process.android.IAndroid;
import com.adb.process.android.context.IContext;

public class AndroidCtrl extends ACtrl{

    AndroidSystemCmd cmd = new AndroidSystemCmd();

    /**
     *  Android 10测试可行
     *
     * 点击指定坐标
     * @param x
     * @param y
     * @return
     */
    public boolean click(int x,int y){
        try{
            exec(cmd.click(226,920));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 滑屏
     * 参数 300 1000 300 500 分别表示起始点x坐标 起始点y坐标 结束点x坐标 结束点y坐标
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean swipe(int startX,int startY,int endX,int endY){
        try{
            exec(cmd.swipe(startX,startY,endX,endY));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Android 10 返回键测试可行
     *
     * 虚拟按键控制
     * @param androidKeyEvent 有效参数见：lib.process.android.AndroidKeyEvent
     * @return
     */
    public boolean inputKeyEvent(int androidKeyEvent){
        try{
            exec(cmd.inputKeyEvent(androidKeyEvent));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    /**
     *
     *  Android 10测试可行
     *  获取当前正在运行进程
     * @param grep 为空时返回全部
     * @return
     */
    public String listProcess(String grep){
        try{
            return exec(cmd.listProcess(grep));
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Android 10测试可行
     *
     * 获取当前正在连接的设备
     * @return
     */
    public String listDevices(){
        try{
            return exec(cmd.devices());
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
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
            String msg = exec(cmd.startActivity(packageName, canonicalName));
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
            return exec(cmd.getTimeOfStartActivity(packageName, canonicalName));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Android 10测试可行
     * @param packageName
     * @param canonicalName
     * @return
     */
    public boolean startService(String packageName,String canonicalName){
        if (!canonicalName.startsWith(packageName)){
            canonicalName = packageName+"."+canonicalName;
        }

        try{
            String msg = exec(cmd.startService(packageName, canonicalName));
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     *
     * 发送广播
     * @param
     * @return
     */
    public boolean sendBroadcast(IContext.Intent intent) {
        try{
            String msg = exec(intent.cmd);
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Android 10测试可行
     * 带参数启动Activity
     * @param intent
     * @return
     */
    public boolean startActivity(IContext.Intent intent) {
        try{
            String msg = exec(intent.cmd);
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Android 10测试可行
     * @param intent
     * @return
     */
    public boolean startService(IContext.Intent intent) {
        try{
            String msg = exec(intent.cmd);
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public AndroidLogcat managerOfLogcat(){
        return new AndroidLogcat(this);
    }

    public AndroidAPP managerOfApp(){
        return new AndroidAPP(this);
    }

    public AndroidFile managerOfFile(){
        return new AndroidFile(this);
    }
}
