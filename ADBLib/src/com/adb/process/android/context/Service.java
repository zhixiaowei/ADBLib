package com.adb.process.android.context;

import com.adb.command.android.app.ServiceCmd;
import com.adb.process.Device;
import com.adb.process.android.IAndroid;

import java.io.IOException;

public class Service extends IAndroid {

    private ServiceCmd cmd = new ServiceCmd();

    public Service(Device context) {
        super(context);
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
            String msg = context.exec(cmd.startService(packageName, canonicalName));
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
            String msg = context.exec(intent.cmd);
            System.out.println(msg);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取所有正在运行的服务
     * @param packageName 可为空，如果为空，则返回所有应用的所有服务
     * @return
     */
    public String getAllRunningService(String packageName){
        try {
           return context.exec(cmd.getAllRunningService(packageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 停止服务
     * @param packageName
     * @param canonicalName
     * @return
     */
    public boolean stopService(String packageName,String canonicalName){
        try {
            context.exec(cmd.stopService(packageName,canonicalName));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
