# ADBLib

## ADBLib
一个封装了adb常用指令的纯Java库（不依赖于其他第三方库），可以方便的进行一些Android设备控制脚本的编写。


## 环境

- 适用于Window环境（其他系统环境未测试adb指令是否正常）
- ADB环境



## 调用
### Broadcast
```java
package com.adb.sample;

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.Broadcast;
import com.adb.process.android.context.BroadcastIntentBuilder;
import com.adb.process.android.context.IContext;

public class BroadcastSample {
    
    public static void main(String[] args) {

        Broadcast broadcast = new Broadcast(new AndroidCtrl());

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

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.Activity;
import com.adb.process.android.context.ActivityIntentBuilder;
import com.adb.process.android.context.IContext;

public class ActivitySample {
    public static void main(String[] args) {
        AndroidCtrl context = new AndroidCtrl();
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

import com.adb.process.AndroidCtrl;
import com.adb.process.android.context.IContext;
import com.adb.process.android.context.Service;
import com.adb.process.android.context.ServiceInentBuilder;

public class ServiceSample {

    public static void main(String[] args) {

        AndroidCtrl context = new AndroidCtrl();
        Service service = new Service(context);

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
AndroidCtrl android = new AndroidCtrl();
AndroidLogcat logcat = android.managerOfLogcat();

//多参数日志过滤
LogcatConfig config = new LogcatConfigBuilder()
    .filterTAG("BindView")//设置TAG
    .showFormat(LogcatConfig.FORMAT_BRIEF)//输出格式
    .filterLevel(LogcatConfig.LEVEL_W)//设置输出的日志等级
    .filter("3")//关键字过滤
    .build();

logcat.print(config);
```
```java
String pid = android.managerOfApp().getPid("com.huangxiaowei.annotationtest");

//将日志保存至设备
logcat.save2AndroidFile("/mnt/sdcard/log.txt",pid);

//将日志保存至window
logcat.save2WindowFile2(true,"E:\\日志.log",pid);
```


### App
```java
AndroidAPP appCtrl = new AndroidAPP(new AndroidCtrl());

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

```
### 其他可参考Sample


