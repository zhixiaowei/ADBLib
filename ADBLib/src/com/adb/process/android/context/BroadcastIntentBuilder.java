package com.adb.process.android.context;

public class BroadcastIntentBuilder extends IContext{

    public BroadcastIntentBuilder(String action){

        builder.append("adb shell am broadcast -a ");
        builder.append(action);
        builder.append(" ");
    }
}
