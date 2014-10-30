package fr.isen.gardencalendar.View;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.Model.PlantableMonth;
import fr.isen.gardencalendar.dal.CalendarAdapter;
import fr.isen.gardencalendar.dal.ContentProviderDal;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.List;

/**
 * Created by François on 30/10/2014.
 */
public class MonthClickListener implements AdapterView.OnItemClickListener {

    PlantDb bdd;
    Context c;
    Plant plant;
    ListView listView;

    public MonthClickListener(PlantDb bdd, Context c, Plant plant, ListView listView) {
        this.bdd = bdd;
        this.c = c;
        this.plant = plant;
        this.listView = listView;
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

        ContentProviderDal dal =  new ContentProviderDal(c);
        List<PlantableMonth> months =bdd.getPlantableMonthForPlant(plant);
        PlantableMonth selected = null;
        String monthName = ((TextView)view).getText().toString();
        for(PlantableMonth m : months){
            if(monthName.equalsIgnoreCase(m.toString())){
                selected = m;
                break;
            }
        }
        if(selected != null){
            dal.addPlant(plant, selected, 1);
        }
        else {
            Toast.makeText(c, "Une erreur a eu lieu lors de la sélection de l'objet", Toast.LENGTH_LONG);
        }
        listView.setAdapter(bdd.getPlantListAdapter());
        listView.setOnItemClickListener(new PlantClickListener(listView, bdd, c));
}
}
