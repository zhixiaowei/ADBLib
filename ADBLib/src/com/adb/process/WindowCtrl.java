package com.adb.process;

import com.adb.command.window.WindowCmd;

public class WindowCtrl extends ACtrl {

    WindowCmd cmd = new WindowCmd();


    /**
     * 打开资源管理器，并进入指定资源文件夹
     */
    public void openFileManager(String path){
        try{
            exec("explorer.exe "+path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 打开Cmd框
     */
    public void openCmdView(){
        try{
            exec("cmd /c start");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 无法关闭之前打开的小黑框
     */
//    public void exitCmdView(){
//        try{
//            exec("cmd /c exit");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    /**
     * 打开记事本
     */
    public void openNotepad(){
        try{
            exec("cmd /c notepad");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 秒后关机
     * @param seconds
     */
    public void shutdown(int seconds){
        try{
            exec(cmd.shutdownAfterCmd(seconds));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 取消秒后关机
     */
    public void cancelShutdown(){
        try{
            exec(cmd.cancelAfterShutdownCmd());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取文件树[tree]
     * @param path
     */
    public String fileTree(String path){

        try{
            return exec("cmd /c tree",null,path);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 获取文件夹目录[dir]
     * @param path
     */
    public String fileDir(String path){

        try{
            return exec("cmd /c dir",null,path);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
