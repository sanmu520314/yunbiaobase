package com.yunbiao.yunbiaobasedemo.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.RxFragment;

import org.greenrobot.eventbus.EventBus;


/**
 * @author ChayChan
 * @description: Fragment的基类
 * @date 2017/6/10  17:09
 */

public abstract class BaseFragment  extends RxFragment {


    private View rootView;

    protected Activity mActivity;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(provideContentViewId(),container,false);

            initView(rootView);
            loadData();
            initListener();
        } else {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    /**StateView的根布局，默认是整个界面，如果需要变换可以重写此方法*/
    public View getStateViewRoot() {
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    /**
     * 初始化一些view
     * @param rootView
     */
    public abstract void initView(View rootView) ;


    /**
     * 设置listener的操作
     */
    public abstract void initListener() ;


//    @Override
//    protected void onFragmentFirstVisible() {
//        //当第一次可见的时候，加载数据
//
//    }




    //得到当前界面的布局文件id(由子类实现)
    protected abstract int provideContentViewId();

    //加载数据
    protected abstract void loadData();

    @Override
    public void onDestroy() {
        super.onDestroy();

        rootView = null;
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

}
