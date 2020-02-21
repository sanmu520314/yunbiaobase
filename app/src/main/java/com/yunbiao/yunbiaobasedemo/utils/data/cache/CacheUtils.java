package com.yunbiao.yunbiaobasedemo.utils.data.cache;

import android.text.TextUtils;


import com.yunbiao.yunbiaobasedemo.afinel.FileConstants;

import java.io.File;


public class CacheUtils {
    private static final String CACHE_SAVE_PATH = FileConstants.PROPERTY_CACHE_PATH;

    private static ACache acache = ACache.get(new File(CACHE_SAVE_PATH));

    //设备编号
    private static final String MAC_INFO_CONSTANT = "MAC";
    private static final String SER_NUMBER = "ser_number";
    private static final String PWD = "pwd";
    private static final String ROTATE = "rotate";
    private static final String DEVICE_TYPE_CONSTANT = "DTYPE";

    private static final String USED_SD = "usedSd";//用户存储用的内置sd卡或者外置sd卡
    private static final String MECHINE_IP = "machine_ip";

    public  static void setDeviceNo(String mac) {
        acache.put(MAC_INFO_CONSTANT, mac);
    }

    public  static String getDeviceNo() {
        return acache.getAsString(MAC_INFO_CONSTANT);
    }

    public  static void putSerNumber(String serNumber) {
        acache.put(SER_NUMBER, serNumber);
    }
    public static String getSerNumber() {
        return acache.getAsString(SER_NUMBER);
    }

    public  static void putPwd(String pwd) {
        acache.put(PWD, pwd);
    }
    public static String getPwd() {
        return acache.getAsString(PWD);
    }

    public  static void putRotate(String rotate) {
        acache.put(ROTATE, rotate);
    }
    public static String getRotate() {
        return acache.getAsString(ROTATE);
    }


    public static void putDTypeKey (String dtype) {
        acache.put(DEVICE_TYPE_CONSTANT, dtype);
    }

    public static String getDType () {//0 未授权  1 网络版  2 单机版
        String s = acache.getAsString(DEVICE_TYPE_CONSTANT);
        String type ="";
        if(!TextUtils.isEmpty(s)){
            if(s.equals("0")){
                type = "(未授权)";
            }else if(s.equals("1")){
                type = "(网络版)";
            }else if(s.equals("2")){
                type= "(单机版)";
            }
        }
        return type;
    }

    public static void putSdPath (String sdPath) {
        acache.put(USED_SD, sdPath);
    }
    public static String getSdPath() {
        String path = acache.getAsString(USED_SD);
        if(!TextUtils.isEmpty(path)){
            return acache.getAsString(USED_SD);
        }
        return "0";
    }

    public static void putMachineIp(String machineip) {
        acache.put(MECHINE_IP, machineip);
    }
    public  static String getMechineIp() {
        return acache.getAsString(MECHINE_IP);
    }

}
