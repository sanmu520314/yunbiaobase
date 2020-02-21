package com.yunbiao.yunbiaobasedemo.utils.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetTool {
    private static final String TAG = "NetTool";

    /**
     * @param urlString 对应的URL 页面  只发送普通数据 ,调用此方法
     * @param params    需要发送的相关数据 包括调用的方法
     * @param imageuri  图片或文件手机上的地址 如:sdcard/photo/123.jpg
     * @param img       图片名称
     */
    public static void communication02(String urlString, Map<String, Object> params, String imageuri, String img) {
        String result = "";

        String end = "\r\n";
        // 是我定义的上传URL
        String MULTIPART_FORM_DATA = "multipart/form-data";
        String BOUNDARY = "---------7d4a6d158c9"; // 数据分隔线
        String imguri = "";
        if (!imageuri.equals("")) {
            imguri = imageuri.substring(imageuri.lastIndexOf("/") + 1);// 获得图片或文件名称
        }
        if (!urlString.equals("")) {
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);// 允许输入
                conn.setDoOutput(true);// 允许输出
                conn.setUseCaches(false);// 不使用Cache
                conn.setConnectTimeout(6000);// 6秒钟连接超时
                conn.setReadTimeout(60000);// 6秒钟读数据超时
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY);

                StringBuilder sb = new StringBuilder();

                // 上传的表单参数部分，格式请参考文章
                for (Map.Entry<String, Object> entry : params.entrySet()) {// 构建表单字段内容
                    sb.append("--");
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
                    sb.append(entry.getValue());
                    sb.append("\r\n");
                }

                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("\r\n");

                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.write(sb.toString().getBytes());

                if (!imageuri.equals("") && !imageuri.equals(null)) {
                    dos.writeBytes("Content-Disposition: form-data; name=\"" + img + "\"; filename=\"" + imguri + "\"" + "\r\n" + "Content-Type: image/jpeg\r\n\r\n");
                    FileInputStream fis = new FileInputStream(imageuri);
                    byte[] buffer = new byte[1024]; // 8k
                    int count = 0;
                    while ((count = fis.read(buffer)) != -1) {
                        dos.write(buffer, 0, count);
                    }
                    dos.writeBytes(end);
                    fis.close();
                }
                dos.writeBytes("--" + BOUNDARY + "--\r\n");
                dos.flush();

                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                result = br.readLine();
                 /* 取得Response内容 */
                int ch;
                StringBuffer b =new StringBuffer();
                while( ( ch = is.read() ) !=-1 )
                {
                    b.append( (char)ch );
                }
          /* 关闭DataOutputStream */
                dos.close();
            } catch (Exception e) {
                e.printStackTrace();
                result = "{\"ret\":\"898\"}";
            }
        }
    }

    /**
     * 人脸识别 传image图片获取人脸信息
     */
    public static String commDetect(String urlString, Map<String, Object> params, String imageuri, String img) {
        String result = "";

        String end = "\r\n";
        // 是我定义的上传URL
        String MULTIPART_FORM_DATA = "multipart/form-data";
        String BOUNDARY = "---------7d4a6d158c9"; // 数据分隔线
        String imguri = "";
        if (!imageuri.equals("")) {
            imguri = imageuri.substring(imageuri.lastIndexOf("/") + 1);// 获得图片或文件名称
        }
        if (!urlString.equals("")) {
            try {
                trustAllHosts();//执行一下trustAllHosts方法，信任所有证书，即可正常进行https请求。
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);// 允许输入
                conn.setDoOutput(true);// 允许输出
                conn.setUseCaches(false);// 不使用Cache
                conn.setConnectTimeout(6000);// 6秒钟连接超时
                conn.setReadTimeout(60000);// 6秒钟读数据超时
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("Charset", "UTF-8");
                conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY);

                StringBuilder sb = new StringBuilder();

                // 上传的表单参数部分，格式请参考文章
                for (Map.Entry<String, Object> entry : params.entrySet()) {// 构建表单字段内容
                    sb.append("--");
                    sb.append(BOUNDARY);
                    sb.append("\r\n");
                    sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
                    sb.append(entry.getValue());
                    sb.append("\r\n");
                }

                sb.append("--");
                sb.append(BOUNDARY);
                sb.append("\r\n");

                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
                dos.write(sb.toString().getBytes());

                if (!imageuri.equals("") && !imageuri.equals(null)) {
                    dos.writeBytes("Content-Disposition: form-data; name=\"" + img + "\"; filename=\"" + imguri + "\"" + "\r\n" + "Content-Type: image/jpeg\r\n\r\n");
                    FileInputStream fis = new FileInputStream(imageuri);
                    byte[] buffer = new byte[1024]; // 8k
                    int count = 0;
                    while ((count = fis.read(buffer)) != -1) {
                        dos.write(buffer, 0, count);
                    }
                    dos.writeBytes(end);
                    fis.close();
                }
                dos.writeBytes("--" + BOUNDARY + "--\r\n");
                dos.flush();

                InputStream is = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                result = br.readLine();
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            result = "faile";
        }
        return result;
    }

    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }
        }};
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
