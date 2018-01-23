package com.ibulgakov.tinkoffinfoviewer;

import android.app.Application;

import io.realm.Realm;
/**
 * Created by ibulgakov on 23.01.18.
 */

public class NewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
