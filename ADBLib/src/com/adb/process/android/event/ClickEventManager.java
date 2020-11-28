package com.adb.process.android.event;

public class ClickEventManager {
    private int mX = -1,mY = -1;

    private double mRateX,mRateY;
    private Event.EventType mEventType;
    private boolean isMove = false;
    private boolean isAvailableEvent = false;//是否是一次有效的事件（是否发生过X坐标或Y坐标变化
    private IEventCallback callback;

    public ClickEventManager(double rateX,double rateY,IEventCallback callback){
        this.mRateX = rateX;
        this.mRateY = rateY;
        this.callback = callback;
    }

    public void onEvent(String s){
        Event.EventType eventType = Event.getType(s);

        switch (eventType){
            case DOWN_X:
                int x = Event.getEventX(s,mRateX);

                this.isMove = this.mX != -1&&x != this.mX;//判定为滑动事件
                this.mX = x;
                this.isAvailableEvent = true;

                break;
            case DOWN_Y:
                int y = Event.getEventY(s,mRateY);

                this.isMove = this.mY != -1&& y != this.mY;//判定为滑动事件
                this.mY = y;
                this.isAvailableEvent = true;

                break;
            case DOWN://按下
            case UP://松开
                this.mEventType = eventType;
                break;
            case FINISH:
                //单个事件结束
                if (this.mEventType == Event.EventType.UP){
                    if (callback!= null){
                        callback.onEventUp(mX,mY);
                    }
                    this.mX = -1;
                    this.mY = -1;
                }else if (this.mEventType == Event.EventType.DOWN){
                    if (callback != null){
                        callback.onEventDown(mX,mY);
                    }

                }else if (this.isAvailableEvent&&this.isMove){
                    if (callback != null){
                        callback.onEventMove(mX,mY);
                    }
                }

                //状态复位
                this.isMove = false;
                this.mEventType = Event.EventType.UNKNOWN;
                this.isAvailableEvent = false;

                break;
            default:
                break;
        }
    }

    public interface IEventCallback{
        public void onCreateProcess(Process process);
        public void onEventDown(int x, int y);
        public void onEventUp(int x, int y);
        public void onEventMove(int x, int y);
        public void onError(String msg);
    }
}
