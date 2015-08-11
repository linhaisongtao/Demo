package com.baitian.daisongsong.demo.utils;

import android.widget.Toast;

import com.baitian.daisongsong.demo.base.Core;

/**
 * Created by daisongsong on 2015/8/11.
 */
public class T {
    private static T sInstance = null;
    private Toast mToast;

    private T() {
        mToast = Toast.makeText(Core.getCore(), "", Toast.LENGTH_SHORT);
        mToast.setDuration(Toast.LENGTH_SHORT);
    }

    private static T getInstance() {
        if (sInstance == null) {
            synchronized (T.class) {
                if (sInstance == null) {
                    sInstance = new T();
                }
            }
        }
        return sInstance;
    }

    public static void show(String msg) {
        T.getInstance().showMsg(msg);
    }

    private void showMsg(String msg) {
        mToast.setText(msg);
        mToast.show();
    }
}
