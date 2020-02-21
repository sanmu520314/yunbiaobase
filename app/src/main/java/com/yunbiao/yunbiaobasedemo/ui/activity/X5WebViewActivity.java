package com.yunbiao.yunbiaobasedemo.ui.activity;

import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tencent.smtt.sdk.TbsReaderView;
import com.yunbiao.yunbiaobasedemo.R;
import com.yunbiao.yunbiaobasedemo.base.BaseActivity;
import com.yunbiao.yunbiaobasedemo.ui.views.TBSWebView;


import java.io.File;

import butterknife.BindView;


public class X5WebViewActivity extends BaseActivity {


    @BindView(R.id.x5WebView)
    TBSWebView x5WebView;

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    private String url;
    TbsReaderView tbsReaderView;
    //是否打开文件
    private boolean isOpenFile;

    @Override
    protected void initView() {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        x5WebView.addJavascriptInterface(new JsObject(), "android");

        url = getIntent().getStringExtra("url");
        isOpenFile = getIntent().getBooleanExtra("isOpenFile", false);
//        x5WebView.loadUrl(url);
        handler.sendEmptyMessageDelayed(1001, 500);
        tbsReaderView = new TbsReaderView(this, readerCallback);
        //打开文件
        if (!TextUtils.isEmpty(url) && isOpenFile) {
            handler.removeMessages(10001);
            rlRoot.addView(tbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            openFile();
        }

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void openFile() {
        File file = new File(url);
        if (!file.exists()) {
            Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
        }
        Bundle bundle = new Bundle();
        bundle.putString("filePath", url);
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = tbsReaderView.preOpen(parseFormat(parseName(url)), false);
        if (result) {
            tbsReaderView.openFile(bundle);
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private String parseName(String url) {
        String fileName = null;
        try {
            fileName = url.substring(url.lastIndexOf("/") + 1);
        } finally {
            if (TextUtils.isEmpty(fileName)) {
                fileName = String.valueOf(System.currentTimeMillis());
            }
        }
        return fileName;
    }

    TbsReaderView.ReaderCallback readerCallback = new TbsReaderView.ReaderCallback() {
        @Override
        public void onCallBackAction(Integer integer, Object o, Object o1) {

        }
    };
    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1001:
//                    x5WebView.loadUrl(url);
                    break;
            }
            return false;
        }
    });


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_exit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        x5WebView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        x5WebView.onPause();
    }

    @Override
    protected void onDestroy() {
        handler.removeMessages(1001);
        handler = null;
        x5WebView.reload();
        //销毁
        x5WebView.clearCache(true);
        x5WebView.clearFormData();
        x5WebView.destroy();
        tbsReaderView.onStop();
        super.onDestroy();
    }




    class JsObject {
        @JavascriptInterface
        public void navigateTo(String jsonData) {//活动专题可能和移动端的交互

        }

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_webview;
    }

}
