package com.yunbiao.yunbiaobasedemo.utils.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by LiuShao on 2016/2/21.
 */
    public class SpUtils {

    private static SharedPreferences sp;
    private static final String SP_NAME = "yunbiaobasedemo";

    public static final String X5SUCCESS = "x5success";

    public static final String DEVICESERNUM = "YBserNo";//YB0000001
    public static final String DEVICENO = "deviceNo";//00000000-60c1-7db6-0000-0000564c36b6

    public static void saveString(Context context, String key, String value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getString(key, defValue);
    }

    public static void saveInt(Context context, String key, int value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getInt(key, value);
    }

    public static void saveFloat(Context context, String key, float value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key, float value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getFloat(key, value);
    }

    public static void saveLong(Context context, String key, long value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key, long value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getLong(key, value);
    }
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getBoolean(key, defValue);
    }


    public static String SceneList2String(List SceneList) {
        try {
            // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            // 然后将得到的字符数据装载到ObjectOutputStream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
            objectOutputStream.writeObject(SceneList);
            // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
            String SceneListString = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            // 关闭objectOutputStream
            objectOutputStream.close();

            return SceneListString;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List String2SceneList(String SceneListString) {
        try {
            byte[] mobileBytes = Base64.decode(SceneListString.getBytes(), Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            List SceneList = (List) objectInputStream.readObject();
            objectInputStream.close();
            return SceneList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
