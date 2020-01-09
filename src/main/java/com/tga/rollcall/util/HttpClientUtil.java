package com.tga.rollcall.util;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okhttp3.Request.Builder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author: sunlu
 * @date: 2018/8/19 上午10:15
 * @description: OkHttpClient
 */
public class HttpClientUtil {
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    /**
     * 读取超时时间
     */
    public static final long READ_TIMEOUT = 10;
    /**
     * 写的超时时间
     */
    public static final long WRITE_TIMEOUT = 10;
    /**
     * 连接超时时间
     */
    public static final long CONNECT_TIMEOUT = 10;
    /**
     * 连接活动保持时间
     */
    public static final long KEEP_ALIVE_DURATION = 30;
    /**
     * 最大连接数
     */
    public static final int MAX_CONNECTION_POOL = 512;
    public static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .connectionPool(
                    new ConnectionPool(MAX_CONNECTION_POOL, KEEP_ALIVE_DURATION, TimeUnit.SECONDS))
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    // 强行返回true 即验证成功
                    return true;
                }
            }).build();

    public static String get(String url) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Builder()
                .url(url)
                .build();
        Response response = httpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * Post json方式请求
     * @param url  请求地址
     * @param json  json字符串
     * @return
     * @throws IOException
     */
    public static String post(String url, String json) throws IOException {
        Response response = null;
        try {
            RequestBody requestBody = RequestBody.create(JSON, json);
            Request request = new Builder().url(url).post(requestBody).build();
            response = CLIENT.newCall(request).execute();
            if (response != null && response.body() != null && (response.isSuccessful() || response.code() == 500)) {
                return response.body().string();
            } else {
                String rsBody =  (null != response && response.body() != null) ? response.body().toString() : " response body is null ";
                throw new IOException("Unexpected  body " + rsBody + " code " + JSONObject.toJSONString(response));
            }

        } catch (Exception e) {
            throw e;
        }finally {
            if(null != response){
                response.close();
            }
        }

    }

    /**
     * Post方式指定 请求头
     * @param url
     * @param json
     * @param header
     * @return
     * @throws IOException
     */
    public static String post(String url, String json, Map<String, String> header)
            throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Builder builder = new Builder();
        Response response = null;
        try {
            if (null != header) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    builder = builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            Request request = builder.url(url).post(body).build();
            response = CLIENT.newCall(request).execute();
            if (response != null && response.body() != null && (response.isSuccessful() || response.code() == 500)) {
                return response.body().string();
            } else {
                String rsBody =  (null != response && response.body() != null) ? response.body().toString() : " response body is null ";
                throw new IOException("Unexpected  body " + rsBody + " code " + JSONObject.toJSONString(response));
            }
        } catch (Exception e) {
            throw e;
        }finally {
            if(null != response){
                response.close();
            }
        }
    }

    public static String postIgnoreSsl(String url, String json) throws IOException{
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response.body().string();

    }
    /**
     *
     * @param url
     * @param data
     * @return
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> data) throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> temp : data.entrySet()) {
            formBody.add(temp.getKey(), temp.getValue());
        }
        Request request = new Builder()
                .url(url)
                .post(formBody.build())
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response.body().string();
    }

    public static boolean postFormBoolean(String url, Map<String, String> data) throws IOException {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> temp : data.entrySet()) {
            formBody.add(temp.getKey(), temp.getValue());
        }
        Request request = new Builder()
                .url(url)
                .post(formBody.build())
                .build();
        Response response = CLIENT.newCall(request).execute();
        return response.isSuccessful();
    }
}