package com.baitian.daisongsong.demo.base;

import android.app.Application;

/**
 * Created by daisongsong on 2015/8/11.
 */
public class Core extends Application {
    public static Core sInstance;

    public static final Core getCore() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
