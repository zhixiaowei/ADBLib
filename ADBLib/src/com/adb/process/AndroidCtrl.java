package com.adb.process;

import com.adb.command.AndroidSystemCmd;
import com.adb.process.android.*;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.Service;
import com.adb.process.android.logcat.AndroidLogcat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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

    public AndroidLogcat managerOfLogcat(){
        return new AndroidLogcat(this);
    }

    public AndroidAPP managerOfApp(){
        return new AndroidAPP(this);
    }

    public AndroidFile managerOfFile(){
        return new AndroidFile(this);
    }

    public Activity managerOfActivity(){
        return new Activity(this);
    }

    public Service managerOfService(){
        return new Service(this);
    }

    public Broadcast managerOfBroadcast(){
        return new Broadcast(this);
    }

    public AndroidHardware managerOfHardware(){
        return new AndroidHardware(this);
    }

    public AndroidSystem managerOfSystem(){
        return new AndroidSystem(this);
    }

    public AndroidMemory managerOfMemory(){
        return new AndroidMemory(this);
    }

    public AndroidNetwork managerOfNetwork(){
        return new AndroidNetwork(this);
    }


    /**
     * 执行cmd并实时输出（适用于持续输出的命令，如日志）
     * @param cmd
     * @return
     * @throws IOException
     */
    public void execAPrint(String cmd,String grep) throws IOException {
        if (isPrintCmd){
            System.out.println(cmd);
        }

        if (grep == null||grep.trim().isEmpty()){
            super.print(runtime.exec(cmd));
        }else{
            print(runtime.exec(cmd),grep);
        }

    }

    /**
     *
     * 由于 grep 的指令会导致输入流的处理出现堵塞，输出的数据会相对滞后于正常命令行的输出（肉眼可见），采用contains替代解决该问题
     * 该方案下运行数据与命令行输出的数据时间上相差无几
     *
     * 打印输出
     * @param process
     */
    private void print(Process process,String grep) {
        if (isPrintCmd){
            System.out.println("grep "+grep);
        }

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(charset)));

            String line;

            while ((line=br.readLine())!=null) {
                if (line.contains(grep)){
                    System.out.println(line);
                }
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName(charset)));
            String errorLine;
            while ((errorLine = error.readLine())!=null) {
                System.out.println(errorLine);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
