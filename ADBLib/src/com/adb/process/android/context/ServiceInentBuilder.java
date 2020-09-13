package com.adb.process.android.context;

public class ServiceInentBuilder extends IContext {

    private String packageName = "";
    private String canonicalName = "";

    public ServiceInentBuilder(String packageName, String canonicalName){

        this.packageName = packageName;
        this.canonicalName = canonicalName;

        builder.append("adb shell am startservice ");
    }

    @Override
    public Intent build() {
        if (!canonicalName.startsWith(packageName)){
            canonicalName = packageName+"."+canonicalName;
        }

        builder.append(packageName).append("/").append(canonicalName);
        return super.build();
    }
}
