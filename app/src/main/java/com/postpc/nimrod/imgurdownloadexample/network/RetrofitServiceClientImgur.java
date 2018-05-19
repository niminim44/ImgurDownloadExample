package com.postpc.nimrod.imgurdownloadexample.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitServiceClientImgur {


    private static final String BASE_URL = "https://api.imgur.com/3/";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    private static Retrofit getRetrofit() {
        return getRetrofit(BASE_URL);
    }


    private static Retrofit getRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonCustomConverterFactory.create(getGson()))
                .addCallAdapterFactory(getRxAdapter())
                .client(getRequestHeader())
                .build();
    }


    @NonNull
    private static Gson getGson() {
        return new GsonBuilder().setDateFormat(DATE_FORMAT)
                .setLenient()
                .create();
    }

    private static RxJava2CallAdapterFactory getRxAdapter() {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    private static OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(createLoggingInterceptor());
        return httpClient.build();
    }

    private static HttpLoggingInterceptor createLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    public static ImgurApi getImgurApi() {
        Retrofit retrofit = RetrofitServiceClientImgur.getRetrofit();
        return retrofit.create(ImgurApi.class);
    }


}
