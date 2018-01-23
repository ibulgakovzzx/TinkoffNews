package com.ibulgakov.tinkoffinfoviewer.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ibulgakov.tinkoffinfoviewer.R;
import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;
import com.ibulgakov.tinkoffinfoviewer.presenters.NewsPresenterImp;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements NewsView {

    private RecyclerView rvNews;
    private NewsAdapter adapter;
    private SwipeRefreshLayout swLayout;
    private ProgressBar pbLoad;

    private NewsPresenterImp newsPresenter;
    private List<News> newsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initUi();
        initRecycler();
        initPullToRefreshListener();
        newsPresenter = new NewsPresenterImp();
        newsPresenter.attachToView(this);
        newsPresenter.loadDbNews();
        newsPresenter.loadApiNews(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.setListener(null);
        swLayout.setOnRefreshListener(null);
        newsPresenter.detachFromView();
    }

    @Override
    public void showNews(List<News> list) {
        swLayout.setRefreshing(false);
        newsList.clear();
        newsList.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(int stringId) {
        Toast.makeText(this,getString(stringId),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeProgressVisibility(boolean isVisible) {
        pbLoad.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public Context getContext(){
        return this;
    }

    private void initUi(){
        rvNews = (RecyclerView) findViewById(R.id.rv_news);
        swLayout = (SwipeRefreshLayout) findViewById(R.id.swr_ayout);
        pbLoad = (ProgressBar) findViewById(R.id.pb_load);
    }

    private void initRecycler(){
        newsList = new ArrayList<>();
        adapter = new NewsAdapter(this,newsList);
        adapter.setListener((view, position) -> {
            if(position >= 0 && position < newsList.size()) {
                News news = newsList.get(position);
                newsPresenter.clickNewsItem(news);
            }
        });
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rvNews.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvNews.setAdapter(adapter);
    }

    private void initPullToRefreshListener(){
        swLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        newsPresenter.loadApiNews(true);
    }
}
