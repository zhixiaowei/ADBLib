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
