package elsaghier.developer.com.capstoneproject.Models;

import android.content.Context;
import android.content.SharedPreferences;

import elsaghier.developer.com.capstoneproject.R;

/**
 * Created by ELSaghier on 2/2/2018.
 */

public class SharedPreferenceFiles {

    private static SharedPreferences sharedPref;

    private static SharedPreferences getSharedPreferences(Context context) {
        if (sharedPref == null) {
            context.getSharedPreferences(
                    context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        }
        return sharedPref;
    }


    public static void saveToSharedPreference(Context context, String key, String value) {
        getSharedPreferences(context);
        sharedPref.edit().putString(key, value).apply();
    }

    public static String getFromSharedPreference(Context context, String key) {
        return sharedPref.getString(key, "No saved Value");
    }
}
