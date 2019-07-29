package com.leonyr.easyutils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.leonyr.lib.luban.Luban;
import com.leonyr.lib.luban.OnRenameListener;
import com.leonyr.lib.utils.SpanUtil;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvMain;
    MAdapter mAdapter;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMain = findViewById(R.id.rv_main);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MAdapter();
        rvMain.setAdapter(mAdapter);

        random = new Random();

        findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = random.nextInt(100);
                rvMain.scrollToPosition(0);
                mAdapter.addItem(String.valueOf(result));
            }
        });
    }

}
