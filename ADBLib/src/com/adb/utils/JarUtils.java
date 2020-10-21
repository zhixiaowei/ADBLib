package com.adb.utils;

public class JarUtils {

    /**
     * 判断当前是否是作为一个jar包
     * @return
     */
    public static boolean isJar() {
        return JarUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath().endsWith(".jar");
    }

    /**
     * 获取当前Jar包的绝对地址（如果当前不为jar包，则返回的地址为当前module的绝对地址）
     * @return
     */
    public static String getJarPath(){
        return JarUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }

}
