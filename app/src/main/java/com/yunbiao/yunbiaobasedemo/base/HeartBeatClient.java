package com.yunbiao.yunbiaobasedemo.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;


import com.yunbiao.yunbiaobasedemo.utils.CommonUtils;
import com.yunbiao.yunbiaobasedemo.utils.data.SpUtils;
import com.yunbiao.yunbiaobasedemo.utils.data.cache.LayoutCache;

import java.util.UUID;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class HeartBeatClient {
    private static final String TAG = "HeartBeatClient";
    /**
     * 心跳频率 默认10s
     */
    private static HeartBeatClient hbc = null;

    private static String sbDeviceId = null;
    public static synchronized HeartBeatClient getInstance() {
        if (hbc == null) {
            hbc = new HeartBeatClient();
        }
        return hbc;
    }

    private HeartBeatClient() {
        initDeviceNo();
    }

    /**
     * 获取设备唯一编号
     *
     * @return
     */
    public static String getDeviceNo() {
        sbDeviceId = LayoutCache.getDeviceNo();
        return sbDeviceId;
    }

    /**
     * 重新初始化设备id，需要获取到设备id
     */
    public static void initDeviceNo() {
        if (sbDeviceId == null || sbDeviceId.equals("-1") || TextUtils.isEmpty(sbDeviceId)) {
            createDeviceNo();
        }
    }

    private static void createDeviceNo() {

        String deviceNo = getMacAddress(5);

        if (!deviceNo.equals("-1")) {
            SpUtils.saveString(APP.getContext(), SpUtils.DEVICENO, deviceNo);
        }

    }

    public static String getAndroidId() {
        APP context = APP.getContext();
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmPhone, androidId;// tmSerial,
        tmDevice = "" + tm.getDeviceId();
        androidId = "" + Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32));// | tmSerial.hashCode());
        tmPhone = deviceUuid.toString();
        return tmPhone;
    }

    /**
     * 多种方法获取wifimac，防止出厂从未开启wifi导致无法获取wifimac
     * 从而引起因mac不一致导致出现资源未下载或设备号改变问题
     * @param restartNum//重复次数
     * @return
     */
    @SuppressLint("HardwareIds")
    public static String getMacAddress(int restartNum) {
        String macAddress = "";
        final WifiManager wifiManager = (WifiManager) APP.getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = (null == wifiManager ? null : wifiManager.getConnectionInfo());
        assert wifiManager != null;
        boolean isActOpen=false;//是否是程序主动打开
        if (!wifiManager.isWifiEnabled()) {//必须先打开，才能获取到MAC地址
            wifiManager.setWifiEnabled(true);
            isActOpen=true;
        }
        if (null != info) {
            //循环几次获取mac，防止首次没打开过wifi时获取不到wifimac
            for (int index = 0; index <restartNum ; index++) {
                if(index!=0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                macAddress= CommonUtils.getWifiMacAddress();
                if (TextUtils.isEmpty(macAddress)){
                    macAddress = info.getMacAddress();
                }
                if (macAddress != null && macAddress.equals("02:00:00:00:00:00")) {//6.0及以上系统获取的mac错误
                    macAddress = CommonUtils.getSixOSMac();
                    Log.e("mac","6.0wifi mac:"+macAddress);
                }
                if (!TextUtils.isEmpty(macAddress)){
                    break;
                }
            }
        }
        if (isActOpen){
            //延时关闭，防止有时关闭不了wifi
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    wifiManager.setWifiEnabled(false);
                }
            },300);
        }
        Log.e("mac","wifi mac:"+macAddress);
        if (TextUtils.isEmpty(macAddress)) {
            macAddress = CommonUtils.getLocalMacAddress();
            Log.e("mac","local mac:"+macAddress);
        }

        String mac = macAddress.toUpperCase();
        String macS = "";
        for (int i = mac.length() - 1; i >= 0; i--) {
            macS += mac.charAt(i);
        }
        UUID uuid2 = new UUID(macS.hashCode(), mac.hashCode());
        Log.e("mac","uuid2:"+uuid2.toString());
        return uuid2.toString();
    }
}
