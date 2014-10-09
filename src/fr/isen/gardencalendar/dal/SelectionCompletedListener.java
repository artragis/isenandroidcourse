package fr.isen.gardencalendar.dal;

import android.database.Cursor;
import android.widget.CursorAdapter;

/**
 * Created by François on 09/10/2014.
 */
public interface SelectionCompletedListener {
    void onSelectionCompleted(Cursor cursor);
}
