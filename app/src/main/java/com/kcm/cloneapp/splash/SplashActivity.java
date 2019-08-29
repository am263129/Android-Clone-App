package com.kcm.cloneapp.splash;


import android.app.Activity;
        import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
        import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.kcm.cloneapp.R;
import com.kcm.cloneapp.home.HomeActivity;
import com.kcm.cloneapp.home.models.LanguageNode;
import com.kcm.cloneapp.home.models.Singleton;

public class SplashActivity extends Activity {
    private int xx;
    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 50;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(icicle);
        setContentView(R.layout.activity_splash);
//        try {
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED ) {
//                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_SMS}, 101);
//            }
//            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED ) {
//                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_CALL_LOG}, 101);
//            }
//
//        } catch (Exception e){
//            e.printStackTrace();
//        }



        ArrayList<Integer> themeList = new ArrayList<Integer>();
        themeList.add(R.drawable.theme_0);
        themeList.add(R.drawable.theme_1);
        themeList.add(R.drawable.theme_2);
        themeList.add(R.drawable.theme_3);
        themeList.add(R.drawable.theme_4);
        themeList.add(R.drawable.theme_5);
        Singleton.getInstance().themeList = themeList;

        ArrayList<LanguageNode> languageList = new ArrayList<LanguageNode>();
        languageList.add(new LanguageNode("العربية","ar"));
        languageList.add(new LanguageNode("български","bg"));
        languageList.add(new LanguageNode("Català","ca"));
        languageList.add(new LanguageNode("čeština","cs"));
        languageList.add(new LanguageNode("dansk","da"));
        languageList.add(new LanguageNode("Deutsche","de"));
        languageList.add(new LanguageNode("Ελληνικά","el"));
        languageList.add(new LanguageNode("English","en"));
        languageList.add(new LanguageNode("Español","es"));
        languageList.add(new LanguageNode("suomi","fi"));
        languageList.add(new LanguageNode("Française","fr"));
        languageList.add(new LanguageNode("हिंदी","hi"));
        languageList.add(new LanguageNode("Hrvatski","hr"));
        languageList.add(new LanguageNode("Magyar","hu"));
        languageList.add(new LanguageNode("bahasa Indonesia","in"));
        languageList.add(new LanguageNode("Italiana","it"));
        languageList.add(new LanguageNode("עברית","iw"));
        languageList.add(new LanguageNode("日本語","ja"));
        languageList.add(new LanguageNode("한국어","ko"));
        languageList.add(new LanguageNode("Lietuvių","lt"));
        languageList.add(new LanguageNode("Latviešu","lv"));
        languageList.add(new LanguageNode("norsk","nb"));
        languageList.add(new LanguageNode("Nederlands","nl"));
        languageList.add(new LanguageNode("polski","pl"));
        languageList.add(new LanguageNode("Português","pt"));
        languageList.add(new LanguageNode("Română","ro"));
        languageList.add(new LanguageNode("русский","ru"));
        languageList.add(new LanguageNode("slovenčina","sk"));
        languageList.add(new LanguageNode("Slovenščina","sl"));
        languageList.add(new LanguageNode("Српски","sr"));
        languageList.add(new LanguageNode("svenska","sv"));
        languageList.add(new LanguageNode("ภาษาไทย","th"));
        languageList.add(new LanguageNode("Türkçe","tr"));
        languageList.add(new LanguageNode("Українська","uk"));
        languageList.add(new LanguageNode("Tiếng Việt","vi"));
        languageList.add(new LanguageNode("中文","zh"));

        Singleton.getInstance().languageList = languageList;


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Boolean opened_status = prefs.getBoolean("opened", false);
        if (!opened_status) {
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            //set default theme
            editor.putInt("theme_index", 0);
            //set default language
            editor.putString("language_index", "en");
            //set speed mode as false
            editor.putBoolean("speed_mode", false);
            editor.putBoolean("opened", true);
            editor.apply();
        }

        //set language
        Resources res = this.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        if (prefs.getString("language_index", "en").equals("en"))
        {
            conf.setLocale(Locale.ENGLISH);
        }else
        {
            conf.setLocale(new Locale(prefs.getString("language_index", "en"))); // API 17+ only.
        }

        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                if (!opened_status)
                {
                    Intent mainIntent = new Intent(SplashActivity.this, GuideOneActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }else
                {
                    Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}