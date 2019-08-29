package com.kcm.cloneapp.home;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kcm.cloneapp.R;
import com.kcm.cloneapp.home.adapters.LanguageAdapter;
import com.kcm.cloneapp.home.adapters.ThemeAdapter;
import com.kcm.cloneapp.home.models.Singleton;

public class LanguageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        ImageView btn_back = (ImageView)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent languageIntent = new Intent(LanguageActivity.this, HomeActivity.class);
                LanguageActivity.this.startActivity(languageIntent);
                LanguageActivity.this.finish();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LanguageAdapter languageAdapter = new LanguageAdapter(Singleton.getInstance().languageList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(languageAdapter);

    }
    @Override
    public void onBackPressed()
    {
        Intent languageIntent = new Intent(LanguageActivity.this, HomeActivity.class);
        LanguageActivity.this.startActivity(languageIntent);
        LanguageActivity.this.finish();
    }

}
