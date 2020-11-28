package com.adb.process;

import com.adb.command.android.AndroidDeviceCmd;
import com.adb.process.android.*;
import com.adb.process.android.app.AndroidAPP;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.Service;
import com.adb.process.android.event.AndroidEvent;
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

    public AndroidEvent managerOfEvent(){return new AndroidEvent(this);}

    private AndroidDeviceCmd cmd = new AndroidDeviceCmd();

    @Override
    protected Process exec(String command, String[] envp, File dir) throws IOException {
        if(command.startsWith("adb")){
            command = command.replace("adb",prefix);
        }

        return super.exec(command, envp, dir);
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
