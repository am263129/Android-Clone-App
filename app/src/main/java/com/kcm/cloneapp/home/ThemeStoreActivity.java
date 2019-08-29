package com.kcm.cloneapp.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import com.kcm.cloneapp.R;
import com.kcm.cloneapp.home.adapters.ThemeAdapter;
import com.kcm.cloneapp.home.models.Singleton;

public class ThemeStoreActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_store);
        ImageView btn_back = (ImageView)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
//        recyclerView.setLayoutManager(layoutManager);

        ThemeAdapter themeAdapter = new ThemeAdapter(Singleton.getInstance().themeList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(themeAdapter);


    }
}
