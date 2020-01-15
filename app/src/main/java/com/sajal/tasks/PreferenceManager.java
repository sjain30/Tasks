package com.sajal.tasks;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private Context context;
    private SharedPreferences sharedPreferences;
    private static final String PREFERNCE="Preference";

    public PreferenceManager(Context context) {
        this.context = context;
        getSharedPreference();
    }

    private void getSharedPreference()
    {
        sharedPreferences=context.getSharedPreferences(context.getString(R.string.Preference),Context.MODE_PRIVATE);
    }
    public void writeSharedPrefernce(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.Preference),"OK");
        editor.commit();
    }

    public boolean checkPreference(){
        boolean status = false;
        if (!sharedPreferences.getString(context.getString(R.string.Preference),"null").equals("null")){
            status=true;
        }
        return status;
    }

    public void clearPreference(){
        sharedPreferences.edit().clear().commit();
    }
}
