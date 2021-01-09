package com.isspgj.network;



import com.isspgj.network.callback.IError;
import com.isspgj.network.callback.IFailure;
import com.isspgj.network.callback.IRequest;
import com.isspgj.network.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by pgj on 2020/9/18
 **/
public class RestClientBuilder {
    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    /**
     * 请求成功回调
     */
    private ISuccess mSuccess = null;
    /**
     * 请求失败回调
     */
    private IFailure mFailure = null;
    /**
     * 请求错误回调
     */
    private IError mError = null;
    /**
     * 请求开始/结束回调
     */
    private IRequest mRequest = null;
    private RequestBody mBody = null;
    /**
     * 下载目录
     */
    private String mDownloadDir = null;
    /**
     * 下载文件扩展名
     */
    private String mExtension = null;
    /**
     * 下载文件名
     */
    private String name = null;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset-UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest) {
        this.mRequest = iRequest;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.name = name;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mSuccess, mFailure, mError, mRequest, mBody, mDownloadDir, mExtension, name);
    }

}
