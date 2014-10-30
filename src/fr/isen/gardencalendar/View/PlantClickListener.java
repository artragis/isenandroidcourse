package fr.isen.gardencalendar.View;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.dal.PlantDb;

/**
 * Created by François on 30/10/2014.
 */
public class PlantClickListener implements AdapterView.OnItemClickListener {

    ListView listView ;
    PlantDb bdd;
    Context c;
    public PlantClickListener(ListView listView, PlantDb bdd, Context currentContext) {
        this.listView = listView;
        c = currentContext;
        this.bdd = bdd;
    }

    /**
     *
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
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(c,
                android.R.layout.simple_list_item_1, android.R.id.text1, bdd.getPlantableMonthForPlant(plant).toArray());
        bdd.getPlantableMonthForPlant(plant);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MonthClickListener(bdd, c, plant, listView));

    }
}
