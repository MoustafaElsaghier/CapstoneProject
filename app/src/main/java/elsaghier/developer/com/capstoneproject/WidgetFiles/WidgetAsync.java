package elsaghier.developer.com.capstoneproject.WidgetFiles;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;

import elsaghier.developer.com.capstoneproject.Models.SharedPreferenceFiles;
import elsaghier.developer.com.capstoneproject.R;

/**
 * Created by ELSaghier on 2/3/2018.
 */

public class WidgetAsync extends AsyncTask<Void, Void, String[]> {

    private Context mContext;
    private RemoteViews remoteViews;
    private AppWidgetManager appWidgetManager;
    private int appWidgetId;

    WidgetAsync(RemoteViews remoteViews, Context mContext, AppWidgetManager appWidgetManager,
                int appWidgetId) {
        this.mContext = mContext;
        this.remoteViews = remoteViews;
        this.appWidgetId = appWidgetId;
        this.appWidgetManager = appWidgetManager;
    }

    @Override
    protected String[] doInBackground(Void... strings) {
        String data[] = new String[2];
        data[0] = SharedPreferenceFiles.
                getFromSharedPreference(mContext, mContext.getString(R.string.film_name));
        data[1] = SharedPreferenceFiles.
                getFromSharedPreference(mContext, mContext.getString(R.string.rest_name));
        return data;
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);

        remoteViews.setTextViewText(R.id.appwidget_movieName, strings[0]);
        remoteViews.setTextViewText(R.id.appwidget_restaurantName, strings[1]);
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

    }
}
