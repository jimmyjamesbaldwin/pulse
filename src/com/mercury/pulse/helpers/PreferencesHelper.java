package com.mercury.pulse.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferencesHelper {

	public boolean loadPreference(Context context, String key) {
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		return mSharedPreferences.getBoolean(key, false);
	}

	public void savePreference(Context context, String key, boolean value) {
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		Editor editor = mSharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}
