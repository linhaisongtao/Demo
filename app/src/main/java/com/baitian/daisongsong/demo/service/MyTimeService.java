package com.baitian.daisongsong.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import android.support.annotation.Nullable;

/**
 * Created by daisongsong on 2015/8/4.
 */
public class MyTimeService extends Service {
    private ITimeService mITimeService;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        if (mITimeService == null) {
            mITimeService = new ITimeService.Stub() {
                @Override
                public long getCurrentTime() throws RemoteException {
                    return System.currentTimeMillis();
                }

                @Override
                public long getProcessId() throws RemoteException {
                    return android.os.Process.myPid();
                }

                @Override
                public long getThreadId() throws RemoteException {
                    return Thread.currentThread().getId();
                }
            };
        }

        return mITimeService.asBinder();
    }
}
