package com.ibulgakov.tinkoffinfoviewer.presenters;

import com.ibulgakov.tinkoffinfoviewer.models.eniity.News;
import com.ibulgakov.tinkoffinfoviewer.views.NewsView;

/**
 * Created by ibulgakov on 23.01.18.
 */

public interface NewsPresenter extends BasePresenter<NewsView> {
    void loadDbNews();
    void loadApiNews(boolean isRefreshing);
    void clickNewsItem(News item);
}
