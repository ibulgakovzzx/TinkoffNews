package com.ibulgakov.tinkoffinfoviewer.models;

import com.ibulgakov.tinkoffinfoviewer.models.eniity.BaseResponse;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.NewsInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by ibulgakov on 23.01.18.
 */

public class RequestManager {
    private static final String END_POINT = "https://api.tinkoff.ru";

    private static RetrofitService service;

    static {
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);
    }

    public static Observable<BaseResponse<List<News>>> getNews(){
        return service.getNews();
    }

    public static Observable<BaseResponse<NewsInfo>> getNewsInfo(String id){
        return service.getNewsInfo(id);
    }

}
