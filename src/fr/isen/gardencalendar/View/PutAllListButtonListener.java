package fr.isen.gardencalendar.View;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import fr.isen.gardencalendar.Model.Plant;
import fr.isen.gardencalendar.Model.PlantableMonth;
import fr.isen.gardencalendar.dal.PlantDb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François on 04/11/2014.
 */
public class PutAllListButtonListener implements Button.OnClickListener{
    private Activity activity;
    private PlantDb bdd;
    private ListView listView ;

    public PutAllListButtonListener(Activity activity, PlantDb bdd, ListView listView) {
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
        List<Plant> plants = bdd.getPlants();
        if(plants.size() == 0){
            // si rien n'a été trouvé on doit aller le chercher online
            Plant plant = new Plant();
            plant.setName("Oignon jaune");
            bdd.insertPlant(plant);
            plants = bdd.getPlants();
            plant = plants.get(0);
            PlantableMonth month = new PlantableMonth();
            month.setMonthNumber(6);
            month.setPlantId(plant.getId());
            bdd.insertMonth(month);

        }

        ArrayAdapter<Plant> adapter = new ArrayAdapter<Plant>(activity,
                android.R.layout.simple_list_item_1, android.R.id.text1, plants.toArray(new Plant[plants.size()]));
        // Assign adapter to ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new PlantClickListener(listView, bdd, activity));
    }
}
