package com.yunbiao.yunbiaobasedemo.ui.activity;

import android.animation.Animator;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.yunbiao.yunbiaobasedemo.base.BaseActivity;
import com.yunbiao.yunbiaobasedemo.utils.show.ToastUtil;

import static com.yunbiao.yunbiaobasedemo.base.APP.getContext;

public class DemoActivity extends BaseActivity {
    @Override
    protected void initView() {

    }

    @Override
    public void initData() {

        XPopupDemo();
        YoyoDemo();


    }

    private void YoyoDemo() {
        YoYo.with(Techniques.Swing)
                .duration(2000).onEnd(new YoYo.AnimatorCallback(){

            @Override
            public void call(Animator animator) {
                //动画结束回调
            }
        })
                .repeat(3)
                .playOn(new View(this));
    }

    private void XPopupDemo() {

        new XPopup.Builder(getContext()).asConfirm("我是标题", "我是内容",
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        //suterToast
                        ToastUtil.getInstance().showRedToast("click confirm");
                    }
                })
                .show();

    }

    @Override
    public void initListener() {

    }

    @Override
    protected int provideContentViewId() {
        return 0;
    }
}
