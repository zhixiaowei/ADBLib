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
