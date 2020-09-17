package com.adb.process.android.logcat;

public class LogcatConfig{
    public static final String LEVEL_V = "v";//Verbose:输出全部日志
    public static final String LEVEL_D = "d";//Debug
    public static final String LEVEL_I = "i";//Info
    public static final String LEVEL_W = "w";//Warning
    public static final String LEVEL_E = "e";//Error
    public static final String LEVEL_F = "f";//Fatal
    public static final String LEVEL_S = "s";//Silent:不输出任何日志

    public static final String FORMAT_BRIEF = "brief";//<priority>/<tag>(<pid>): <message>
    public static final String FORMAT_PROCESS = "process";//<priority>(<pid>) <message>
    public static final String FORMAT_TAG = "tag";//<priority>/<tag>: <message>
    public static final String FORMAT_RAW = "raw";//<message>
    public static final String FORMAT_TIME = "time";//<datetime> <priority>/<tag>(<pid>): <message>
    public static final String FORMAT_LONG = "long";//[ <datetime> <pid>:<tid> <priority>/<tag> ]<message>

    public String cmd = "";
    public String grep = null;

    public LogcatConfig(String tag,String level,String format,String grep){

        if (tag == null||tag.isEmpty()){
            tag = "*";
        }

        if (level == null||level.isEmpty()){
            level = LEVEL_I;
        }

        if (format == null||format.isEmpty()){
            format = FORMAT_TIME;
        }

        this.grep = grep;

//        if (grep == null||grep.isEmpty()){
            cmd = "adb shell logcat -v "+format+" -s "+tag+":"+level;
//        }else{

//            cmd = "adb shell logcat -v "+format+" -s "+tag+":"+level+"|grep \""+grep+"\"";
//        }
    }
}