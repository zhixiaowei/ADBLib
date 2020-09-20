package com.adb.process.android.context;

public class BroadcastIntentBuilder extends IContext{

    /**
     * 无差别广播
     * @param action
     */
    public BroadcastIntentBuilder(String action){
        builder.append("adb shell am broadcast -a ")
               .append(action).append(" ");
    }

    /**
     * 限定接收器的地址
     *
     * @param action 广播Action
     * @param packageName 接收器的包名
     * @param canonicalName 接收器的完整类路径
     */
    public BroadcastIntentBuilder(String action,String packageName, String canonicalName){
        this(action);

        builder.append("-n ").append(packageName).append("/").append(canonicalName);
    }
}
