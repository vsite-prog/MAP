package hr.vsite.map.predavanje11;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import hr.vsite.map.predavanje11.VirusiUgovor.Virus;


import androidx.annotation.Nullable;

// Zovemo shema ili helper objekt
public class VirusiHelper extends SQLiteOpenHelper {
    static final int VERZIJA_BAZE = 1;
    static final String SKRIPT_KREIRAJ_VIRUS_TABLICU =
            "CREATE TABLE " + Virus.IME_TABLICE + " ( "  +
                    Virus._ID + " INTEGER PRIMARY KEY, " +
                    Virus.STUPAC_NAZIV + " TEXT, " +
                    Virus.STUPAC_BROJ + " TEXT " +
            ");";
    static final String BRIŠI_VIRUS_TABLICU =
            "DROP TABLE " + Virus.IME_TABLICE + ";";


    public VirusiHelper(Context context) {
        super(context, VirusiUgovor.IME_BAZE, null, VERZIJA_BAZE);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Ako baza ne postoji stvori je
        db.execSQL(SKRIPT_KREIRAJ_VIRUS_TABLICU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // sql kod za promjene na novu verziju, ima i downgrade
        // Izbriši pa ponovo kreiraj
        db.execSQL(BRIŠI_VIRUS_TABLICU);
        db.execSQL(SKRIPT_KREIRAJ_VIRUS_TABLICU);
    }
}
