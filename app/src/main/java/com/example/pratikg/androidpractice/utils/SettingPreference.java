package com.example.pratikg.androidpractice.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SettingPreference {

    public static final String PREFS_NAME = "mySettings";

    public static void setStringValueInPref(Context context, List<String> prefKey,
                                            int actualValue) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE).edit();
       /* Set<String> set = new HashSet<String>();
        set.addAll(prefKey);
        editor.putStringSet("key", set);*/

        for(int i = 0; i<prefKey.size(); i++) {
            editor.putString("position" + i, prefKey.get(i));
        }

        editor.commit();

    }

    public static boolean addStudentToPref(Context context, List<Integer> prefKey,
                                           int actualValue)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor mEdit1 = sp.edit();
        /* sKey is an array */
        mEdit1.putInt("Status_size", prefKey.size());
        for(int i=0;i<prefKey.size();i++)
        {
            mEdit1.remove("Status_" + i);
            mEdit1.putInt("Status_" + i, prefKey.get(i));
        }

        return mEdit1.commit();
    }

    public static List<Integer> loadStudentFromPref(Context mContext)
    {
        List<Integer>sKey =new ArrayList<>();
        SharedPreferences mSharedPreference1 =   PreferenceManager.getDefaultSharedPreferences(mContext);
        sKey.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            sKey.add(mSharedPreference1.getInt("Status_" + i, 0));
        }

        return sKey;
    }
}
