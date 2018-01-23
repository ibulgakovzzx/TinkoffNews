package com.ibulgakov.tinkoffinfoviewer.presenters;

import android.content.Context;
import android.content.Intent;

import com.ibulgakov.tinkoffinfoviewer.R;
import com.ibulgakov.tinkoffinfoviewer.models.RequestManager;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.NewsInfo;
import com.ibulgakov.tinkoffinfoviewer.views.NewsInfoActivity;
import com.ibulgakov.tinkoffinfoviewer.views.NewsView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

public class NewsPresenterImp implements NewsPresenter {

    private NewsView newsView;
    private Realm realm;

    @Override
    public void attachToView(NewsView view) {
        newsView = view;
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void detachFromView() {
        newsView = null;
        if(!realm.isClosed()){
            realm.close();
        }
    }


    @Override
    public void loadApiNews(boolean isRefreshing) {
        if(!isRefreshing){
            newsView.changeProgressVisibility(true);
        }
        RequestManager.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                            if("OK".equals(response.getResultCode())) {
                                if(!isRefreshing) {
                                    newsView.changeProgressVisibility(false);
                                }
                                List<News> news = response.getPayload();
                                if (news != null && news.size() > 0) {
                                    Collections.sort(news, newsComparator);
                                    insertToRealm(news);
                                    newsView.showNews(news);
                                } else {
                                    newsView.showMessage(R.string.nothing_update);
                                }
                            }
                        }, throwable -> {
                            newsView.changeProgressVisibility(false);
                            newsView.showMessage(R.string.network_error);
                        }
                );
    }

    @Override
    public void clickNewsItem(News item) {
        newsView.changeProgressVisibility(true);
        RequestManager.getNewsInfo(item.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(response -> {
                    if("OK".equals(response.getResultCode())) {
                        newsView.changeProgressVisibility(false);

                        Context context = newsView.getContext();
                        NewsInfo newsInfo = response.getPayload();

                        Intent infoIntent = new Intent(context, NewsInfoActivity.class);
                        infoIntent.putExtra("content",newsInfo.getContent());
                        context.startActivity(infoIntent);
                    }
                }, throwable -> {
                    newsView.changeProgressVisibility(false);
                    newsView.showMessage(R.string.network_error);
                });
    }


    @Override
    public void loadDbNews() {
        realm.executeTransaction(realm -> {
            RealmResults<News> dbNews = realm.where(News.class).findAll();
            if(dbNews.size() > 0){
                try {
                    News[] newsArr = new News[dbNews.size()];
                    dbNews.toArray(newsArr);
                    List<News> newsList = Arrays.asList(newsArr);
                    Collections.sort(newsList,newsComparator);
                    newsView.showNews(newsList);
                } catch (ClassCastException ex){
                    newsView.showMessage(R.string.db_errror);
                }
            }

        });
    }


    private Comparator<News> newsComparator = (o1, o2) -> {
        if(o1.getPublicationDate() == null){
            if(o2.getPublicationDate() == null)
                return 0;
            return -1;
        }

        Long l1 = o1.getPublicationDate().getMilliseconds();
        Long l2 = o2.getPublicationDate().getMilliseconds();

        if (l1 == null) {
            if (l2 == null)
                return 0;
            return 1;
        }
        else if (l2 == null)
            return -1;
        else {
            long diff = l2 - l1;
            if (diff > 0)
                return 1;
            else if (diff < 0)
                return -1;
            else
                return 0;
        }
    };

    private void insertToRealm(List<News> newsList){
        realm.executeTransaction(realm -> {
            realm.copyToRealmOrUpdate(newsList);
        });
    }


}
