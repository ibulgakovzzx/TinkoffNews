package com.ibulgakov.tinkoffinfoviewer.presenters;

/**
 * Created by ibulgakov on 23.01.18.
 */

public interface BasePresenter<V> {
    void attachToView(V view);
    void detachFromView();
}
