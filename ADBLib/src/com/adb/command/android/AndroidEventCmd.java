package com.adb.command.android;

public class AndroidEventCmd {

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
}
