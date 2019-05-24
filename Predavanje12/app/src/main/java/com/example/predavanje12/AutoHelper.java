package com.example.predavanje12;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.predavanje12.AutoContract.Auto;

public class AutoHelper extends SQLiteOpenHelper {
    // naziv file-a i trenutna verzija
    public static final String IME_BAZE = "Automobili";
    public static final int VERZIJA_BAZE = 1;

    // Skript za kreiranje tablica u bazi
    public static final String KREIRAJ_TABLICU =
            "CREATE TABLE " + Auto.IME_TABLICE + " ( " +
                    Auto._ID + " INTEGER PRIMARY KEY, " +
                    Auto.STUPAC_MARKA + " TEXT, " +
                    Auto.STUPAC_MODEL + " TEXT ); ";
    public static final String BRISI_TABLICU =
            "DROP TABLE " + Auto.IME_TABLICE + "; ";

    public AutoHelper(Context context) {
        super(context, IME_BAZE, null, VERZIJA_BAZE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // AKO NE POSTOJI file sada će biti kreiran
        db.execSQL(KREIRAJ_TABLICU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Konstanta verzije je podignuta izvrši brisanje pa ponovo kreiraj
        db.execSQL(BRISI_TABLICU);
        db.execSQL(KREIRAJ_TABLICU);
    }
}