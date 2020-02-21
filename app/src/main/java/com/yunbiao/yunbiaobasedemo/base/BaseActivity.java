package com.yunbiao.yunbiaobasedemo.base;

import android.app.Activity;

import android.os.Bundle;

import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.Nullable;

    import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yunbiao.yunbiaobasedemo.MainActivity;
import com.yunbiao.yunbiaobasedemo.utils.MyCountDownTimer;


import org.greenrobot.eventbus.EventBus;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;


/**
 * @author ChayChan
 * @description: activity的基类
 * @date 2017/6/10  16:42
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    private static long mPreTime;
    private static Activity mCurrentActivity;// 对所有activity进行管理
    public static List<Activity> mActivities = new LinkedList<Activity>();
    protected Bundle savedInstanceState;
    private String currentClassName;

    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentClassName = this.getClass().getName();
        this.savedInstanceState = savedInstanceState;
        mCurrentActivity = this;
//        //初始化的时候将其添加到集合中
        synchronized (mActivities) {
            mActivities.add(this);
        }
        if (currentClassName.contains("full")){
            finishOthers(mCurrentActivity.getClass());
        }
        //子类不再需要设置布局ID，也不再需要使用ButterKnife.bind()
        setContentView(provideContentViewId());
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }
    protected  abstract  void initView() ;

    public abstract void initData();

    public abstract  void initListener();

    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    @Override
    protected void onResume() {
        super.onResume();
        mTimer.cancel();
        mTimer.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentActivity = null;
        mTimer.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁的时候从集合中移除
        synchronized (mActivities) {
            mActivities.remove(this);
        }
    }
    /**
     * 退出应用的方法
     */
    public static void exitApp() {
        ListIterator<Activity> iterator = mActivities.listIterator();
        while (iterator.hasNext()) {
            Activity next = iterator.next();
            next.finish();
        }
    }
    public static Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    /**
     * 统一退出控制
     */
    @Override
    public void onBackPressed() {
        if (mCurrentActivity instanceof MainActivity){
            //如果是主页面
            if (System.currentTimeMillis() - mPreTime > 2000) {// 两次点击间隔大于2秒
//                UIUtils.showToast("再按一次，退出应用");
                mPreTime = System.currentTimeMillis();
                return;
            }
        }
        super.onBackPressed();// finish()
    }

    public boolean isEventBusRegisted(Object subscribe) {
        return EventBus.getDefault().isRegistered(subscribe);
    }

    public void registerEventBus(Object subscribe) {
        if (!isEventBusRegisted(subscribe)) {
            EventBus.getDefault().register(subscribe);
        }
    }

    public void unregisterEventBus(Object subscribe) {
        if (isEventBusRegisted(subscribe)) {
            EventBus.getDefault().unregister(subscribe);
        }
    }


    private static  int READ_PHONE_STATE=1;



    MyCountDownTimer mTimer = new MyCountDownTimer(30000* 1000, 1000) {//90
        @Override
        public void onTick(long millisUntilFinished) {
//            Log.e(TAG, "onTick----------------------->" + millisUntilFinished);
        }

        @Override
        public void onFinish() {

            finishOthers(null);
//            System.gc();
//            Log.e(TAG, "Finish: ");
        }
    };

    /**
     * finish所有其它Activity
     */
    public static void finishOthers(Class<? extends Activity> activity) {
        finish(activity);
    }

    public static void finish(Class<? extends Activity> currentActivity) {
        for (Iterator<Activity> iterator = mActivities.iterator(); iterator.hasNext(); ) {
            Activity activity = iterator.next();
            if (activity.getClass() == currentActivity) {
                continue;
            }
            iterator.remove();
            activity.finish();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
            if (ev.getAction() == 0) {
                Log.e(TAG, "重新计时");
                mTimer.cancel();
                mTimer.start();
            }
        return super.dispatchTouchEvent(ev);
    }

}
