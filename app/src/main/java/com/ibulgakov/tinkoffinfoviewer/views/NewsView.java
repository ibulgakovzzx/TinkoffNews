package com.ibulgakov.tinkoffinfoviewer.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;

import java.util.List;

/**
 * Created by User on 22.01.2018.
 */

public interface NewsView  extends SwipeRefreshLayout.OnRefreshListener{
    void showNews(List<News> list);
    void showMessage(int stringId);
    void changeProgressVisibility(boolean isVisible);
    Context getContext();
}
