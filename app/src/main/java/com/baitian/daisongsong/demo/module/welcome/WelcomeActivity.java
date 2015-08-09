package com.baitian.daisongsong.demo.module.welcome;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baitian.daisongsong.demo.R;
import com.baitian.daisongsong.demo.base.BaseActivity;
import com.baitian.daisongsong.demo.module.floatview.FloatService;
import com.baitian.daisongsong.demo.module.main.MainActivity;
import com.baitian.daisongsong.demo.service.ITimeService;
import com.baitian.daisongsong.demo.service.MyTimeService;
import com.baitian.daisongsong.demo.service.MyTimeService1;

public class WelcomeActivity extends BaseActivity {
    private ITimeService mITimeService;
    private Messenger mMessenger;
    private Messenger mClientMessenger = new Messenger(new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            switch (msg.what) {
                case 1:
                    TextView textView = (TextView) findViewById(R.id.mTextViewMessenger);
                    textView.setText(String.valueOf(msg.obj));
                    break;
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

//        bindService();

        findViewById(R.id.mTextViewTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WelcomeActivity.this, "Click", Toast.LENGTH_SHORT).show();
                TextView textView = (TextView) v;
                if (mITimeService != null) {
                    try {
                        textView.setText(String.format("时间戳:%d,进程ID:%d,线程ID:%d\n时间戳:%d,进程ID:%d,线程ID:%d",
                                        mITimeService.getCurrentTime(),
                                        mITimeService.getProcessId(),
                                        mITimeService.getThreadId(),
                                        System.currentTimeMillis(),
                                        android.os.Process.myPid(),
                                        Thread.currentThread().getId())
                        );
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

//        bindTimeService1();
        findViewById(R.id.mTextViewMessenger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                textView.setText(mMessenger + "");
                if (mMessenger != null) {
                    Message message = Message.obtain();
                    message.what = 1;
                    message.replyTo = mClientMessenger;
                    try {
                        mMessenger.send(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        findViewById(R.id.mButtonShowFloatView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, FloatService.class);
                startService(intent);
            }
        });

        findViewById(R.id.mButtonMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void bindTimeService1() {
        Intent intent = new Intent(this, MyTimeService1.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mMessenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);

    }

    private void bindService() {
        Intent intent = new Intent(this, MyTimeService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mITimeService = ITimeService.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}
