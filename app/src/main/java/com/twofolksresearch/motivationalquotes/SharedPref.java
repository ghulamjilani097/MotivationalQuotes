package com.twofolksresearch.motivationalquotes;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref
{
    private Context context;
    private boolean language;
    private SharedPreferences sharedPreferences;

    SharedPref(Context context)
    {
        this.context=context;
        sharedPreferences= (SharedPreferences) context.getSharedPreferences("Userinfo",Context.MODE_PRIVATE);
    }

    boolean getLaguage() {
        language=sharedPreferences.getBoolean("lang",false);
       // Log.d("pass","Pass: "+password);
        return language;
    }

    void setLanguage(boolean language) {
        this.language = language;
        sharedPreferences.edit().putBoolean("lang",true).apply();
    }
}