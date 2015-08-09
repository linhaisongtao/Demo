package com.baitian.daisongsong.demo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by daisongsong on 2015/8/4.
 */
public class MyTimeService1 extends Service {
    private Messenger mMessenger = new Messenger(new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            Log.d("MyTimeService1", msg + " " + msg.replyTo);
            switch (msg.what) {
                case 1:
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = "Hello Messenger !";
                    try {
                        if(msg.replyTo != null){
                            msg.replyTo.send(message);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

}
