package fr.isen.gardencalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ShowCalendar extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(getApplicationContext(), "Hello world", Toast.LENGTH_LONG);
        Spinner plantList = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(this, R.array.plants_array, android.R.layout.simple_spinner_dropdown_item);

        plantList.setAdapter(list);
        plantList.setClickable(true);
        
        //à voir pour graphique :
        //librairies for developer
        //devappsdirect
        //penser à utiliser android studio <3

    }
}
