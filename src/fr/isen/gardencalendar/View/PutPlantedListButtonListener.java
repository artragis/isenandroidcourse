package fr.isen.gardencalendar.View;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import fr.isen.gardencalendar.dal.PlantDb;

/**
 * Created by François on 04/11/2014.
 */
public class PutPlantedListButtonListener implements Button.OnClickListener {

    private Activity activity;
    private PlantDb bdd;
    private ListView listView ;

    public PutPlantedListButtonListener(Activity activity, PlantDb bdd, ListView listView) {
        this.activity = activity;
        this.bdd = bdd;
        this.listView = listView;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
