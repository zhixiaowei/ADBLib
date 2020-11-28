package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.event.AndroidEvent;
import com.adb.process.android.event.ClickEventManager;
import com.adb.process.android.event.KeyCode;

public class EventSample {
    public static void main(String[] args) {

        Device device = new ADBCtrl().firstDevice();
        if (device == null){
            System.out.println("找不到连接的设备");
        }

        AndroidEvent androidEvent = device.managerOfEvent();

//        //点击指定坐标
//        androidEvent.click(230,449);
//
//        //滑动
//        androidEvent.swipe(230,449,200,440);
//
//        androidEvent.inputKeyEvent(KeyCode.KEYCODE_BACK);//执行返回键

        //监听设备的点击/滑动/松开事件
        androidEvent.listenOfClickEvent(new ClickEventManager.IEventCallback() {
            @Override
            public void onCreateProcess(Process process) {
                //获取Process，并在需要的时候调用process结束任务
            }

            @Override
            public void onEventDown(int x, int y) {
                System.out.println("点击坐标：("+x+","+y+")");

//                androidEvent.click(x,y); //可以通过click点击同样位置，但不能在这个地方写，会导致死循环的
            }

            @Override
            public void onEventUp(int x, int y) {
                System.out.println("松开：("+x+","+y+")");
            }

            @Override
            public void onEventMove(int x, int y) {
                System.out.println("滑动坐标：("+x+","+y+")");
            }

            @Override
            public void onError(String msg) {
                //执行错误时，回调该方法
            }
        });
    }
}
