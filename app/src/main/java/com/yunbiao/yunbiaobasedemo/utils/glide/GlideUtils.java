package com.yunbiao.yunbiaobasedemo.utils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yunbiao.yunbiaobasedemo.R;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * @author ChayChan
 * @description: 对glide进行封装的工具类
 * @date 2017/6/19  20:43
 */

public class GlideUtils {

    public static void load(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.meeting_people);
        if (context!=null){

                Glide.with(context)
                        .load(url)
                        .apply(options)
                        .into(iv);

        }

    }

    public static void loadDim(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.meeting_people);
        if (context!=null){
            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .transform(new BlurTransformation(2, 1))
                    .into(iv);

        }

    }

    public static void load(Context context, String url, ImageView iv, int placeHolderResId) {
        if (placeHolderResId == -1) {
            Glide.with(context)
                    .load(url)
                    .into(iv);
            return;
        }
        RequestOptions options = new RequestOptions();
        options.placeholder(placeHolderResId);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(iv);
    }

    public static void loadRound(Context context, String url, ImageView iv) {
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_circle_default)
                .centerCrop()
                .circleCrop();

        Glide.with(context)//
                .load(url)//
                .apply(options)//
                .into(iv);
    }
}
