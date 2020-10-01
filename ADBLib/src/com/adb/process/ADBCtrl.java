package com.adb.process;

import com.adb.command.ADBCmd;

public class ADBCtrl extends ACtrl{

    private Device[] devices;

    /**
     * 在已经配置adb环境变量的情况下，调用该方法
     */
    public ADBCtrl(){
        super();
    }

    /**
     *
     * @param adbDir ADB所在文件夹
     */
    public ADBCtrl(String adbDir){
        super(adbDir);
    }


    ADBCmd cmd = new ADBCmd();

    @Override
    public void setCharset(String charset) {
        super.setCharset(charset);

        if (devices!=null){
            for (Device device:devices){
                device.setCharset(charset);
            }
        }
    }

    @Override
    public void isPrintCmd(boolean isPrintCmd) {
        super.isPrintCmd(isPrintCmd);

        if (devices!=null){
            for (Device device:devices){
                device.isPrintCmd(isPrintCmd);
            }
        }
    }



    public String queryADBVersion(){
        try{
            return exec(cmd.version());
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
    public Device[] listDevices(){
        try{
            String msg = exec(cmd.devices());
            String[] list = msg.split("\r\n");
            devices = new Device[list.length - 1];

            if (list[0].equalsIgnoreCase("List of devices attached")){
                for (int i = 1;i<list.length;i++){
                    String deviceName = list[i].split("\t")[0].trim();
                    devices[i-1] = new Device(this,deviceName);
                }

                return devices;
            }else{
                System.out.println("deviceAttached:"+msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Device firstDevice(){
        Device[] list = listDevices();
        if (list != null) {
            return list[0];
        }else{
            return null;
        }
    }
}
