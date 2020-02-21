package com.yunbiao.yunbiaobasedemo.utils.system;








import com.yunbiao.yunbiaobasedemo.afinel.Constants;
import com.yunbiao.yunbiaobasedemo.afinel.api.ApiService;
import com.yunbiao.yunbiaobasedemo.base.APP;
import com.yunbiao.yunbiaobasedemo.base.HeartBeatClient;
import com.yunbiao.yunbiaobasedemo.ui.bean.MessageBean;
import com.yunbiao.yunbiaobasedemo.utils.CommonUtils;
import com.yunbiao.yunbiaobasedemo.utils.HandleMessageUtils;
import com.yunbiao.yunbiaobasedemo.utils.http.BaseObserver;
import com.yunbiao.yunbiaobasedemo.utils.http.BaseResponse;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuShao on 2016/3/4.
 */

public class MachineDetial {
    private static final String TAG = "MachineDetial";
    private String upMechineDetialUrl = Constants.BASEURL + "device/service/updateDeviceHardwareInfo.html";

    private static MachineDetial machineDetial;

    public static MachineDetial getInstance() {
        if (machineDetial == null) {
            machineDetial = new MachineDetial();
        }
        return machineDetial;
    }

    private MachineDetial() {

    }
    /**
     * 上传设备信息
     */
    public void upLoadHardWareMessage() {
        HandleMessageUtils.getInstance().runInThread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> map = new HashMap<String, String>();
                map.put("deviceNo", HeartBeatClient.getDeviceNo());
                map.put("screenWidth", String.valueOf(CommonUtils.getScreenWidth(APP.getContext())));
                map.put("screenHeight", String.valueOf(CommonUtils.getScreenHeight(APP.getContext())));
                map.put("diskSpace", CommonUtils.getMemoryTotalSize());
                map.put("useSpace", CommonUtils.getMemoryUsedSize());
                map.put("softwareVersion", CommonUtils.getAppVersion(APP.getContext()) + "_" + 1);

                map.put("deviceCpu", CommonUtils.getCpuName() + " " + CommonUtils.getNumCores() + "核" + CommonUtils
                        .getMaxCpuFreq() + "khz");
                map.put("deviceIp", CommonUtils.getIpAddress());//当前设备IP地址
                map.put("mac", CommonUtils.getLocalMacAddress());//设备的本机MAC地址

                ApiService.instance
                        .upLoadHardWareMessage(HeartBeatClient.getDeviceNo(), String.valueOf(CommonUtils.getScreenWidth(APP.getContext())),
                                String.valueOf(CommonUtils.getScreenHeight(APP.getContext())),CommonUtils.getMemoryTotalSize(),
                                CommonUtils.getMemoryUsedSize(),CommonUtils.getAppVersion(APP.getContext()) + "_" + 1,
                                CommonUtils.getCpuName() + " " + CommonUtils.getNumCores() + "核" + CommonUtils
                                        .getMaxCpuFreq() + "khz",CommonUtils.getIpAddress(),CommonUtils.getLocalMacAddress() )
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
        });
    }


}
