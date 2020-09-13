package com.adb.process;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public abstract class ACtrl {

    private Runtime runtime;
    private String charset = "GBK";//编码格式

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
        return getReply(runtime.exec(cmd));
    }

    /**
     * 执行cmd并实时输出（适用于持续输出的命令，如日志）
     * @param cmd
     * @return
     * @throws IOException
     */
    public void execPrint(String cmd) throws IOException {
        print(runtime.exec(cmd));
    }

    private void print(Process process) {
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
