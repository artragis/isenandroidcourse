package fr.isen.gardencalendar.dal;

import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import fr.isen.gardencalendar.R;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by François on 09/10/2014.
 */
public class CalendarAdapter  implements SelectionCompletedListener{

    private Cursor cur = null;
    private Context context;
    private boolean isFetched = false;
    private List<DataFetchListener> onDataFetched = new LinkedList<DataFetchListener>();
    public CalendarAdapter(Context context) {
        this.context = context;
    }

    public CursorAdapter getCursorAdapter() throws NotFetchedException{
        if(!isFetched){
            throw new NotFetchedException();
        }
        CursorAdapter adapter = new SimpleCursorAdapter(context, R.layout.planttextitem, cur,
                new String[]{CalendarContract.Calendars.ACCOUNT_NAME,
                        CalendarContract.Calendars.CALENDAR_DISPLAY_NAME},
                new int[]{R.layout.planttextitem, R.layout.planttextitem},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        return adapter;
    }
    public void addListener(DataFetchListener listener){
        onDataFetched.add(listener);
    }
    @Override
    public void onSelectionCompleted(Cursor cursor) {
        cur = cursor;
        isFetched = cursor != null;
        for(DataFetchListener listener: onDataFetched){
            listener.onDataFetched(getCursorAdapter());
        }

    }

    public  static class ListViewContentBinder implements DataFetchListener{
        ListView view;

        public ListViewContentBinder(ListView view) {
            this.view = view;
        }

        @Override
        public void onDataFetched(CursorAdapter adapter) {
            view.setAdapter(adapter);
        }
    }
}
