package elsaghier.developer.com.capstoneproject.Models;

import android.content.Context;
import android.content.SharedPreferences;

import elsaghier.developer.com.capstoneproject.R;

/**
 * Created by ELSaghier on 2/2/2018.
 */

public class SharedPreferenceFiles {

    private static SharedPreferences sharedPref;

    private static void getSharedPreferences(Context context) {
        if (sharedPref == null) {
            sharedPref = context.getSharedPreferences(
                    context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        }
    }


    public static void saveToSharedPreference(Context context, String key, String value) {
        getSharedPreferences(context);
        sharedPref.edit().putString(key, value).apply();
    }

    public static String getFromSharedPreference(Context context, String key) {
        getSharedPreferences(context);
        return sharedPref.getString(key, context.getString(R.string.missing_data));
    }
}
