package fr.isen.gardencalendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.*;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.Model.PlantableMonth;
import fr.isen.gardencalendar.dal.CalendarAdapter;
import fr.isen.gardencalendar.dal.ContentProviderDal;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.Calendar;
import java.util.List;

public class ShowCalendar extends Activity {
    ListView listView ;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTheme(R.style.AppTheme);
        setContentView(R.layout.main);

        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.listView);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android List View",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
        Plant toBeAdded = new Plant();
        toBeAdded.setName("Haricot");

        PlantDb bdd = new PlantDb(getApplicationContext());
        bdd.open();
        bdd.insertPlant(toBeAdded);


        // Assign adapter to ListView
        listView.setAdapter(bdd.getPlantListAdapter());
        Log.d("d","after adapter");
        CalendarAdapter cal = new CalendarAdapter(getApplicationContext());
        cal.addListener(new CalendarAdapter.ListViewContentBinder((ListView)findViewById(R.id.listView)));
        ContentProviderDal calendarDal = new ContentProviderDal(getApplicationContext());
        //calendarDal.addPlant(toBeAdded, 1);
        calendarDal.addSelectionCompletedListener(cal);
        calendarDal.startSelect();

        bdd.close();

    }
}
