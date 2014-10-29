package fr.isen.gardencalendar.Model;

/**
 * Created by François on 23/10/2014.
 */
public class PlantableMonth {
    private int id;
    private int plantId;
    private int monthNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    @Override
    public String toString() {
        String[] m = {"","Janvier", "Fevrier", "Mars", "Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Decembre"};
        return m[getMonthNumber()];
    }
}
