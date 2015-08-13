package com.baitian.daisongsong.demo.module.okhttpdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baitian.daisongsong.demo.R;
import com.baitian.daisongsong.demo.base.BaseActivity;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by daisongsong on 2015/8/13.
 */
public class OkHttpActivity extends BaseActivity {
    private TextView mTextViewResult;
    private EditText mEditTextUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_demo);

        mTextViewResult = (TextView) findViewById(R.id.mTextViewResult);
        mEditTextUrl = (EditText) findViewById(R.id.mEditTextUrl);

        findViewById(R.id.mButtonSendReuqest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpAsyncTask okHttpAsyncTask = new OkHttpAsyncTask();
                String url = mEditTextUrl.getText().toString();
                if (TextUtils.isEmpty(url)) {
                    url = mEditTextUrl.getHint().toString();
                }
                okHttpAsyncTask.execute(url);
            }
        });
    }

    private class OkHttpAsyncTask extends AsyncTask<String, Integer, String[]> {
        private OkHttpClient mOkHttpClient = new OkHttpClient();

        @Override
        protected String[] doInBackground(String... params) {
            String url = params[0];
            String[] result = new String[2];
            Request request = new Request.Builder().url(url).build();
            try {
                Response response = mOkHttpClient.newCall(request).execute();
                result[0] = String.valueOf(response.code());
                result[1] = new String(response.body().bytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            mTextViewResult.setText(String.format("%s\n%s", s[0], s[1]));
        }
    }
}
