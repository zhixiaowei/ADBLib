package com.adb.process.android.logcat;

import com.adb.command.android.AndroidLogcatCmd;
import com.adb.process.Device;
import com.adb.process.android.IAndroid;
import com.adb.process.android.app.ProcessInfo;
import com.adb.process.android.app.ProcessManager;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AndroidLogcat extends IAndroid {

    private AndroidLogcatCmd cmd = new AndroidLogcatCmd();
    private Process lastProcess;//最后一次的Process
    private ExecutorService fixedThreadPool= Executors.newFixedThreadPool(3);

    public AndroidLogcat(Device context) {
        super(context);
    }

    /**
     * 在命令行窗口正常
     * adb logcat -v time > [path]
     *
     * （小米）Android 10测试无效
     *暂时没有找到无法正常执行的原因,替代方案：save2WindowFile2()
     * @param path
     */
    @Deprecated
    public void save2WindowFile(String path){
        try{
            context.exec(cmd.logcat2WindowFile(path));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 为了替代adb指令无法正常指令的方案。
     * @param isPrint 保存的同时是否打印输出
     * @param path 保存路径
     * @param grep 日志关键字过滤
     */
    public void save2WindowFile2(boolean isPrint,String path,String grep){
        try{
            context.execASave(cmd.logcat(grep),path,isPrint);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存日志到Android设备
     * @param path 文件完整路径
     * @param tag 日志过滤的TAG
     */
    public boolean save2AndroidFile(String path,String tag){
        try {
            String reply = context.exec(cmd.logcat2AndroidFile(path, tag));
            return !reply.startsWith("error:");
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 打印日志
     * @param grep 过滤，为空时不过滤
     */
    public void print(String grep){
        try {
            context.execAPrint(cmd.logcat(grep));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listenLogcatByPackage(LogcatConfig config,IExecCallback callback){
        if (callback == null){
            return;
        }

        if (config == null||config.packageName == null||config.packageName.trim().isEmpty()){

        }

        //监听进程，并每5秒检查一下当前进程ID是否有所变化
        new ProcessManager(context).setListenerOfProcess(config.packageName, new ProcessManager.IListenerOfProcess() {
            @Override
            public void onChange(ProcessInfo info) {
                fixedThreadPool.execute(() -> {
                    context.exec(cmd.logcatByPid(info.pid), new IExecCallback() {
                        @Override
                        public void onCreatedProcess(Process process) {
                            lastProcess = process;
                            callback.onCreatedProcess(process);
                        }

                        @Override
                        public void onReplyLine(String str) {
                            callback.onReplyLine(str);
                        }

                        @Override
                        public void onErrorLine(String str) {
                            callback.onErrorLine(str);
                        }
                    });
                });
            }


            @Override
            public void onNoFoundPid() {
                if (lastProcess!= null){
                    lastProcess.destroy();
                    //判定进程已经被销毁，故而结果该Process，避免堵塞造成资源浪费
                    lastProcess = null;
                }
            }

            @Override
            public void onError(String info) {
                callback.onErrorLine(info);
            }
        },5);


    }

    /**
     * 根据PID打印日志和可以配合getPid使用
     * @param pid
     */
    public void printByPid(String pid){

        try {
            context.execAPrint(cmd.logcatByPid(pid));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void print(LogcatConfig config){
        try {
            context.execAPrint(config.cmd,config.grep);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
