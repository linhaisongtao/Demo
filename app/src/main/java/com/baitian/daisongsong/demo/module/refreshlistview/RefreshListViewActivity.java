package com.baitian.daisongsong.demo.module.refreshlistview;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baitian.daisongsong.demo.R;
import com.baitian.daisongsong.demo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daisongsong on 2015/8/12.
 */
public class RefreshListViewActivity extends BaseActivity {
    private MyAdapter mMyAdapter;
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_refresh_list_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mMyAdapter = new MyAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);
    }


    private static class MyAdapter extends RecyclerView.Adapter {
        private List<String> mStrings;
        private Context mContext;

        public MyAdapter(Context context) {
            this.mContext = context;
            mStrings = initData();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(mContext, parent);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder) holder).bind(mStrings.get(position));
        }

        @Override
        public int getItemCount() {
            return mStrings.size();
        }

        private List<String> initData() {
            List<String> data = new ArrayList<>();
            char pre = 'A';
            for (int i = 0; i < 3; ++i) {
                pre += i;
                char c = 'A';
                for (int j = 0; j < 26; ++j) {
                    data.add(String.format("%c%c", pre, (c++)));
                }
            }
            return data;
        }

    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(Context context, ViewGroup parent) {
            super(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
        }

        public void bind(String str) {
            ((TextView) itemView).setText(str);
        }
    }


}
