package com.adb.command.andriodCmd;

public class AndroidSystemCmd {

    /**
     * 点击屏幕
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @return 命令行
     */
    public String click(int x, int y) {
        return "adb shell input tap " + x + " " + y;
    }

    /**
     * 查询连接设备
     * @return
     */
    public String devices(){
        return "adb devices";
    }

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
     * 获取启动
     * @param packageName
     * @param canonicalName
     * @return
     */
    public String getTimeOfStartActivity(String packageName,String canonicalName){
        return "adb shell am start -W "+packageName+"/"+canonicalName;
    }

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
     * 获取进程列表
     * @return
     */
    public String listProcess(String grep){
        if (grep == null||grep.isEmpty()){
            return "adb shell ps";
        }else{
            return "adb shell ps|grep "+grep;
        }
    }

    /**
     * 截屏并保存到window，部分设备不支持（6.0以上设备不可行）
     * @param windowPath
     * @return
     *
     * 由于部分设备不支持，建议采用screenshot2AndroidFile然后再复制到电脑端
     */
    public String screenshot2WindowFile(String windowPath){
        return "adb exec-out screencap -p > "+windowPath;
    }

    /**
     * 截屏并保存到android
     * @param androidPath
     * @return
     */
    public String screenshot2AndroidFile(String androidPath){
        return "adb shell screencap -p "+androidPath;
    }

    /**
     * 模拟按键
     * @param event
     * @return
     */
    public String inputKeyEvent(int event){
        return "adb shell input keyevent "+event;
    }
}
