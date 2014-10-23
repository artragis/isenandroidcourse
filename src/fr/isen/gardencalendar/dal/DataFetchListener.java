package fr.isen.gardencalendar.dal;

import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;

/**
 * Created by François on 09/10/2014.
 */
public interface DataFetchListener {
    void onDataFetched(ArrayAdapter adapter);
}
