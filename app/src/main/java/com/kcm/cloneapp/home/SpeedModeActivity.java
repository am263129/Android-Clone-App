package com.kcm.cloneapp.home;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.kcm.cloneapp.R;

public class SpeedModeActivity extends AppCompatActivity {
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    Switch switch_speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_mode);
        ImageView btn_back = (ImageView)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Boolean speed_mode = prefs.getBoolean("speed_mode", false);
        switch_speed = (Switch)findViewById(R.id.switch_speed);
        switch_speed.setChecked(speed_mode);
        switch_speed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putBoolean("speed_mode", isChecked);
                editor.apply();
            }
        });

    }
}
