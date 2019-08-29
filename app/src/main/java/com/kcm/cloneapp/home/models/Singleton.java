package com.kcm.cloneapp.home.models;

import java.util.ArrayList;

/**
 * Created by Administrator on 2/23/2017.
 */

public class Singleton {
    public ArrayList<Integer> themeList;
    public ArrayList<LanguageNode> languageList;
    private static class SingletonHolder{
        private static final Singleton INSTANCE=new Singleton();
    }
    public static Singleton getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
}
