package com.yunbiao.yunbiaobasedemo.utils.system;


import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;


import com.yunbiao.yunbiaobasedemo.afinel.ResourceUpdate;
import com.yunbiao.yunbiaobasedemo.base.BaseActivity;
import com.yunbiao.yunbiaobasedemo.base.HeartBeatClient;
import com.yunbiao.yunbiaobasedemo.utils.http.NetTool;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class ScreenShot {

    private static ScreenShot screenShot = null;

    private ScreenShot() {
    }

    public static ScreenShot getInstanse() {
        if (screenShot == null) {
            synchronized (ScreenShot.class) {
                if (screenShot == null) {
                    screenShot = new ScreenShot();
                }
            }
        }
        return screenShot;
    }



    /**
     * 屏幕截图
     */
    public void shootScreen() {
        screencap();
    }
    private void screencap() {
        try {
            if (ShellUtils.checkRootPermission() && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES
                    .ICE_CREAM_SANDWICH) {
                // 图片截取
                Process process = Runtime.getRuntime().exec("sh");
                DataOutputStream dos = new DataOutputStream(process.getOutputStream());
                String path = ResourceUpdate.SCREEN_CACHE_PATH;
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String sid = HeartBeatClient.getDeviceNo();
                String filePath = path + sid + ".png";
                File imageFile = new File(filePath);
                if (imageFile.exists()) {
                    imageFile.delete();
                }
                dos.writeBytes("/system/bin/screencap -p " + filePath + "\n");
                dos.writeBytes("exit\n");
                process.waitFor();
                int temp = 0;
                while (!new File(filePath).exists()) {
                    temp++;
                    if (temp >= 5) {
                        break;
                    }
                    Thread.sleep(1000);
                }
                Thread.sleep(2000);
                // 图片旋转并压缩
//                changeImageRotationAndCompress(filePath);
                if (new File(filePath).exists()){
                    Bitmap decodeFile = BitmapFactory.decodeFile(filePath);
                    FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                    // 图片压缩
                    decodeFile.compress(CompressFormat.JPEG, 20, fileOutputStream);
                    // 图片上传
                    sendCutFinish(sid, filePath);
                    decodeFile.recycle();
                }
            } else {
                cutYsScreen();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void cutYsScreen() {
        try {
            Bitmap bitmap = snapShotWithStatusBar();
            String path = ResourceUpdate.SCREEN_CACHE_PATH;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            String sid = HeartBeatClient.getDeviceNo();
            String filePath = path + sid + ".png";
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            // 图片压缩
            bitmap.compress(CompressFormat.JPEG, 20, fileOutputStream);
            fileOutputStream.close();
            // 图片上传
            sendCutFinish(sid, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 截屏,通用接口
    public Bitmap snapShotWithStatusBar() {
        View view = BaseActivity.getCurrentActivity().getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        DisplayMetrics dm = new DisplayMetrics();
        BaseActivity.getCurrentActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;  // 获取屏幕的宽  像素
        int height = dm.heightPixels; // 获取屏幕的高  像素
        Bitmap bp = Bitmap.createScaledBitmap(bmp, width, height, true);
//        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);

        view.destroyDrawingCache();
        return bp;
    }
    private void sendCutFinish(String sid, String filePath) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("sid", sid);
        NetTool.communication02(ResourceUpdate.SCREEN_UPLOAD_URL, params, filePath, "screenimage");
    }
}
