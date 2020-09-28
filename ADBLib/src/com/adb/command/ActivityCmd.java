package com.adb.command;

public class ActivityCmd {
    /**
     *启动应用的Activity
     * @param packageName 包名
     * @param canonicalName 在包名下的地址
     * @return
     */
    public String startActivity(String packageName, String canonicalName){
        if (canonicalName == null||canonicalName.trim().isEmpty()){
            return "adb shell am start -n "+packageName;//部分设备不可用，不建议使用
        }else{
            return "adb shell am start -n "+packageName+"/"+canonicalName;//需要有intent-filter/Action/category
        }
    }

    /**
     *启动应用的Activity
     * @param packageName 包名
     * @return
     */
    public String startActivity(String packageName){
       return "adb shell monkey -p "+packageName+" -c android.intent.category.LAUNCHER 1";
    }

    /**
     * 获取启动
     * @param packageName
     * @param canonicalName
     * @return
     */
    public String getTimeOfStartActivity(String packageName,String canonicalName){
        return "adb shell am start -W "+packageName+"/"+canonicalName;
    }

    /**
     * 获取前台Activity
     * @return
     */
    public String getForegroundActivity(){
        return "adb shell dumpsys activity activities | grep \"mResumedActivity\"";
    }

}
