package hr.vsite.map.predavanje10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hr.vsite.map.predavanje10.MoraContract.Mora;

/**
 * Created by ivan on 03-May-16.
 */
public class MoraHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mora.db";
    private static final int DATABASE_VERSION = 3; //Provjerava svaki put helper je li se verzija pokrenila
    //Sad kreiraj SQL upit z akreiranje tablica
    private static final String SQL_CREATE =
            "CREATE TABLE " + Mora.TABLE_NAME + " (" +
                    Mora._ID + " INTEGER PRIMARY KEY," +
                    Mora.COL_NAME_SIFRA + " TEXT, " + //Ovdje je bila greška, nije bio space prije TEXT
                    Mora.COL_NAME_NAZIV + " TEXT " +
            " )";

    private static final String SQL_DROP =  "DROP TABLE " + Mora.TABLE_NAME;

    public MoraHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(SQL_DROP);
        database.execSQL(SQL_CREATE);
        // kad se verzija baze razlikuje izvedi neki kod: database.execSQL(DATABASE_DROP_CREATE);
    }
}
