package com.adb.process;

import com.adb.command.android.AndroidDeviceCmd;
import com.adb.process.android.*;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.Service;
import com.adb.process.android.logcat.AndroidLogcat;

import java.io.File;
import java.io.IOException;

public class Device extends ACtrl{

    public String deviceName;
    private String prefix;

    /**
     *
     * @param adbCtrl Device将继承ADBCtrl的基本参数配置，当设置ADBCtrl时，也会同时设置所有连接的设备，
     *                但如果设置Device，则只对该Device的输出数据有效
     * @param deviceName
     */
    protected Device(ADBCtrl adbCtrl,String deviceName) {
        this.runtimeDir = adbCtrl.runtimeDir;
        this.charset = adbCtrl.charset;
        this.isPrintCmd = adbCtrl.isPrintCmd;

        this.deviceName = deviceName;
        this.prefix = "adb -s "+deviceName;
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

    private AndroidDeviceCmd cmd = new AndroidDeviceCmd();

    @Override
    protected Process exec(String command, String[] envp, File dir) throws IOException {
        if(command.startsWith("adb")){
            command = command.replace("adb",prefix);
        }

        return super.exec(command, envp, dir);
    }

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
            String reply = exec(cmd.click(x,y));
            if (reply!=null&&reply.trim().isEmpty()){
                //正常执行的情况下是无应答的
                return true;
            }else{
                System.out.println(reply);
                return false;
            }
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
            String reply = exec(cmd.swipe(startX,startY,endX,endY));

            if (reply!=null&&reply.trim().isEmpty()){
                //正常执行的情况下是无应答的
                return true;
            }else{
                System.out.println(reply);
                return false;
            }
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
            String reply = exec(cmd.inputKeyEvent(androidKeyEvent));

            if (reply!=null&&reply.trim().isEmpty()){
                //正常执行的情况下是无应答的
                return true;
            }else{
                System.out.println(reply);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 截屏并保存到window
     *
     * 由于ADB的已有指令仅支持低版本的设备，故而采用更通用的方案
     * 先截屏保存至Android，再将文件从Android复制到Window，最后删除Android上的文件
     * @param windowPath
     * @return
     */
    public boolean screenshot2WindowFile(String windowPath){

        AndroidFile file = new AndroidFile(this);

        try{
            String path = file.getExternalStorageDirectoryPath().trim();
            if (path == null||path.isEmpty()){
                System.err.println("获取sdcard目录失败");
                return false;
            }else{
                String name = new File(windowPath).getName();
                String androidPath = path+"/"+name;

                screenshot2AndroidFile(androidPath);
                String rs = file.copyFileAndroid2Window(androidPath,windowPath);
                file.delDirFileBySuffix(path,name);

                return rs.contains("file pulled");
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 截屏并保存到android
     * @param androidPath
     * @return
     */
    public boolean screenshot2AndroidFile(String androidPath){
        try{
            String reply = exec(cmd.screenshot2AndroidFile(androidPath));
            return !reply.startsWith("error:");
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
