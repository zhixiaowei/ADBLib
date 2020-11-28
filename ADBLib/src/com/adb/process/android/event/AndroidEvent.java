package com.adb.process.android.event;

import com.adb.command.android.AndroidEventCmd;
import com.adb.process.Device;
import com.adb.process.android.IAndroid;
import com.sun.glass.ui.Size;

public class AndroidEvent extends IAndroid {

    private AndroidEventCmd cmd = new AndroidEventCmd();

    public AndroidEvent(Device context) {
        super(context);
    }

    /**
     *  Android 10测试可行
     *
     * 点击指定坐标
     * @param x
     * @param y
     * @return
     */
    public boolean click(int x,int y){
        try{
            String reply = exec(cmd.click(x,y));
            if (reply!=null&&reply.trim().isEmpty()){
                //正常执行的情况下是无应答的
                return true;
            }else{
                System.out.println(reply);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 滑屏
     * 参数 300 1000 300 500 分别表示起始点x坐标 起始点y坐标 结束点x坐标 结束点y坐标
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public boolean swipe(int startX,int startY,int endX,int endY){
        try{
            String reply = exec(cmd.swipe(startX,startY,endX,endY));

            if (reply!=null&&reply.trim().isEmpty()){
                //正常执行的情况下是无应答的
                return true;
            }else{
                System.out.println(reply);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Android 10 返回键测试可行
     *
     * 虚拟按键控制
     * @param androidKeyEvent 有效参数见：lib.process.android.AndroidKeyEvent
     * @return
     */
    public boolean inputKeyEvent(int androidKeyEvent){
        try{
            String reply = exec(cmd.inputKeyEvent(androidKeyEvent));

            if (reply!=null&&reply.trim().isEmpty()){
                //正常执行的情况下是无应答的
                return true;
            }else{
                System.out.println(reply);
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    /**
     * 监听Android的点击事件
     * @param callback
     */
    public void listenOfClickEvent(ClickEventManager.IEventCallback callback){

        if (callback == null){
            return;
        }

        try {
            Size mScreenSize = context.managerOfHardware().getScreenSize();
            Size mEventSize = Event.getEventSize(context);

            double rateX = mScreenSize.width*1.0/mEventSize.width;//event体系中的X坐标乘以该值可以转为屏幕的x坐标
            double rateY = mScreenSize.height*1.0/mEventSize.height;//event体系中的y坐标乘以该值可以转为屏幕的y坐标

            ClickEventManager mClickEventCtrl = new ClickEventManager(rateX, rateY,callback);

            context.exec("adb shell getevent", new IExecCallback() {
                @Override
                public void onCreatedProcess(Process process) {
                    callback.onCreateProcess(process);
                }

                @Override
                public void onReplyLine(String s) {
                    mClickEventCtrl.onEvent(s);
                }

                @Override
                public void onErrorLine(String s) {
                    System.err.println(s);
                    callback.onError(s);
                }
            },"",false);

        }catch (Exception e){
            e.printStackTrace();
            callback.onError(e.getMessage());
        }
    }
}
