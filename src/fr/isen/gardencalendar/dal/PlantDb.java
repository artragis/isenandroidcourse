package fr.isen.gardencalendar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.isen.gardencalendar.Model.Plant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by François on 25/09/2014.
 */
public class PlantDb {


    private SQLiteDatabase bdd;

    private SqliteDal maBaseSQLite;

    public PlantDb(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new SqliteDal(context, "blaaa", null, 1);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertLivre(Plant plant){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put("name", plant.getName());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert("plants", null, values);
    }

    public int updatePlant(int id, Plant plant){
        //La mise à jour d'un plant dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle plant on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();

        values.put("name", plant.getName());
        return bdd.update("plants", values, "id" + " = " +id, null);
    }

    public int removePlantWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete("plants", "id" + " = " +id, null);
    }

    public List<Plant> getPlants(){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query("plants", new String[]{"id", "name"}, null, null, null, null, null);
        List<Plant> result = new ArrayList<Plant>();

        for(int i=0; i<c.getCount(); i++){

            result.add(cursorToPlant(c));

        }
        c.close();
        return result;
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Plant cursorToPlant(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToNext();
        //On créé un livre
        Plant livre = new Plant();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        livre.setId(c.getInt(0));
        livre.setName(c.getString(1));



        //On retourne le livre
        return livre;
    }
}
