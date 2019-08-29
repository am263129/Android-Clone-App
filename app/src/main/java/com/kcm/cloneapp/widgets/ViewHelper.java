package com.kcm.cloneapp.widgets;

import com.kcm.cloneapp.VApp;

/**
 * @author Lody
 */
public class ViewHelper {

    public static int dip2px(float dpValue) {
        final float scale = VApp.getApp().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
