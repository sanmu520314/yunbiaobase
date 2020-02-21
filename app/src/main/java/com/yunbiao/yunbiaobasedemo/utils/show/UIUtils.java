package com.yunbiao.yunbiaobasedemo.utils.show;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yunbiao.yunbiaobasedemo.R;
import com.yunbiao.yunbiaobasedemo.base.APP;

/**
 * Created by Administrator on 2019/4/18.
 */

public class UIUtils {

    private static Toast mToast;

    public static void show(final Activity context, final String message, final int time){
        if(context == null){
            return;
        }

        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mToast != null){
                    mToast.cancel();
                }

                mToast = new Toast(context);
                View toastView = View.inflate(context, R.layout.layout_toast, null);
                TextView tvToast = (TextView) toastView.findViewById(R.id.tv_toast);
                tvToast.setText(message);
                mToast.setView(toastView);
                mToast.setGravity(Gravity.CENTER,0,0);
                mToast.setDuration(time);
                mToast.show();
            }
        });
    }

    public static void showShort(Activity context,String message){
        show(context,message, 5 * 1000);
    }

    public static void showLong(Activity context,String message){
        show(context,message, 10 * 1000);
    }

    private static Toast mTipsToast;
    public static void showTitleTip(String title) {
        if(mTipsToast != null){
            mTipsToast.cancel();
            mTipsToast = null;
        }
        int padding = 20;
        TextView textView = new TextView(APP.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextSize(36);
        textView.setText(title);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(padding,padding,padding,padding);
        textView.setGravity(Gravity.CENTER);

        mTipsToast = new Toast(APP.getContext());
        mTipsToast.setDuration(Toast.LENGTH_LONG);
        mTipsToast.setGravity(Gravity.CENTER, 0, 0);
        mTipsToast.setView(textView);
        mTipsToast.show();
    }
    public static void showMeetingWarnTip(String title) {
        if(mTipsToast != null){
            mTipsToast.cancel();
            mTipsToast = null;
        }
        int padding = 20;
        TextView textView = new TextView(APP.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setTextSize(50);
        textView.setText(title);
        textView.setTextColor(APP.getContext().getResources().getColor(R.color.main_blue));
        textView.setPadding(padding,padding,padding,padding);
        textView.setGravity(Gravity.CENTER);

        mTipsToast = new Toast(APP.getContext());
        mTipsToast.setDuration(Toast.LENGTH_LONG);
        mTipsToast.setGravity(Gravity.CENTER, 0, 0);
        mTipsToast.setView(textView);
        mTipsToast.show();
    }

    public static void showMeetingTip(String title) {
        if(mTipsToast != null){
            mTipsToast.cancel();
            mTipsToast = null;
        }
        int padding = 20;
        TextView textView = new TextView(APP.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setTextSize(50);
        textView.setText(title);
        textView.setTextColor(APP.getContext().getResources().getColor(R.color.main_orange));
        textView.setPadding(padding,padding,padding,padding);
        textView.setGravity(Gravity.CENTER);

        mTipsToast = new Toast(APP.getContext());
        mTipsToast.setDuration(Toast.LENGTH_LONG);
        mTipsToast.setGravity(Gravity.CENTER, 0, 0);
        mTipsToast.setView(textView);
        mTipsToast.show();
    }
    public static void showProgressTip(String title,String tip) {
        if(mTipsToast != null){
            mTipsToast.cancel();
            mTipsToast = null;
        }
        ProgressBar pd = new ProgressBar(APP.getContext());
        pd.setIndeterminate(false);
//        pd.setCancelable(true);
//        pd.setTitle(title);
//        pd.setMessage(tip);
//

        mTipsToast = new Toast(APP.getContext());
        mTipsToast.setDuration(Toast.LENGTH_LONG);
        mTipsToast.setGravity(Gravity.CENTER, 0, 0);
        mTipsToast.setView(pd);
        mTipsToast.show();
    }
}
