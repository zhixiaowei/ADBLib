package com.adb.process.android.event;

import com.adb.process.Device;
import com.sun.glass.ui.Size;

import java.io.IOException;

public class Event {

    public enum EventType{
        UNKNOWN,//未知
        DOWN,//按下
        UP,//松开
        DOWN_X,//触发X坐标变化
        DOWN_Y,//触发Y坐标变化
        FINISH//事件结束
    }

    public static EventType getType(String s){
        if (s.contains("0003 0036")){
            return EventType.DOWN_Y;
        }else if (s.contains("0003 0035")){
            return EventType.DOWN_X;
        }else if(s.contains("0001 014a 00000001")){
            //点击
            return EventType.DOWN;
        }else if(s.contains("0001 014a 00000000")){
            //松开
            return EventType.UP;
        }else if(s.contains("0000 0000 00000000")){
            //单个事件结束
            return EventType.FINISH;
        }else{
            return EventType.UNKNOWN;
        }
    }

    /**
     * 处理内容 /dev/input/event4: 0003 0035 000002e3
     *  000002e3为event体系中X坐标的十六进制，我们需要转为十进制，然后转为屏幕的X坐标
     * @param s
     * @param mRateX
     * @return
     */
    public static int getEventX(String s, double mRateX) {
        return (int) (Integer.parseInt(s.substring(s.indexOf("0035")+4).trim(),16)*mRateX);
    }

    /**
     * 处理内容为：/dev/input/event4: 0003 0036 0000053f
     *  0000053f为event体系中Y坐标的十六进制，我们需要转为十进制，然后转为屏幕的Y坐标
     * @param s
     * @param mRateY
     * @return
     */
    public static int getEventY(String s,double mRateY){
        return (int) (Integer.parseInt(s.substring(s.indexOf("0036")+4).trim(),16)*mRateY);
    }

    /**
     * 获取点击event体系中的宽高（最大x值及y值
     *
     * @param device
     * @return
     * @throws IOException
     */
    public static Size getEventSize(Device device) throws IOException {

        /**
         * 返回内容：
         *   0035  : value 0, min 0, max 1079, fuzz 0, flat 0, resolution 0
         *   0036  : value 0, min 0, max 2339, fuzz 0, flat 0, resolution 0
         */
        String msg = device.exec("adb shell getevent -p | grep -e 0035 -e 0036");

        Size size = new Size();
        int index = msg.indexOf("max ") + 4;
        size.width = Integer.parseInt(msg.substring(index,msg.indexOf(",",index)));
        int index2 = msg.indexOf("max ",index)+4;
        size.height = Integer.parseInt(msg.substring(index2,msg.indexOf(",",index2)));

        return size;
    }
}
