package com.yunbiao.yunbiaobasedemo.afinel.api;

import com.yunbiao.yunbiaobasedemo.ui.bean.MessageBean;
import com.yunbiao.yunbiaobasedemo.utils.http.RetrofitClient;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    ApiService instance = RetrofitClient.getInstance().getRetrofit().create(ApiService.class);


    //上传设备信息
    @POST("/device/service/updateDeviceHardwareInfo.html")
    Observable<MessageBean> upLoadHardWareMessage(@Query("deviceNo") String deviceNo, @Query("screenWidth") String screenWidth,
                                                  @Query("screenHeight") String screenHeight,
                                                  @Query("diskSpace") String diskSpace, @Query("useSpace") String useSpace,
                                                  @Query("softwareVersion") String softwareVersion, @Query("deviceCpu") String deviceCpu,
                                                  @Query("deviceIp") String deviceIp, @Query("mac") String mac);


    //上传下载速度
    @POST("/device/status/uploadnetspeed.html")
    Observable<MessageBean> netseed(@Query("deviceNo") String deviceNo,
                                @Query("speed") String speed);

    //上传版本信息
    @POST("/device/service/uploadAppVersionNew.html")
    Observable<MessageBean> uploadAppVersion(@Query("deviceNo") String deviceNo,
                                    @Query("version") String version,
                                    @Query("type") String type);

    //上传磁盘信息
    @POST("/device/service/uploadDisk.html")
    Observable<MessageBean> uploadDiskUrl(@Query("deviceNo") String deviceNo,
                                    @Query("diskInfo") String diskInfo);
}
