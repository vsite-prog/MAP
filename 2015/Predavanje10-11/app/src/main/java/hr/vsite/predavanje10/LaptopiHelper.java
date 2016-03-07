package hr.vsite.predavanje10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hr.vsite.predavanje10.LaptopContract.Laptop;

/**
 * Created by IBritvic on 15.12.2014..
 */
public class LaptopiHelper extends SQLiteOpenHelper {
    private final String DATABASE_CREATE = "CREATE TABLE " + Laptop.TABLE_NAME
                                        + "( _ID INT PRIMARY KEY, "
                                        + Laptop.COLUMN_NAZIV + " TEXT, "
                                        + Laptop.COLUMN_TIP + " TEXT)";


    public LaptopiHelper(Context context) {
        super(context, LaptopContract.DATABASE_NAME, null, LaptopContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    { database.execSQL(DATABASE_CREATE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_CREATE);
    }
}
