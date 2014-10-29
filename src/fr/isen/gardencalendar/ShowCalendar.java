package fr.isen.gardencalendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.*;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.Model.PlantableMonth;
import fr.isen.gardencalendar.dal.CalendarAdapter;
import fr.isen.gardencalendar.dal.ContentProviderDal;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShowCalendar extends Activity implements AdapterView.OnItemClickListener{
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
        bdd.insertPlant(toBeAdded);
        List<Plant> plants = bdd.getPlants();
        List<String> plantName = new ArrayList<String>();
        for(Plant p1: plants){
            plantName.add(p1.getName());
            PlantableMonth m = new PlantableMonth();
            m.setPlantId(p1.getId());
            m.setMonthNumber(8);
            bdd.insertMonth(m);

        }
        ArrayAdapter<Plant> adapter = new ArrayAdapter<Plant>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, plants.toArray(new Plant[plants.size()]));
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        Log.d("size", String.valueOf(plantName.size()));
        Log.d("d","after adapter");
/*        CalendarAdapter cal = new CalendarAdapter(getApplicationContext());
        cal.addListener(new CalendarAdapter.ListViewContentBinder((ListView)findViewById(R.id.listView)));
        ContentProviderDal calendarDal = new ContentProviderDal(getApplicationContext());
        //calendarDal.addPlant(toBeAdded, 1);
        calendarDal.addSelectionCompletedListener(cal);
        calendarDal.startSelect();
*/      listView.setOnItemClickListener(this);
        //bdd.close();

    }

    /**
     * Perform any final cleanup before an activity is destroyed.  This can
     * happen either because the activity is finishing (someone called
     * {@link #finish} on it, or because the system is temporarily destroying
     * this instance of the activity to save space.  You can distinguish
     * between these two scenarios with the {@link #isFinishing} method.
     * <p/>
     * <p><em>Note: do not count on this method being called as a place for
     * saving data! For example, if an activity is editing data in a content
     * provider, those edits should be committed in either {@link #onPause} or
     * {@link #onSaveInstanceState}, not here.</em> This method is usually implemented to
     * free resources like threads that are associated with an activity, so
     * that a destroyed activity does not leave such things around while the
     * rest of its application is still running.  There are situations where
     * the system will simply kill the activity's hosting process without
     * calling this method (or any others) in it, so it should not be used to
     * do things that are intended to remain around after the process goes
     * away.
     * <p/>
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onPause
     * @see #onStop
     * @see #finish
     * @see #isFinishing
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        bdd.close();
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Plant plant = bdd.getPlants().get(position);
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, bdd.getPlantableMonthForPlant(plant).toArray());
        bdd.getPlantableMonthForPlant(plant);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(null);
    }
}
