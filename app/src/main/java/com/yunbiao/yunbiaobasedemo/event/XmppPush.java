package com.yunbiao.yunbiaobasedemo.event;

/**
 * Created by Administrator on 2018/11/27.
 */

public class XmppPush {
    private String message;

    public XmppPush(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
