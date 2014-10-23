package fr.isen.gardencalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.Model.PlantableMonth;
import fr.isen.gardencalendar.dal.CalendarAdapter;
import fr.isen.gardencalendar.dal.ContentProviderDal;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.Calendar;
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
        Plant haricot = new Plant();

        plantList.setClickable(true);

        Plant toBeAdded = new Plant();
        PlantableMonth pm = new PlantableMonth();
        pm.setMonthNumber(3);
        pm.setPlantId(1);
        toBeAdded.setName("Haricot");
        PlantDb db = new PlantDb(getApplicationContext());
        db.open();
        db.insertPlant(toBeAdded);
        db.insertMonth(pm);
        plantList.setAdapter(db.getPlantListAdapter());
        //ArrayAdapter<Plant> list = new ArrayAdapter<Plant>(getApplicationContext(), R.layout.main, added);
        //plantList.setAdapter(list);

        plantList.setClickable(true);
        try{
            toBeAdded.setId(1);
            db.getPlantableMonthForPlant(toBeAdded);
        }catch (Exception e){

        }

        //getContentResolver().
        db.close();

        CalendarAdapter cal = new CalendarAdapter(getApplicationContext());
        cal.addListener(new CalendarAdapter.ListViewContentBinder((ListView)findViewById(R.id.listView)));
        ContentProviderDal calendarDal = new ContentProviderDal(getApplicationContext());
        //calendarDal.addPlant(toBeAdded, 1);
        calendarDal.addSelectionCompletedListener(cal);
        calendarDal.startSelect();
        //à voir pour graphique :
        //librairies for developer
        //devappsdirect
        //penser à utiliser android studio <3

    }
}
