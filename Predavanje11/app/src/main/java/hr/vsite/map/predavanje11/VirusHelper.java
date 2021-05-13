package hr.vsite.map.predavanje11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hr.vsite.map.predavanje11.VirusContract.Virus;

public class VirusHelper extends SQLiteOpenHelper {

    final static int DATABASE_VERSION = 1; // Ja postavljam verziju baze

    // KOnstruktor koji pokreće bazu
    public VirusHelper(Context context){
        super(context, VirusContract.DATABASE_NAME, null, DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ovdje kreiram bazu tj. tablice
        db.execSQL(Virus.CREATE_TABLE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Ovdje dižem, verziju ima i downgrade
        // Vidim koja je nova verzija u kodu ali znam i koja je stara
    }
}
