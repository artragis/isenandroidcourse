package fr.isen.gardencalendar.dal;

import android.content.AsyncQueryHandler;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by François on 09/10/2014.
 */
public class ContentProviderDal extends AsyncQueryHandler {
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Calendars._ID,                           // 0
            CalendarContract.Calendars.ACCOUNT_NAME,                  // 1
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,         // 2
            CalendarContract.Calendars.OWNER_ACCOUNT                  // 3
    };
    public static final int PROJECTION_ID_INDEX = 0;
    public static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    public static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    public static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    public List<SelectionCompletedListener> selectionCompletedListeners = new LinkedList<SelectionCompletedListener>();


    public ContentProviderDal(Context context) {
        super(context.getContentResolver());


    }

    public void startSelect(){

        String selection = CalendarContract.Calendars.ACCOUNT_NAME + " = ? AND "+ CalendarContract.Calendars.OWNER_ACCOUNT
                + " = ?";
        String[] selectionArgs = new String[] {"francois.dambrine@isen-lille.fr",
                "francois.dambrine@isen-lille.fr"};
        super.startQuery(1, null, CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs, null);

    }
    public void addSelectionCompletedListener(SelectionCompletedListener listener){
        selectionCompletedListeners.add(listener);
    }
    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        super.onQueryComplete(token, cookie, cursor);
        if(token == 1) {

            for (SelectionCompletedListener listener : selectionCompletedListeners) {
                listener.onSelectionCompleted(cursor);
            }
        }
    }
}
