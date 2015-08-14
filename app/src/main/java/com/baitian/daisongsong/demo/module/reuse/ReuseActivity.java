package com.baitian.daisongsong.demo.module.reuse;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baitian.daisongsong.demo.R;
import com.baitian.daisongsong.demo.base.BaseActivity;
import com.baitian.daisongsong.demo.utils.T;

/**
 * Created by daisongsong on 2015/8/14.
 */
public class ReuseActivity extends BaseActivity {
    private ReuseLayout mReuseLayout;
    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reuse);

        mReuseLayout = (ReuseLayout) findViewById(R.id.mReuseLayout);
        mScrollView = (ScrollView) findViewById(R.id.mScrollView);

        findViewById(R.id.mButtonShowFirstVisiblePosition).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                for (int i = 0; i < mReuseLayout.getChildCount(); ++i) {
                    View v1 = mReuseLayout.getChildAt(i);
                    if (v1.getVisibility() != View.VISIBLE) {
                        position = i;
                        break;
                    }
                }

                T.show("position=" + position + "," + mScrollView.getScrollY());
            }
        });


        for (int i = 0; i < 50; ++i) {
            TextView textView = new TextView(this);
            textView.setText("i=" + i);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            textView.setLayoutParams(lp);
            mReuseLayout.addView(textView);
        }

    }
}
