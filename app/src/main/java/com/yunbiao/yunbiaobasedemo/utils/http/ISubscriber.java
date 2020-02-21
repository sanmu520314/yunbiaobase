package com.yunbiao.yunbiaobasedemo.utils.http;

import io.reactivex.disposables.Disposable;

public interface ISubscriber<T extends BaseResponse> {
    void doOnSubscribe(Disposable d);

    void doOnError(String errorMsg);

    void doOnNext(T t);

    void doOnComplete();
}