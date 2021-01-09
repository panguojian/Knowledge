package com.isspgj.network;


import com.isspgj.network.callback.IError;
import com.isspgj.network.callback.IFailure;
import com.isspgj.network.callback.IRequest;
import com.isspgj.network.callback.ISuccess;
import com.isspgj.network.callback.RequestCallbacks;


import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by pgj on 2020/9/18
 **/
public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    /**
     * 请求成功回调
     */
    private final ISuccess SUCCESS;
    /**
     * 请求失败回调
     */
    private final IFailure FAILURE;
    /**
     * 请求错误回调
     */
    private final IError ERROR;
    /**
     * 请求开始/结束回调
     */
    private final IRequest REQUEST;
    private final RequestBody BODY;
    /**
     * 下载目录
     */
    private final String DOWNLOAD_DIR;
    /**
     * 下载文件扩展名
     */
    private final String EXTENSION;
    /**
     * 下载文件名
     */
    private final String NAME;

    public RestClient(String url, Map<String, Object> params, ISuccess success, IFailure failure, IError error, IRequest rquest, RequestBody body, String downloadDir, String extension, String name) {
        this.URL = url;
        PARAMS.putAll(params);
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = rquest;
        this.BODY = body;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(SUCCESS, FAILURE, ERROR, REQUEST);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

//    public final void download() {
//        new DownloadHandler(URL, SUCCESS, FAILURE, ERROR, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME).handleDownload();
//    }
}
