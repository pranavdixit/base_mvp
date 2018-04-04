package com.idme.network;

import com.idme.BuildConfig;
import com.idme.Idme;
import com.idme.common.AppLog;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by pranav.dixit on 02/04/18.
 */

public class RestController {
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String HEADER_CACHE_MAX_AGE = "Cache-Max-Age";
    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_USER_AGENT = "User-Agent";
    private static final String HEADER_DEVICE_ID = "deviceId";
    private static final int CACHE_SIZE = 10 * 1024 * 1024;
    private static final String SET_COOKIE = "set-cookie";
    private static final String NO_CACHE = "no-cache";
    private RestAPI restAPI;
    private OkHttpClient httpClient;
    private String host;
    private int DEFAULT_READ_TIMEOUT_SECONDS = 60;
    private static boolean ENABLE_HTTP_LOGS= BuildConfig.DEBUG;


    private static volatile RestController mInstance;

    private RestController() {
        restAPI = newRetrofitRepo(host, DEFAULT_READ_TIMEOUT_SECONDS);
    }

    public static RestController getInstance() {
        if (mInstance == null) {
            synchronized (RestController.class) {
                if (mInstance == null)
                    mInstance = new RestController();
            }
        }
        return mInstance;
    }

    private static Cache getCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(Idme.getInstance().getCacheDir(), "http-cache"), CACHE_SIZE); // 10 MB
        } catch (Exception e) {
            AppLog.e("Could not create Cache!" + e.getMessage());
        }
        return cache;
    }

    private RestAPI newRetrofitRepo(String host, int secondsReadTimeout) {
        this.host = host;
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient = getClient(secondsReadTimeout))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(host)
                .build();
        return retrofit.create(RestAPI.class);
    }

    private OkHttpClient getClient(int secondsReadTimeout) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (ENABLE_HTTP_LOGS) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        builder.readTimeout(secondsReadTimeout, TimeUnit.SECONDS).cache(getCache());
        return builder.build();
    }

}
