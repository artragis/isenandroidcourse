package fr.isen.gardencalendar.dal;

import android.content.AsyncQueryHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import fr.isen.gardencalendar.Model.Plant;

import java.util.*;

/**
 * Created by François on 09/10/2014.
 */
public class ContentProviderDal extends AsyncQueryHandler {
    public static final String[] EVENT_PROJECTION = new String[] {
            CalendarContract.Events._ID,                           // 0
            CalendarContract.Events.TITLE,                  // 1
            CalendarContract.Events.DESCRIPTION,         // 2
            CalendarContract.Events.DTSTART,
            CalendarContract.Events.DTEND// 3
    };
    public static final int PROJECTION_ID_INDEX = 0;
    public static final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
    public static final int PROJECTION_DISPLAY_NAME_INDEX = 2;
    public static final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;
    public List<SelectionCompletedListener> selectionCompletedListeners = new LinkedList<SelectionCompletedListener>();


    public ContentProviderDal(Context context) {
        super(context.getContentResolver());


    }
    public void addPlant(Plant plant, long calID){
        Date date = new Date();
        date.setTime(date.getTime() + 60000);
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, date.getTime());
        values.put(CalendarContract.Events.TITLE, plant.getName());
        values.put(CalendarContract.Events.DESCRIPTION, "Savez vous planter des choux?");
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        values.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Paris");
        values.put(CalendarContract.Events.DTEND, date.getTime() + 3600000);
        startInsert(2, null, CalendarContract.Events.CONTENT_URI, values);
    }
    public void startSelect(){

        String selection =  CalendarContract.Events.DTSTART +" > ? AND "+ CalendarContract.Events.DTEND +" < ?";
        String[] selectionArgs = new String[] {String.valueOf( new Date().getTime() - 7200000) , String.valueOf( new Date().getTime() + 7200000)};
        super.startQuery(1, null, CalendarContract.Events.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs, null);

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
