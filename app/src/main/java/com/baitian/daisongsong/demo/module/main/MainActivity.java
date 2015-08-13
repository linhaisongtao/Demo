package com.baitian.daisongsong.demo.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baitian.daisongsong.demo.R;
import com.baitian.daisongsong.demo.base.BaseActivity;
import com.baitian.daisongsong.demo.module.leftmenu.LeftMenuActivity;
import com.baitian.daisongsong.demo.module.okhttpdemo.OkHttpActivity;
import com.baitian.daisongsong.demo.module.refreshlistview.RefreshListViewActivity;
import com.baitian.daisongsong.demo.module.speed.SpeedActivity;
import com.baitian.daisongsong.demo.utils.T;

/**
 * Created by daisongsong on 2015/7/30.
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.mButtonLeftMenuDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeftMenuActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.mButtonSpeedDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SpeedActivity.class));
            }
        });

        findViewById(R.id.mButtonShowToast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.show("mButtonShowToast");
            }
        });

        findViewById(R.id.mButtonRefreshListView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RefreshListViewActivity.class));
            }
        });

        findViewById(R.id.mButtonResetContentView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_main);
            }
        });

        findViewById(R.id.mButtonOkHttp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OkHttpActivity.class));
            }
        });
    }
}
