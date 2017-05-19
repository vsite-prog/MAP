package hr.vsite.map.predavanje11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hr.vsite.map.predavanje11.PticeContract.Ptice;

/**
 * Created by ivan on 15-May-17.
 */

public class PticeHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "ptice.db"; //Ovo je file baše baze
    final static int DATABASE_VERSION = 1; //OVU VERZIJU PROMATRAMO KOD Upgrade-a
    //Script za kreiranje I BRISANJE baze podataka
    final static String KREIRAJ_BAZU_SQL =
            "CREATE TABLE " + Ptice.TABLE_NAME + " ( "
            + Ptice._ID + " INTEGER PRIMARY KEY, "
            + Ptice.COL_IME + " TEXT, "
            + Ptice.COL_VRSTA + " TEXT )";

    final static String BRISI_BAZU_SQL = "DROP TABLE " +  Ptice.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(KREIRAJ_BAZU_SQL); //idemo kreirati našu tablicu
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //STARU ČITA IZ FILe-a a novu iz gornje varijable
        db.execSQL(BRISI_BAZU_SQL);
        db.execSQL(KREIRAJ_BAZU_SQL); //idemo kreirati našu tablicu

    }

    //Ovo je kontruktor kojji će inicijalizirati bazu
    public PticeHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
