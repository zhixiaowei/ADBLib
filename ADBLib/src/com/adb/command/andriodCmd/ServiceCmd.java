package com.adb.command.andriodCmd;

public class ServiceCmd {

    /**
     *启动应用的Activity
     * @param packageName 包名
     * @param canonicalName 在包名下的地址
     * @return
     */
    public String startService(String packageName,String canonicalName){
        return "adb shell am startservice -n "+packageName+"/"+canonicalName;
    }

    /**
     * 获取所有正在运行的服务
     * @param packageName
     * @return
     */
    public String getAllRunningService(String packageName) {

        if (packageName==null||packageName.isEmpty()){
            return "adb shell dumpsys activity services";
        }else{
            return "adb shell dumpsys activity services "+packageName;
        }

    }

    /**
     * 停止服务
     * @param packageName
     * @param canonicalName
     * @return
     */
    public String stopService(String packageName, String canonicalName) {
        return "adb shell am stopservice "+packageName+"/"+canonicalName;
    }
}
