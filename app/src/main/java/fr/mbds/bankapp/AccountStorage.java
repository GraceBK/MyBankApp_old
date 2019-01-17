/*
 * Created by Grace BK on 1/14/19 12:16 PM
 *
 * Copyright (c) 2019. School project.
 */

package fr.mbds.bankapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class AccountStorage {

    /* TODO mettre dans un sharePreference des information concernant mon identifiant.
    ID : d'un compte
     */

    private static final String TAG = "AccountStorage";

    private static final String PREF_ACCOUNT_NUMBER = "account_id";


    public static void setAccount(Context context, String id) {
        Log.i(TAG, "Sauvegarde du compte : " + id);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(PREF_ACCOUNT_NUMBER, id).apply();
    }

    public static String getAccount(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getString(PREF_ACCOUNT_NUMBER, "");
    }

}
