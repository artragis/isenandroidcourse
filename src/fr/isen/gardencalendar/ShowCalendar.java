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


}
