package com.kcm.cloneapp.home.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import com.kcm.cloneapp.R;
import com.kcm.cloneapp.home.HomeActivity;
import com.kcm.cloneapp.home.LanguageActivity;
import com.kcm.cloneapp.home.models.LanguageNode;

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {
    private List<LanguageNode> languageList;
    private Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public LanguageAdapter(List<LanguageNode> languageList, Context context) {
        this.languageList = languageList;
        this.context = context;
    }

    @Override
    public LanguageAdapter.LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_language_app, viewGroup, false);

        return new LanguageAdapter.LanguageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageAdapter.LanguageViewHolder viewHolder, final int i) {
        LanguageNode languageNode = languageList.get(i);

        viewHolder.txt_language.setText(languageNode.language_name);

        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        String prev_language = prefs.getString("language_index", "en");
        if (prev_language.equals(languageNode.language_code))
        {
            //check icon
            viewHolder.img_check.setImageResource(R.drawable.ic_check);
        }else
        {
            //uncheck icon
            viewHolder.img_check.setImageResource(R.drawable.ic_no_check);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putString("language_index", languageNode.language_code);
                editor.apply();
                //set language
                Resources res = context.getResources();
                // Change locale settings in the app.
                DisplayMetrics dm = res.getDisplayMetrics();
                android.content.res.Configuration conf = res.getConfiguration();
                if (languageNode.language_code.equals("en"))
                {
                    conf.setLocale(Locale.ENGLISH);
                }else
                {
                    conf.setLocale(new Locale(languageNode.language_code)); // API 17+ only.
                }
                // Use conf.locale = new Locale(...) if targeting lower versions
                res.updateConfiguration(conf, dm);
                Intent languageIntent = new Intent(context, HomeActivity.class);
                context.startActivity(languageIntent);
                ((Activity) context).finish();

            }
        });
    }

    @Override
    public int getItemCount() {
        return languageList.size();
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_language;
        public ImageView img_check;
        public LanguageViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_language = (TextView)itemView.findViewById(R.id.txt_language);
            img_check = (ImageView)itemView.findViewById(R.id.img_check);
        }
    }
}
