package com.yunbiao.yunbiaobasedemo.utils.show;

import android.graphics.Color;

import com.github.johnpersano.supertoasts.SuperToast;
import com.yunbiao.yunbiaobasedemo.R;
import com.yunbiao.yunbiaobasedemo.base.APP;
import com.yunbiao.yunbiaobasedemo.utils.anim.YoYoUtil;

/**
 * Created by Weiping on 2015/11/30.
 */
public class ToastUtil {
    private static ToastUtil ourInstance = new ToastUtil();

    public static ToastUtil getInstance() {
        return ourInstance;
    }

    private ToastUtil() {
    }

    public void showToast(int text, int color) {
        SuperToast.cancelAllSuperToasts();
        SuperToast superToast = new SuperToast(APP.getContext());
        superToast.setAnimations(YoYoUtil.TOAST_ANIMATION);
        // 设置停留时间
        superToast.setDuration(SuperToast.Duration.SHORT);

        superToast.setTextColor(Color.parseColor("#ffffff"));
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        superToast.setText(APP.getContext().getResources().getString(text));
        superToast.setBackground(color);
        superToast.show();
    }

    public void showToast(String text, int color) {
        SuperToast.cancelAllSuperToasts();
        SuperToast superToast = new SuperToast(APP.getContext());
        superToast.setAnimations(YoYoUtil.TOAST_ANIMATION);
        superToast.setDuration(SuperToast.Duration.SHORT);
        superToast.setTextColor(Color.parseColor("#ffffff"));
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        superToast.setText(text);
        superToast.setBackground(color);
        superToast.show();
    }

    public void showRedToast(String text) {
        SuperToast.cancelAllSuperToasts();
        SuperToast superToast = new SuperToast(APP.getContext());
        superToast.setAnimations(YoYoUtil.TOAST_ANIMATION);
        superToast.setDuration(SuperToast.Duration.SHORT);
        superToast.setTextColor(Color.parseColor("#ffffff"));
        superToast.setTextSize(SuperToast.TextSize.SMALL);
        superToast.setText(text);
        superToast.setBackground(R.color.colorAccent);
        superToast.show();
    }
}
