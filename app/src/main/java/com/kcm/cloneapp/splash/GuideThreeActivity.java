package com.kcm.cloneapp.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.kcm.cloneapp.R;
import com.kcm.cloneapp.home.HomeActivity;

public class GuideThreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_three);
        interstitial();
        Button nextBtn = (Button) findViewById(R.id.btn_next);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xx=1;
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }else{
                    xx=0;
                    Intent mainIntent = new Intent(GuideThreeActivity.this, HomeActivity.class);
                    GuideThreeActivity.this.startActivity(mainIntent);
                    GuideThreeActivity.this.finish();
                }

            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent prevIntent = new Intent(GuideThreeActivity.this, GuideTwoActivity.class);
        GuideThreeActivity.this.startActivity(prevIntent);
        GuideThreeActivity.this.finish();
    }

    //interstitial
    int xx=0;
    InterstitialAd mInterstitialAd;
    private void interstitial(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.admob_interstitial));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                if (xx==1) {
                    xx=0;
                    Intent mainIntent = new Intent(GuideThreeActivity.this, HomeActivity.class);
                    GuideThreeActivity.this.startActivity(mainIntent);
                    GuideThreeActivity.this.finish();
                }
            }
        });
        requestNewInterstitial();}
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);  }
}
