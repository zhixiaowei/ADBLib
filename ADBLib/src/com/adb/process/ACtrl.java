package com.adb.process;

import java.io.*;
import java.nio.charset.Charset;

public abstract class ACtrl {

    protected Runtime runtime;
    protected String charset = "GBK";//编码格式
    protected boolean isPrintCmd = false;

    public ACtrl(){
        runtime = Runtime.getRuntime();
    }

    /**
     * 设置编码格式
     * @param charset
     */
    public void setCharset(String charset){
        if (charset!=null&&!charset.isEmpty()){
            this.charset = charset;
        }
    }

    /**
     * 是否打印执行的命令
     * @param isPrintCmd
     */
    public void isPrintCmd(boolean isPrintCmd){
        this.isPrintCmd = isPrintCmd;
    }

    /**
     * 对文件夹进行cmd操作
     *
     * @param command
     * @param envp
     * @param dir
     * @return
     * @throws Exception
     */
    protected String exec(String command, String[] envp, String dir) throws Exception {
        return getReply(runtime.exec(command, envp, new File(dir)));
    }

    /**
     * 执行cmd
     * @param cmd
     * @return
     * @throws IOException
     */
    public String exec(String cmd) throws IOException {
        if (isPrintCmd){
            System.out.println(cmd);
        }

        return getReply(runtime.exec(cmd));
    }

    /**
     * 执行cmd并实时输出（适用于持续输出的命令，如日志）
     * @param cmd
     * @return
     * @throws IOException
     */
    public void execAPrint(String cmd) throws IOException {
        if (isPrintCmd){
            System.out.println(cmd);
        }

        print(runtime.exec(cmd));
    }

    /**
     * 执行并将输出保存，适用于日志
     * @param cmd 执行的命令
     * @param path 保存路径
     * @param isPrintOutput
     * @throws IOException
     */
    public void execASave(String cmd,String path,boolean isPrintOutput) throws IOException {

        if (isPrintCmd){
            System.out.println(cmd);
        }

        save(runtime.exec(cmd),path,isPrintOutput);
    }

    /**
     * 打印输出
     * @param process
     */
    protected void print(Process process) {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(charset)));

            String line;

            while ((line=br.readLine())!=null) {
                System.out.println(line);
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName(charset)));
            String errorLine;
            while ((errorLine = error.readLine())!=null) {
                System.out.println(errorLine);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 保存及打印输出
     * @param process
     * @param path
     * @param isPrint
     */
    private void save(Process process,String path,boolean isPrint) {
        try{
            RandomAccessFile file = new RandomAccessFile(path,"rw");
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(charset)));

            file.seek(file.length());

            String line;
            while ((line=br.readLine())!=null) {
                if (isPrint){
                    System.out.println(line);
                }

                file.writeBytes(line+"\r\n");
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName(charset)));
            String errorLine;
            while ((errorLine = error.readLine())!=null) {

                if (isPrint){
                    System.out.println(errorLine+"\r\n");
                }

                file.writeBytes(errorLine);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取应答
     * @param process
     * @return
     */
    private String getReply(Process process){

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(charset)));
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line=br.readLine())!=null) {
                builder.append(line);
                builder.append("\r\n");
            }

            BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(),Charset.forName(charset)));
            String errorLine;

            while ((errorLine = error.readLine())!=null) {
                builder.append(errorLine);
                builder.append("\r\n");
            }

            return builder.toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public void close(){
        if (runtime!=null){
            runtime.gc();
        }
    }

}
