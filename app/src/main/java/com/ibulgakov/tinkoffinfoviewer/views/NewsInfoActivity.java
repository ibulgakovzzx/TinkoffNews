package com.ibulgakov.tinkoffinfoviewer.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import com.ibulgakov.tinkoffinfoviewer.R;

public class NewsInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        if(intent == null) {
            finish();
            return;
        }

        String content = intent.getStringExtra("content");

        TextView tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText(TextUtils.isEmpty(content) ? getString(R.string.empty_content) : Html.fromHtml(content));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
