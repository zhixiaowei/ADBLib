package com.adb.process.android.context;

public class ActivityIntentBuilder extends IContext{

    private String packageName = "";
    private String canonicalName = "";

    /**
     *
     * @param action Activity的Action
     * @param packageName 包名
     * @param canonicalName Activity的完整类地址
     */
    public ActivityIntentBuilder(String action, String packageName, String canonicalName){
        this.packageName = packageName;
        this.canonicalName = canonicalName;

        builder.append("adb shell am start -a ").append(action).append(" ");
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
