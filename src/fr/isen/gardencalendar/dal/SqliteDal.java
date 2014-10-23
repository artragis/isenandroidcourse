package fr.isen.gardencalendar.dal;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/**
 * Created by François on 25/09/2014.
 */
public class SqliteDal extends SQLiteOpenHelper {

    public SqliteDal(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public SqliteDal(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE plants ( _id INTEGER PRIMARY KEY AUTOINCREMENT, plant_name TEXT NOT NULL);");
        db.execSQL("CREATE TABLE plantable_days (_id INTEGER PRIMARY KEY AUTOINCREMENT, plant_id INTEGER NOT NULL, month_number INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS plants;");
        db.execSQL("DROP TABLE IF EXISTS plantable_days");
        onCreate(db);
    }
}
