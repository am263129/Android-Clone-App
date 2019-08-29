package com.kcm.cloneapp.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kcm.cloneapp.R;

public class GuideOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_one);

        Button nextBtn = (Button) findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GuideOneActivity.this, GuideTwoActivity.class);
                GuideOneActivity.this.startActivity(mainIntent);
                GuideOneActivity.this.finish();
            }
        });
    }
}
