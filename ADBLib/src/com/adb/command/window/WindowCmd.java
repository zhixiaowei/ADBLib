package com.adb.command.window;

public class WindowCmd {


    /**
     * afterSeconds秒后关机
     * @param afterSeconds
     * @return
     */
    public String shutdownAfterCmd(int afterSeconds){
        return "shutdown -s-t "+afterSeconds;
    }

    /**
     * 定时关机
     * @param hour
     * @param minite
     * @return
     */
    @Deprecated
    public String shutdownAtTimeCmd(int hour,int minite){
        return "at "+hour+":"+minite+" shutdow /s /f";
    }

    /**
     * 取消秒后关机
     * @return
     */
    public String cancelAfterShutdownCmd(){
        return "shutdown -a";
    }

    /**
     * 取消定时关机
     * @return
     */
    @Deprecated
    public String cancelAtTimeShutdownCmd(){
        return "at /delete";
    }





}
