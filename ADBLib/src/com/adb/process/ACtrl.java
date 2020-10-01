package com.adb.process;

import java.io.*;
import java.nio.charset.Charset;

public abstract class ACtrl {

    protected Runtime runtime;
    protected String charset = "GBK";//编码格式
    protected boolean isPrintCmd = false;
    protected File runtimeDir = null;//运行位置,如adb的地址

    public ACtrl(){
        runtime = Runtime.getRuntime();
    }

    public ACtrl(String runtimePath){
        this();

        if (runtimePath != null && !runtimePath.trim().isEmpty()) {
            File temp = new File(runtimePath);
            if (!temp.exists()) {
                System.err.println("找不到该文件夹");
                this.runtimeDir = null;
            } else if (temp.isDirectory()) {
                this.runtimeDir = temp;
            } else{
                this.runtimeDir = temp.getParentFile();
            }
        }else{
            this.runtimeDir = null;
        }
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
     *
     * @param command
     * @param envp
     * @param dir
     * @return
     * @throws Exception
     */
    protected Process exec(String command, String[] envp, File dir) throws IOException {

        if (isPrintCmd){
            System.out.println(command);
        }

        return runtime.exec(command, envp, dir);
    }

    /**
     * 执行cmd
     * @param cmd
     * @return
     * @throws IOException
     */
    public String exec(String cmd) throws IOException {
        return getReply(exec(cmd,null,runtimeDir));
    }

    /**
     * 执行cmd并实时输出（适用于持续输出的命令，如日志）
     * @param cmd
     * @return
     * @throws IOException
     */
    public void execAPrint(String cmd) throws IOException {
        print(exec(cmd,null,runtimeDir));
    }

    /**
     * 执行并将输出保存，适用于日志
     * @param cmd 执行的命令
     * @param path 保存路径
     * @param isPrintOutput
     * @throws IOException
     */
    public void execASave(String cmd,String path,boolean isPrintOutput) throws IOException {
        save(exec(cmd,null,runtimeDir),path,isPrintOutput);
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

    /**
     * 执行cmd并实时输出（适用于持续输出的命令，如日志）
     * @param cmd
     * @return
     * @throws IOException
     */
    public void execAPrint(String cmd,String grep) throws IOException {
        if (isPrintCmd){
            System.out.println(cmd);
        }

        if (grep == null||grep.trim().isEmpty()){
            print(exec(cmd,null,null));
        }else{
            print(exec(cmd,null,null),grep);
        }

    }

    /**
     *
     * 由于 grep 的指令会导致输入流的处理出现堵塞，输出的数据会相对滞后于正常命令行的输出（肉眼可见），采用contains替代解决该问题
     * 该方案下运行数据与命令行输出的数据时间上相差无几
     *
     * 打印输出
     * @param process
     */
    private void print(Process process,String grep) {
        if (isPrintCmd){
            System.out.println("grep "+grep);
        }

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName(charset)));

            String line;

            while ((line=br.readLine())!=null) {
                if (line.contains(grep)){
                    System.out.println(line);
                }
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

    public void close(){
        if (runtime!=null){
            runtime.gc();
        }
    }

}
