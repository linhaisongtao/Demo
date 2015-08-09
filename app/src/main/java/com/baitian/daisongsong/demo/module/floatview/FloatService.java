package com.baitian.daisongsong.demo.module.floatview;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by daisongsong on 2015/8/5.
 */
public class FloatService extends Service {
    private static final long INTERVAL = 1000;

    private FloatManager mFloatManager;

    private Timer mTimer;
    private Handler mHandler;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mFloatManager = new FloatManager(getApplicationContext());
        if (mTimer == null) {
            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(new MyTimerTask(), 0, INTERVAL);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mFloatManager.refreshPercent(new Random().nextFloat());
                }
            });

        }
    }
}
