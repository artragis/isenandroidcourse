package fr.isen.gardencalendar.dal;

/**
 * Created by François on 09/10/2014.
 */
public class NotFetchedException extends RuntimeException{
    public NotFetchedException() {
        super("Les données désirées ne sont pas encore acheminées");
    }
}
