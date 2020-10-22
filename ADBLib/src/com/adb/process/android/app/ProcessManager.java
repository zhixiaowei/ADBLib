package com.adb.process.android.app;

import com.adb.process.Device;
import com.adb.process.android.IAndroid;
import com.adb.utils.StringUtils;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * 进程ID监听器，定时查询
 */
public class ProcessManager extends IAndroid{

    private ProcessInfo info = new ProcessInfo();
    private Timer timer;
    private TimerTask task;

    public ProcessManager(Device context) {
        super(context);
    }

    /**
     * 设置监听器，监听进程变化，在设备断开后会自动结束轮询，也可以通过调用finish的方法主动结束轮询
     * @param packageName 包名
     * @param listener 监听器
     * @param intervalOfSeconds 定期查询进程状态的间隔（秒）
     */
    public void startListenerOfProcess(String packageName, IListenerOfProcess listener,int intervalOfSeconds){

        info.packageName = packageName;

        if (listener == null){
            return;
        }

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                String reply = context.managerOfApp().getPid(packageName).trim();

                if (reply.isEmpty()){
                    listener.onNoFoundPid();
                }else if (!info.pid.equals(reply)){
                    if (StringUtils.isNumber(reply)){
                        info.pid = reply;
                        listener.onChange(info);
                    }else{
                        listener.onError(reply);

                        //设备断开，主动结束
                        if (reply.startsWith("error: no devices")&&reply.contains(context.deviceName)){
                            finish();
                        }
                    }
                }
            }
        };

        timer.schedule(task,0, TimeUnit.SECONDS.toMillis(intervalOfSeconds));
    }

    public void finish(){
        try {
            if (task != null){
                task.cancel();
                task = null;
                timer.cancel();
                task = null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface IListenerOfProcess{
        public void onChange(ProcessInfo info);
        public void onNoFoundPid();
        public void onError(String info);
    }
}
