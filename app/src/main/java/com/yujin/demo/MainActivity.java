package com.yujin.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.yujin.demo.demo.R;
import com.yujin.view.XListView;
import com.yujin.view.XListView.IXListViewListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = "MainActivity";

    private XListView mListView;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        mListView.setPullRefreshEnable(true);
        mListView.setXListViewListener(new IXListViewListener() {

            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mListView.stopRefresh();
                        Toast.makeText(MainActivity.this, "refresh", Toast.LENGTH_SHORT).show();

                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                        String dateStr = sdf.format(calendar.getTime());
                        mListView.setRefreshTime(dateStr);
                    }
                }, 1000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    // ģ�������ݣ�1s֮��ֹͣ����
                    @Override
                    public void run() {
                        mListView.stopLoadMore();
                        Toast.makeText(MainActivity.this, "loadMore",
                                Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });
        mListView.setAdapter(new ListViewAdapter(this));
    }
}
