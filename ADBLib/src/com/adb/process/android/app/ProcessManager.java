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
    private Timer timer = new Timer();
    private TimerTask task;

    public ProcessManager(Device context) {
        super(context);
    }

    /**
     * 设置监听器，监听进程变化
     * @param packageName 包名
     * @param listener 监听器
     * @param intervalOfSeconds 定期查询进程状态的间隔（秒）
     */
    public void setListenerOfProcess(String packageName, IListenerOfProcess listener,int intervalOfSeconds){

        info.packageName = packageName;

        if (listener == null){
            return;
        }

        task = new TimerTask() {
            @Override
            public void run() {
                String pid = context.managerOfApp().getPid(packageName).trim();

                if (pid.isEmpty()){
                    listener.onNoFoundPid();
                }else if (!info.pid.equals(pid)){
                    if (StringUtils.isNumber(pid)){
                        info.pid = pid;
                        listener.onChange(info);
                    }else{
                        listener.onError(pid);
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
