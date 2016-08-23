package com.peng.tradeprogressview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by PS on 2016/8/23.
 */
public class MainActivity extends AppCompatActivity{

    private TradeProgressView view = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        view = (TradeProgressView) findViewById(R.id.progress_view);
        view.setType(1).setContent("刷卡出款  ¥10000.00", "银行处理中", "入账  ¥10000.00").invalidate();
    }
}
