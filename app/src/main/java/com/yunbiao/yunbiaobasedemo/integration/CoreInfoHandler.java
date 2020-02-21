package com.yunbiao.yunbiaobasedemo.integration;

import android.util.Log;

import com.yunbiao.yunbiaobasedemo.afinel.ResourceUpdate;
import com.yunbiao.yunbiaobasedemo.base.APP;
import com.yunbiao.yunbiaobasedemo.event.XmppPush;
import com.yunbiao.yunbiaobasedemo.utils.ArmsUtils;
import com.yunbiao.yunbiaobasedemo.utils.data.SpUtils;
import com.yunbiao.yunbiaobasedemo.utils.show.UIUtils;
import com.yunbiao.yunbiaobasedemo.utils.data.JsonUtil;
import com.yunbiao.yunbiaobasedemo.utils.system.MachineDetial;
import com.yunbiao.yunbiaobasedemo.utils.system.SoundControl;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * xmpp消息处理
 *
 * @author Administrator
 */
public class CoreInfoHandler {
    private static final String TAG = "CoreInfoHandler";

    public static final int ONLINE_TYPE = 1;// 上线
    private static final int CONTENT_TYPE = 2;// 内容修改
    private static final int VOICE_TYPE = 3;// 声音
    private static final int CUTSCREN_TYPE = 4;// 截屏
    private static final int RUNSET_TYPE = 5;// 设备开关机设置
    private static final int SHOW_SERNUM = 6;// 显示设备编号
    private static final int SHOW_VERSION = 7;// 显示版本号
    private static final int SHOW_DISK_IFNO = 8;// 获取磁盘容量
    private static final int POWER_RELOAD = 9;// 设备 开机 重启
    private static final int PUSH_TO_UPDATE = 10;//软件升级
    private final static int HARDWARE_UPDATE = 11;//通知设备硬件更新,上传设备信息
    private final static int HARDWARESCREENROTATE_UPDATE = 12;//屏幕旋转
    private final static int SET_CLEAR_LAYOUT = 13;//一键删除布局
    public final static int PUSH_MESSAGE = 14;//推送广告消息，快发字幕
    private final static int REFERSH_RENEWAL_STATUS = 15;//欠费停机设备支付
    private final static int CHANNEL_TYPE = 16;//输入源选择
    private final static int PUSH_IMAGE = 17;//手机端快发图片
    private final static int FACE_DETECT = 18;//开通人脸识别
    private final static int EARTH_CINEMA = 19;//大地影院
    private final static int UNICOM_SCREEN = 20;//联屏
    private final static int IMAGE_PUSH = 21;//推送的图片
    private final static int VIDEO_PUSH = 22;//推送的视频
    public final static int BIND_PUSH = 23;//绑定
    public final static int SHOP_PUSH = 25;//整体店铺
    public final static int CAR_PUSH = 26;//一条车辆信息
    public final static int H5ADDRESS_PUSH = 27;//更新H5地址
    public final static int AD_FLASH_PUSH = 28;//更新广告
    public final static int LOGFILE_PUSH = 29;//日志文件
    public final static int REFRESH_FACE_LOG = 30;// 推送终端发送人脸日志数据
    public final static int REFRESH_FACE_FILE = 31;//会议修改
    public final static int CUTSCREN_TYPE_QINIU = 32;//推送终端发送人脸图片文件
    public final static int MEETINGPOOPLE_ADD = 102;//推送终端发送人脸图片文件
    public final static int MEETINGPOOPLE_PUSH = 103;//推送终端发送人脸图片

    public static void messageReceived(String message) {
        Log.e(TAG, "接收消息：" + message );
        EventBus.getDefault().post(new XmppPush(message));
        try {
            JSONObject mesJson = new JSONObject(message);
            Integer type = mesJson.getInt("type");
            JSONObject contentJson;
            switch (type.intValue()) {
                case ONLINE_TYPE:
                    // 系统登录
                    contentJson = mesJson.getJSONObject("content");
                    if (!contentJson.isNull("serNum")) {
//                     //第一次系统启动的时候服务器没有设备详细信息，需要向设备传消息
                        //第一次系统启动的时候服务器没有设备详细信息，需要向设备传消息
                        MachineDetial.getInstance().upLoadHardWareMessage();
                        String serNum =  JsonUtil.getFieldValue(contentJson.toString(),"serNum");
                        SpUtils.saveString(APP.getContext(), SpUtils.DEVICESERNUM, serNum);

                        String deviceNo = mesJson.getString("sid");
                        SpUtils.saveString(APP.getContext(), SpUtils.DEVICENO, deviceNo);
                    }



                    break;

                case VOICE_TYPE:// 声音控制
                    JSONObject jsonObject = mesJson.getJSONObject("content");
                    if (jsonObject != null) {
                        SoundControl.setMusicSound(jsonObject.getDouble("voice"));
                    }
                    break;
                case CUTSCREN_TYPE:

                    break;
                case RUNSET_TYPE:

                    break;
                case SHOW_SERNUM:
                  String  content=  JsonUtil.getFieldValue(mesJson.toString(),"content");
                    if (content != null) {
                      String deviceNo = SpUtils.getString(APP.getContext(), SpUtils.DEVICESERNUM, "");
                        Log.e(TAG, "deviceNo------------->"+deviceNo );
                        UIUtils.showTitleTip(deviceNo);
                    }
                    break;
                case SHOW_VERSION:// 版本信息
                    ResourceUpdate.uploadAppVersion();
                    break;
                case SHOW_DISK_IFNO:
                    contentJson = mesJson.getJSONObject("content");
                    Integer flag = (Integer) ArmsUtils.getJsonObj(contentJson, "flag", null);
                    if (flag != null) {
                        if (flag == 0) { //显示
                            ResourceUpdate.uploadDiskInfo();
                        } else if (flag == 1) {// 清理磁盘
                            ResourceUpdate.uploadDiskInfo();
                        }
                    }
                    break;
                case POWER_RELOAD:// 机器重启

                    break;
                case PUSH_TO_UPDATE:

                    break;
                case HARDWARE_UPDATE:
                    break;
                case HARDWARESCREENROTATE_UPDATE://屏幕旋转
                    break;

                case PUSH_MESSAGE:

                    break;
                case REFERSH_RENEWAL_STATUS://欠费停机设备支付

                    break;


                case SHOP_PUSH:break;
                case CAR_PUSH:break;
                case H5ADDRESS_PUSH:break;
                case AD_FLASH_PUSH:break;
                case LOGFILE_PUSH:break;
                case REFRESH_FACE_LOG:break;
                case MEETINGPOOPLE_PUSH:

                    break;


                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}