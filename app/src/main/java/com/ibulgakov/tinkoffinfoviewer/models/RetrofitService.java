package com.ibulgakov.tinkoffinfoviewer.models;

import com.ibulgakov.tinkoffinfoviewer.models.eniity.BaseResponse;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.NewsInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetrofitService {
    @GET("/v1/news")
    Observable<BaseResponse<List<News>>> getNews();

    @GET("/v1/news_content")
    Observable<BaseResponse<NewsInfo>> getNewsInfo(@Query("id") String id);
}
