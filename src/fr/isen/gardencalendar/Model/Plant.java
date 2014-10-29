package fr.isen.gardencalendar.Model;

/**
 * Created by François on 25/09/2014.
 */
public class Plant {
    private int id;

    public Plant() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

    @Override
    public String toString(){
        return name;
    }
}
