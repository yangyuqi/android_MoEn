package com.youzheng.zhejiang.robertmoog.Base.request;


import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;


import com.youzheng.zhejiang.robertmoog.Base.utils.SSLSocketClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MultipartBody;
import rx.Observable;
import rx.Subscriber;


/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpClientManager
{
    private static OkHttpClientManager mInstance;
    private OkHttpClient mOkHttpClient;
    private Handler mDelivery;


    private static final String TAG = "OkHttpClientManager";

    private OkHttpClientManager()
    {
        mOkHttpClient =  new OkHttpClient().newBuilder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())//配置
            .build();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    public static OkHttpClientManager getInstance()
    {
        if (mInstance == null)
        {
            synchronized (OkHttpClientManager.class)
            {
                if (mInstance == null)
                {
                    mInstance = new OkHttpClientManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 对外公布post Json 方法
     */

    public static void postAsynJson(String type, String url, StringCallback callback)
    {
        getInstance()._postAsynJson(type,url,callback);
    }

    /**
     * 异步post JSON
     */
    private void  _postAsynJson(String type, String url, final StringCallback callback)
    {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON,type);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        deliveryResultJson(callback,request);
        Log.e("http--request---",url+type);
    }

    private void deliveryResultJson(final StringCallback callback, Request request)
    {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try
                {
                    final String string = response.body().string();
                    sendSuccessStringCallback(string, callback);
                } catch (IOException e)
                {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }


        });
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return Response
     */
    private Response _getAsyn(String url) throws IOException
    {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        return execute;
    }

    /**
     * 同步的Get请求
     *
     * @param url
     * @return 字符串
     */
    private String _getAsString(String url) throws IOException
    {
        Response execute = _getAsyn(url);
        return execute.body().string();
    }


    /**
     * 异步的get请求
     *
     * @param url
     * @param callback
     */
    private void _getAsyn(String url, final StringCallback callback)
    {
        Log.e("http--request--",url);
        final Request request = new Request.Builder()
                .url(url)
                .build();
        deliveryResult(callback, request);
    }


    /**
     * 异步下载文件
     *
     * @param url
     * @param destFileDir 本地文件存储的文件夹
     * @param callback
     */
    private void _downloadAsyn(final String url, final String destFileDir, final StringCallback callback)
    {
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                {
                    InputStream is = null;
                    byte[] buf = new byte[2048];
                    int len = 0;
                    FileOutputStream fos = null;
                    try
                    {
                        is = response.body().byteStream();
                        File file = new File(destFileDir, getFileName(url));
                        fos = new FileOutputStream(file);
                        while ((len = is.read(buf)) != -1)
                        {
                            fos.write(buf, 0, len);
                        }
                        fos.flush();
                        //如果下载文件成功，第一个参数为文件的绝对路径
                        sendSuccessStringCallback(file.getAbsolutePath(), callback);
                    } catch (IOException e)
                    {
                        sendFailedStringCallback(response.request(), e, callback);
                    } finally
                    {
                        try
                        {
                            if (is != null) is.close();
                        } catch (IOException e)
                        {
                        }
                        try
                        {
                            if (fos != null) fos.close();
                        } catch (IOException e)
                        {
                        }
                    }

                }
            }

        });
    }

    private String getFileName(String path)
    {
        int separatorIndex = path.lastIndexOf("/");
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    private void setErrorResId(final ImageView view, final int errorResId)
    {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                view.setImageResource(errorResId);
            }
        });
    }


    //*************对外公布的方法************


    public static Response getAsyn(String url) throws IOException
    {
        return getInstance()._getAsyn(url);
    }


    public static String getAsString(String url) throws IOException
    {
        return getInstance()._getAsString(url);
    }

    public static void getAsyn(String url, StringCallback callback)
    {
        getInstance()._getAsyn(url, callback);
    }


    private String guessMimeType(String path)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    private Param[] validateParam(Param[] params)
    {
        if (params == null)
            return new Param[0];
        else return params;
    }

    private Param[] map2Params(Map<String, String> params)
    {
        if (params == null) return new Param[0];
        int size = params.size();
        Param[] res = new Param[size];
        Set<Map.Entry<String, String>> entries = params.entrySet();
        int i = 0;
        for (Map.Entry<String, String> entry : entries)
        {
            res[i++] = new Param(entry.getKey(), entry.getValue());
        }
        return res;
    }

    private void deliveryResult(final StringCallback callback, Request request)
    {
        mOkHttpClient.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e) {
                sendFailedStringCallback(call.request(), e, callback);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try
                {
                    final String string = response.body().string();
                    sendSuccessStringCallback(string, callback);
                } catch (IOException e)
                {
                    sendFailedStringCallback(response.request(), e, callback);
                }
            }

        });
    }

    private void sendFailedStringCallback(final Request request, final IOException e, final StringCallback callback)
    {
        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                    callback.onFailure(request, e);
            }
        });
    }

    private void sendSuccessStringCallback(final String string, final StringCallback callback)
    {

        mDelivery.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (callback != null)
                    try {
                        callback.onResponse(string);
                    }catch (Exception e){

                    }
                    Log.e("http--response--",string);
            }
        });
    }



    public interface StringCallback
    {
        void onFailure(Request request, IOException e);

        void onResponse(String response);
    }

    public static class Param
    {
        public Param()
        {
        }

        public Param(String key, String value)
        {
            this.key = key;
            this.value = value;
        }

        String key;
        String value;
    }




    public static void postAsyn(String url, StringCallback callback, File file, String fileKey) throws IOException
    {
        getInstance()._postAsyn(url, callback, file, fileKey);
    }


    /**
     * 异步基于post的文件上传，单文件不带参数上传
     *
     * @param url
     * @param callback
     * @param file
     * @param fileKey
     * @throws IOException
     */
    private void _postAsyn(String url, StringCallback callback, File file, String fileKey) throws IOException
    {
        Request request = buildMultipartFormRequest(url, new File[]{file}, new String[]{fileKey}, null);
        deliveryResult(callback, request);
    }

    private Request buildMultipartFormRequest(String url, File[] files,
                                              String[] fileKeys, Param[] params)
    {
        params = validateParam(params);

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        for (Param param : params)
        {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + param.key + "\""),
                    RequestBody.create(null, param.value));
        }
        if (files != null)
        {
            RequestBody fileBody = null;
            for (int i = 0; i < files.length; i++)
            {
                File file = files[i];
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                //TODO 根据文件名设置contentType
                builder.addPart(Headers.of("Content-Disposition",
                        "form-data; name=\"" + fileKeys[i] + "\"; filename=\"" + fileName + "\""),
                        fileBody);
            }
        }

        RequestBody requestBody = builder.build();
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public  Observable<String> sendMultipart(final String reqUrl, final Map<String, Object> params, final String pic_key, final List<File> files){
        return Observable.create(new Observable.OnSubscribe<String>(){

            @Override
            public void call(final Subscriber<? super String> subscriber) {
                MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
                multipartBodyBuilder.setType(MultipartBody.FORM);
                //遍历map中所有参数到builder
//                if (params != null){
//                    for (String key : params.keySet()) {
//                        multipartBodyBuilder.addFormDataPart(key, params.get(key));
//                    }
//                }
                //遍历paths中所有图片绝对路径到builder，并约定key如“upload”作为后台接受多张图片的key
                if (files != null){
                    for (File file : files) {
                        multipartBodyBuilder.addFormDataPart(pic_key, file.getName(), RequestBody.create(MEDIA_TYPE_PNG, file));
                        Log.e("几张图片",file.toString());
                    }
                }
                //构建请求体
                RequestBody requestBody = multipartBodyBuilder.build();

                Request.Builder RequestBuilder = new Request.Builder();
                RequestBuilder.url(reqUrl);// 添加URL地址
                RequestBuilder.post(requestBody);
                Request request = RequestBuilder.build();
                Log.e("图片网址 ",request+"");
                mOkHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        subscriber.onError(e);
                        subscriber.onCompleted();
                        call.cancel();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();
                        subscriber.onNext(str);
                        subscriber.onCompleted();
                        call.cancel();
                    }
                });
            }
        });
    }


}