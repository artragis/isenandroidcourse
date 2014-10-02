package fr.isen.gardencalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.List;

public class ShowCalendar extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Spinner plantList = (Spinner)findViewById(R.id.spinner);

        plantList.setClickable(true);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        Plant toBeAdded = new Plant();
        toBeAdded.setName("Haricot");
        PlantDb db = new PlantDb(getApplicationContext());
        db.open();
        db.insertPlant(toBeAdded);
        plantList.setAdapter(db.getPlantListAdapter());
        //ArrayAdapter<Plant> list = new ArrayAdapter<Plant>(getApplicationContext(), R.layout.main, added);
        //plantList.setAdapter(list);


        db.close();
        //à voir pour graphique :
        //librairies for developer
        //devappsdirect
        //penser à utiliser android studio <3

    }
}
