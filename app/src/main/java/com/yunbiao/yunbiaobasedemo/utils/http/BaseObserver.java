package com.yunbiao.yunbiaobasedemo.utils.http;

import android.widget.Toast;

import com.yunbiao.yunbiaobasedemo.base.APP;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T extends BaseResponse> implements Observer<T>, ISubscriber<T> {
    private Toast mToast;

    @Override
    public void onSubscribe(Disposable d) {
        doOnSubscribe(d);
    }

    @Override
    public void onNext(T t) {
        doOnNext(t);
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            setError("SocketTimeoutException");
        } else if (e instanceof ConnectException) {
            setError("ConnectException");
        } else if (e instanceof UnknownHostException) {
            setError("UnknownHostException");
        } else {
            String error = e.getMessage();
            doOnError(error);
        }
    }

    private void setError(String s) {
        showToast(s);
        doOnError(s);
        doOnNetError();
    }

    private void doOnNetError() {
        //弹框表明网络有问题
    }

    @Override
    public void onComplete() {
        doOnComplete();
    }

    private void showToast(String s) {
        if (mToast == null) {
            mToast = Toast.makeText(APP.getContext(), s, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(s);
        }
        mToast.show();
    }

}