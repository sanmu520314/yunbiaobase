package com.yunbiao.yunbiaobasedemo.afinel;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;


import com.yunbiao.yunbiaobasedemo.afinel.api.ApiService;
import com.yunbiao.yunbiaobasedemo.base.APP;
import com.yunbiao.yunbiaobasedemo.base.HeartBeatClient;
import com.yunbiao.yunbiaobasedemo.ui.bean.MessageBean;
import com.yunbiao.yunbiaobasedemo.utils.show.UIUtils;
import com.yunbiao.yunbiaobasedemo.utils.http.BaseObserver;
import com.yunbiao.yunbiaobasedemo.utils.system.SdCardUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class ResourceUpdate {

    private static final String TAG = "ResourceUpdate";
    private static String WEB_BASE_URL = Constants.RESOURCE_URL;

    public static String COMPANYINFO=WEB_BASE_URL +"api/company/getcompany.html";//获取公司信息以及下的部门信息接口
    public static String UPDATSTAFF=WEB_BASE_URL +"api/entry/entryupdate.html";//修改员工信息接口
    public static String ADDSTAFF=WEB_BASE_URL +"api/entry/entryadd.html";//添加员工信息接口
    public static String DELETESTAFF=WEB_BASE_URL +"api/entry/entrydelete.html";//删除员工接口
    public static String GETSTAFF=WEB_BASE_URL +"api/entry/getentry.html";//获取员工信息接口
    public static String SIGNLOG=WEB_BASE_URL +"api/sign/signlog.html";//创建签到信息接口
    public static String SIGNARRAY=WEB_BASE_URL +"api/sign/signlogByarray.html";//创建签到信息接口json
    public static String ADDDEPART=WEB_BASE_URL +"api/department/departmentadd.html";//创建部门
    public static String DELETEDEPART=WEB_BASE_URL +"api/department/departmentdelete.html";//删除部门

    /**
     * 资源获取
     **/
    private static String RESOURCE_URL = WEB_BASE_URL + "device/service/getresource.html";

    /**
     * 判断服务器和本地布局是否匹配
     **/
    public static String LAYOUT_CHANGE_STATUS = WEB_BASE_URL + "device/service/layoutchangestatus.html";

    /**
     * 判断服务器中的设备是否在线
     **/
    public static String DEVICE_ONLINE_STATUS = WEB_BASE_URL + "device/status/getrunstatus.html";

    /**
     * 前端布局
     */
    private static String LAYOUT_MENU_URL = WEB_BASE_URL + "device/service/getLayoutMenu.html";

    /**
     * 天气获取
     **/
    public static String WEATHER_URL = WEB_BASE_URL + "weather/city.html";

    /**
     * 限号获取
     **/
    public static String CARRUN_URL = WEB_BASE_URL + "weather/carrun.html";

    /**
     * 外币汇率获取
     **/
    public static String RMBRATE_URL = WEB_BASE_URL + "weather/exrate.html";

    /**
     * 版本检测
     **/
    public static String VERSION_URL = WEB_BASE_URL + "device/service/getversion.html";

    /**
     * 开关机时间获取
     **/
    public static String POWER_OFF_URL = WEB_BASE_URL + "device/service/poweroff.html";

    /**
     * 截图上传
     **/
    public static String SCREEN_UPLOAD_URL = WEB_BASE_URL + "device/service/uploadScreenImg.html";

    /**
     * 上传进度
     **/
    public static String RES_UPLOAD_URL = WEB_BASE_URL + "device/service/rsupdate.html";

    /**
     * SER_NUMBER
     **/
    public static String SER_NUMBER = WEB_BASE_URL + "device/status/getHasNumber.html";

    public static String SCAN_TO_CALL = WEB_BASE_URL + "/mobilebusself/mobilebusselfpost/selectbyordersernum.html";

    public static String SETTIME = WEB_BASE_URL + "common/service/getSystemTime.html";

    /**
     * 绑定设备
     */
    public static String DEC_NUM = WEB_BASE_URL + "device/status/binduser.html";

    /**
     * 获取续费二维码
     */
    public static String QRCODE = WEB_BASE_URL + "device/renewal/getopenrenewalqrcode.html";

    /**
     * 上传人脸识别
     */
    public static String UPLOADFACE = WEB_BASE_URL + "visitors/saveVisitors.html";

    /**
     * 上传下载进度
     */
    public static String NETSPEEFURL = WEB_BASE_URL + "device/status/uploadnetspeed.html";

    /**
     * 音量调节值获取
     * http://tyiyun.com/device/service/getVolume.html?deviceId=ffffffff-
     * be09-eca9-756a-0d8000000000
     */
    private static String VOLUME_URL = WEB_BASE_URL + "device/service/getVolume.html";
    private static String UPLOAD_APP_VERSION_URL = WEB_BASE_URL + "device/service/uploadAppVersionNew.html";

    private static String UPLOAD_DISK_URL = WEB_BASE_URL + "device/service/uploadDisk.html";

    private static String CACHE_BASE_PATH = FileConstants.PROPERTY_CACHE_PATH;
    public static String IMAGE_CACHE_PATH = CACHE_BASE_PATH + "resource/";// 资源存储目录
    public static String WEI_CACHE_PATH = CACHE_BASE_PATH + "wei/";// 资源存储目录
    public static String PROPERTY_CACHE_PATH = CACHE_BASE_PATH + "property/";// 参数缓存存储目录
    public static String SCREEN_CACHE_PATH = CACHE_BASE_PATH + "screen/";//参数缓存存储目录
    public static String PUSH_CACHE_PATH = CACHE_BASE_PATH + "push/";// 资源存储目录

    public static String RESOURSE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();



    public static void initWebConnect() {
        WEB_BASE_URL = Constants.RESOURCE_URL;
        RESOURCE_URL = WEB_BASE_URL + "device/service/getresource.html";
        LAYOUT_MENU_URL = WEB_BASE_URL + "device/service/getLayoutMenu.html";
        WEATHER_URL = WEB_BASE_URL + "weather/city.html";
        VERSION_URL = WEB_BASE_URL + "device/service/getversion.html";
        POWER_OFF_URL = WEB_BASE_URL + "device/service/poweroff.html";
        SCREEN_UPLOAD_URL = WEB_BASE_URL + "device/service/uploadScreenImg.html";
        RES_UPLOAD_URL = WEB_BASE_URL + "device/service/rsupdate.html";
        SER_NUMBER = WEB_BASE_URL + "device/status/getHasNumber.html";
        VOLUME_URL = WEB_BASE_URL + "device/service/getVolume.html";
        UPLOAD_APP_VERSION_URL = WEB_BASE_URL + "device/service/uploadAppVersionNew.html";
        UPLOAD_DISK_URL = WEB_BASE_URL + "device/service/uploadDisk.html";
        SETTIME = WEB_BASE_URL + "common/service/getSystemTime.html";
        DEC_NUM = WEB_BASE_URL + "device/status/binduser.html";
        RMBRATE_URL = WEB_BASE_URL + "weather/exrate.html";
        QRCODE = WEB_BASE_URL + "device/renewal/getopenrenewalqrcode.html";
        CARRUN_URL = WEB_BASE_URL + "weather/carrun.html";
    }

    /**
     * 下载进度完成
     */
    static void finishUpLoad() {
        ApiService.instance
                .netseed(HeartBeatClient.getDeviceNo(),"-1")
                .subscribeOn(Schedulers.newThread())//上游线程
                .observeOn(AndroidSchedulers.mainThread())//下游线程
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                    }
                    @Override
                    public void doOnNext(MessageBean baseResponse) {
                    }
                    @Override
                    public void doOnError(String errorMsg) {
                    }
                    @Override
                    public void doOnComplete() {

                    }
                });
    }

    /**
     * 实时下载进度
     * 上传到服务器
     */

    static void upToServer(String speedStr) {
        ApiService.instance
                .netseed(HeartBeatClient.getDeviceNo(),speedStr)
                .subscribeOn(Schedulers.newThread())//上游线程
                .observeOn(AndroidSchedulers.mainThread())//下游线程
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                    }
                    @Override
                    public void doOnNext(MessageBean baseResponse) {
                    }
                    @Override
                    public void doOnError(String errorMsg) {
                    }
                    @Override
                    public void doOnComplete() {

                    }
                });
    }

//    /**
//     * 音量调节
//     * <p/>
//     * param uid
//     */
//    public static void setVolume(String uid) {
//        HashMap<String, String> paramMap = new HashMap<String, String>();
//        paramMap.put("deviceId", uid);
//        String volume = NetTool.sendPost(VOLUME_URL, paramMap);
//        Double volumeD = Double.parseDouble(volume);
//        SoundControl.setMusicSound(volumeD);
//    }

    public static void uploadAppVersion() {
        UIUtils.showTitleTip("版本:" + getVersionName());
        ApiService.instance
                .uploadAppVersion(HeartBeatClient.getDeviceNo(),  getVersionName(),1+ "")
                .subscribeOn(Schedulers.newThread())//上游线程
                .observeOn(AndroidSchedulers.mainThread())//下游线程
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                    }
                    @Override
                    public void doOnNext(MessageBean baseResponse) {
                    }
                    @Override
                    public void doOnError(String errorMsg) {
                    }
                    @Override
                    public void doOnComplete() {

                    }
                });
    }
    /**
     * 获取当前版本号
     * @return
     */
    public  static String getVersionName() {
        String version = "";
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = APP.getContext().getApplicationContext().getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(APP.getContext().getApplicationContext().getPackageName(), 0);
            version = packInfo.versionName;
        } catch (Exception e) {
        }
        return version;
    };

    /**
     * 上传磁盘数据
     */
    public static void uploadDiskInfo() {
        String diskInfo = SdCardUtils.getSDDiskCon();
        String ss = "磁盘:" + diskInfo;
        UIUtils.showTitleTip(ss);
        HashMap<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("deviceNo", HeartBeatClient.getDeviceNo());
        paramMap.put("diskInfo", diskInfo);
        ApiService.instance
                .uploadDiskUrl(HeartBeatClient.getDeviceNo(),  diskInfo)
                .subscribeOn(Schedulers.newThread())//上游线程
                .observeOn(AndroidSchedulers.mainThread())//下游线程
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void doOnSubscribe(Disposable d) {
                    }
                    @Override
                    public void doOnNext(MessageBean baseResponse) {
                    }
                    @Override
                    public void doOnError(String errorMsg) {
                    }
                    @Override
                    public void doOnComplete() {

                    }
                });
    }

}
