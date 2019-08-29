package com.kcm.cloneapp.home.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import com.kcm.cloneapp.R;

public class ThemeAdapter extends RecyclerView.Adapter<ThemeAdapter.ThemeViewHolder> {

    private List<Integer> themeList;
    private Context context;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public ThemeAdapter(List<Integer> themeList, Context context) {
        this.themeList = themeList;
        this.context = context;
    }

    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_theme_app, viewGroup, false);

        return new ThemeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder viewHolder, final int i) {
        int themeImgIndex = themeList.get(i);
        viewHolder.img_theme.setImageResource(themeImgIndex);

        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        int prev_index = prefs.getInt("theme_index", 0);
        if (prev_index == i)
        {
            //show check icon
            viewHolder.img_check.setVisibility(View.VISIBLE);
        }else
        {
            //hide check icon
            viewHolder.img_check.setVisibility(View.INVISIBLE);
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putInt("theme_index", i);
                editor.apply();
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return themeList.size();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_theme;
        public ImageView img_check;
        public ThemeViewHolder(@NonNull View itemView) {
            super(itemView);
            img_theme = (ImageView)itemView.findViewById(R.id.img_theme);
            img_check = (ImageView)itemView.findViewById(R.id.img_check);
        }
    }
}
