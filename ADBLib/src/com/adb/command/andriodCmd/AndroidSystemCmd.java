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

    /**
     * 滑屏
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public String swipe(int startX, int startY, int endX, int endY) {
        return "adb shell input swipe "+startX+" "+startY+" "+endX+" "+endY;
    }

    /**
     * 产品型号，如：Nexus 5
     * @return
     */
    public String productModel(){
        return "adb shell getprop ro.product.model";
    }

    /**
     * 获取电池信息
     * @return
     */
    public String getBatteryInfo() {
        return "adb shell dumpsys battery";
    }
}
