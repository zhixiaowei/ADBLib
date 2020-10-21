package com.adb.process.android.logcat;

public class LogcatConfigBuilder {

    private String tag = null;
    private String level = null;
    private String format = null;
    private String filter = null;
    private String packageName = null;

    /**
     * 根据TAG过滤
     * @param tag
     * @return
     */
    public LogcatConfigBuilder filterTAG(String tag){
        this.tag = tag;
        return this;
    }

    /**
     * 根据日志Level过滤
     * @param level
     * @return
     */
    public LogcatConfigBuilder filterLevel(String level){
        this.level = level;
        return this;
    }

    /**
     * 设置日志输出的格式
     * @param format
     * @return
     */
    public LogcatConfigBuilder showFormat(String format){
        this.format = format;
        return this;
    }

    public LogcatConfigBuilder filterPackage(String packageName){
        this.packageName =packageName;
        return this;
    }

    /**
     * 关键字过滤
     * @param grep
     * @return
     */
    public LogcatConfigBuilder filter(String grep){
        this.filter = grep;
        return this;
    }

    public LogcatConfig build(){
        return new LogcatConfig(packageName,tag,level,format,filter);
    }
}
