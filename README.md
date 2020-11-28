# ADBLib

## ADBLib
一个封装了adb常用指令的纯Java库（不依赖于其他第三方库），可以方便的进行一些Android设备控制脚本的编写。


## 环境

- 适用于Window环境（其他系统环境未测试adb指令是否正常）
- ADB环境（需提前下载好ADB工具，无需配置ADB的系统环境变量）



## 调用

### 基本应用
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.AndroidSystem;
import com.adb.process.android.context.Activity;

public class CommonSample {
    public static void main(String[] args) {

        //如果已经配置过ADB环境变量，则可以
        ADBCtrl adb = new ADBCtrl();

        //在未配置ADB环境变量的情况下可以通过调用
        ADBCtrl adb1 = new ADBCtrl("E:\\tool\\adbDir\\");

        adb.setCharset("GBK");//设置编码格式，默认为GBK，可不设置
        adb.isPrintCmd(true);//是否打印执行的命令

        //获取已连接的设备
        Device[] device = adb.listDevices();

        //如果我们确定连接的设备只有一个或者操作任一台设备均可，无需进行选择时，也可以直接调用
        Device device1 = adb.firstDevice();

        AndroidSystem system = device1.managerOfSystem();
        system.listProcess(null);//获取系统进程等
        //详情可看 com.adb.sample.AndroidSystemSample

        Activity activity = device1.managerOfActivity();
        activity.start("com.hxw.test");
        //详情可看 com.adb.sample.ActivitySample

        //其他API可阅其他Sample
    }
}
```

### Event
```java
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

        //点击指定坐标
        androidEvent.click(230,449);

        //滑动
        androidEvent.swipe(230,449,200,440);

        androidEvent.inputKeyEvent(KeyCode.KEYCODE_BACK);//执行返回键

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

```

### Broadcast
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.BroadcastIntentBuilder;
import com.adb.process.android.context.IContext;

public class BroadcastSample {

    public static void main(String[] args) {

        Broadcast broadcast = new Broadcast(new ADBCtrl().firstDevice());

        IContext.Intent intent =
                new BroadcastIntentBuilder("com.hxw.test")//ACTION
                        .addExtra("数组",new int[]{1,2,3,4})
                        .addExtra("KeyOfString","i am string")
                        .addExtra("KeyOfBoolean",false)
                        .addExtra("KeyOfLong",Long.valueOf("12433333399"))
                        .build();
        broadcast.send(intent);//发送广播

        broadcast.send(Broadcast.BOOT_COMPLETED);//发送开机广播

        broadcast.send("com.hxw.hi");//发送指定Action的广播
    }
}
```


### Activity
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.ActivityIntentBuilder;
import com.adb.process.android.context.IContext;

public class ActivitySample {
    public static void main(String[] args) {
        Device context = new ADBCtrl().firstDevice();
        Activity activity = new Activity(context);

        //根据包名启动应用（进入默认Activity）
        activity.start("com.huangxiaowei.annotationtest");

        //启动指定Activity
        activity.start("com.hxw.test","com.hxw.test.MainActivity");

        //带参数启动Activity
        IContext.Intent intent = new ActivityIntentBuilder("android.intent.action.TestActivity",
                "com.huangxiaowei.annotationtest",
                "com.huangxiaowei.annotationtest.TestActivity")
                .addExtra("KEY","这是一个Value")
                .build();
        activity.start(intent);

    }
}

```
### 
### Service
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.android.context.IContext;
import com.adb.process.android.context.Service;
import com.adb.process.android.context.ServiceInentBuilder;

public class ServiceSample {

    public static void main(String[] args) {

        Service service = new Service(new ADBCtrl().listDevices()[0]);

        service.getAllRunningService(null);//获取正在运行的服务，参数为指定应用的包名

        //带参数启动服务
        IContext.Intent intent = new ServiceInentBuilder("com.hxw.test","com.hxw.test.MainService")
                .addExtra("KEY","VALUE").build();
        service.startService(intent);

        //无参数启动服务
        service.startService("com.hxw.test","com.hxw.test.MainService");

        //停止服务
        service.stopService("com.hxw.test","com.hxw.test.MainService");
    }
}
```


### Logcat
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.logcat.AndroidLogcat;
import com.adb.process.android.logcat.LogcatConfig;
import com.adb.process.android.logcat.LogcatConfigBuilder;

public class LogcatSample {
    public static void main(String[] args) {

        Device android = new ADBCtrl().firstDevice();
        AndroidLogcat logcat = android.managerOfLogcat();

        //多参数日志过滤
        LogcatConfig config = new LogcatConfigBuilder()
                .filterTAG("BindView")//设置TAG
                .showFormat(LogcatConfig.FORMAT_BRIEF)//输出格式
                .filterLevel(LogcatConfig.LEVEL_W)//设置输出的日志等级
                .filter("3")//关键字过滤
                .build();

        logcat.print(config);

        logcat.listenLogcat(config, new ACtrl.IExecCallback() {
                    @Override
                    public void onCreatedProcess(Process process) {
        
                    }
        
                    @Override
                    public void onReplyLine(String str) {
                        //实时获取日志处理
                    }
        
                    @Override
                    public void onErrorLine(String str) {
                        //返回的错误信息
                    }
                },false);//不打印日志，如果为true，则回调的同时打印输出日志

    }
}

```
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.logcat.AndroidLogcat;

public class LogcatSample {
    public static void main(String[] args) {

        Device android = new ADBCtrl().firstDevice();
        AndroidLogcat logcat = android.managerOfLogcat();

        String pid = android.managerOfApp().getPid("com.huangxiaowei.annotationtest");

        //将日志中TAG为"Test"的日志保存至设备
        logcat.save2AndroidFile("/mnt/sdcard/log.txt",pid);

       //将日志保存至window
        logcat.save2WindowFile2(true,"E:\\日志.log",pid);
    }
}
```


### App
```java
package com.adb.sample;

import com.adb.process.ADBCtrl;
import com.adb.process.Device;
import com.adb.process.android.app.AndroidAPP;

import java.io.IOException;

public class APPSample {
    public static void main(String[] args) throws IOException {

        Device device = new ADBCtrl().firstDevice();
        if (device == null){
            System.out.println("找不到连接的设备");
        }

        AndroidAPP appCtrl = device.managerOfApp();

        //获取APP列表
        String list = appCtrl.listApp();

        System.out.println(list);

        //卸载应用
        boolean uninstallResult = appCtrl.uninstall("com.taobao.idlefish");

        //安装应用，非debug签名的apk，如果已经安装，则重新安装
        boolean installResult = appCtrl.install("E:\\BaiduNetdiskDownload\\FiddlerSetup.apk",false,true);

        //强制停止应用
        appCtrl.stopApp("com.taobao.idlefish");

        //读取应用的信息
        String msg = appCtrl.readAppInfo("com.xiaomi.youpin");
        System.out.println(msg);

        //如果应用正在运行，获取该进程的ID
        String pid = appCtrl.getPid("com.taobao.idlefish");
    
        //监听进程ID变化（一般发生进程崩溃/重启才会导致进程变化）
        ProcessManager process = new ProcessManager(device);
        process.startListenerOfProcess("com.huangxiaowei.joke", new ProcessManager.IListenerOfProcess() {
            @Override
            public void onChange(ProcessInfo info) {
                System.out.println("监听到进程变化，"+info.pid);
            }

            @Override
            public void onNoFoundPid() {
                System.out.println("找不到该进程");
            }

            @Override
            public void onError(String info) {
                System.out.println("Error："+info);
                if (info.startsWith("error")){
                    process.finish();//结束轮询
                }

            }
        },5);



    }
}


```
### 其他可参考Sample
https://github.com/zhixiaowei/ADBLib/tree/master/src/com/adb/sample

### 核心代码解析


### ADB指令部分参考
https://github.com/mzlogin/awesome-adb


