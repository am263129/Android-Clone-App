package com.kcm.cloneapp.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.kcm.cloneapp.R;

public class GuideTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_two);

        Button nextBtn = (Button) findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(GuideTwoActivity.this, GuideThreeActivity.class);
                GuideTwoActivity.this.startActivity(mainIntent);
                GuideTwoActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        Intent prevIntent = new Intent(GuideTwoActivity.this, GuideOneActivity.class);
        GuideTwoActivity.this.startActivity(prevIntent);
        GuideTwoActivity.this.finish();
    }
}
