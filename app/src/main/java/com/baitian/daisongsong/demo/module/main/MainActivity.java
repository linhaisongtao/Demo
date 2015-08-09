package com.baitian.daisongsong.demo.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baitian.daisongsong.demo.R;
import com.baitian.daisongsong.demo.base.BaseActivity;
import com.baitian.daisongsong.demo.module.leftmenu.LeftMenuActivity;
import com.baitian.daisongsong.demo.module.speed.SpeedActivity;

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
    }
}
