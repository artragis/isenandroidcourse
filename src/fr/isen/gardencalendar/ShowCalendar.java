package fr.isen.gardencalendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.*;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.Model.PlantableMonth;
import fr.isen.gardencalendar.View.PlantClickListener;
import fr.isen.gardencalendar.View.PutAllListButtonListener;
import fr.isen.gardencalendar.dal.CalendarAdapter;
import fr.isen.gardencalendar.dal.ContentProviderDal;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShowCalendar extends Activity {
    ListView listView ;
    PlantDb bdd;
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
        listView.setItemsCanFocus(true);
        listView.setClickable(true);
        // Defined Array values to show in ListView
        String[] values = null;
        Plant toBeAdded = new Plant();
        toBeAdded.setName("Poirreaux");


        bdd = new PlantDb(getApplicationContext());
        bdd.open();

        Button listAllButton = (Button) findViewById(R.id.liste_toute_button);
        listAllButton.setOnClickListener(new PutAllListButtonListener(this, bdd, listView));
        listAllButton.callOnClick();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bdd.close();
    }


}
