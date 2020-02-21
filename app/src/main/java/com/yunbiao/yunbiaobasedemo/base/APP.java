package com.yunbiao.yunbiaobasedemo.base;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.beardedhen.androidbootstrap.TypefaceProvider;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.tencent.smtt.sdk.QbSdk;
import com.yunbiao.yunbiaobasedemo.utils.data.SpUtils;
import com.yunbiao.yunbiaobasedemo.utils.exception.MyCrashHandler;

import java.util.HashSet;
import java.util.Set;

public class APP extends Application {

    private static APP instance;
    protected boolean isNeedCaughtExeption = false;
    private Set<Activity> allActivities;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (isNeedCaughtExeption) {
            cauchException();
        }
        TypefaceProvider.registerDefaultIconSets();//初始化androidbootstap
        initX5();
        initDownloader();
    }

    private void initDownloader() {
        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .commit();
    }

    private void initX5() {
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
                SpUtils.saveBoolean(APP.getContext(),SpUtils.X5SUCCESS,arg0);
            }
            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    // -------------------异常捕获-----捕获异常后重启系统-----------------//
    private void cauchException() {
        // 程序崩溃时触发线程
        MyCrashHandler mycrashHandler = new MyCrashHandler();
        Thread.setDefaultUncaughtExceptionHandler(mycrashHandler);
    }


    public static APP getContext() {
        return instance;
    }


    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }
    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}