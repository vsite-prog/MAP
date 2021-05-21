package hr.vsite.map.predavanje12;

import android.provider.BaseColumns;

public class VirusContract {

    // Prazni constructor, tako da netko slučajno ne kreira objekt iz klase
    private VirusContract(){}
    public final static String DATABASE_NAME = "Virusi2.db";
    public static abstract class Virus implements BaseColumns {
        // NAzivi svih identifikatora, sve na istome mjesto
        public static final String TABLE_NAME   = "Virus";
        public static String COLUMN_NAME   = "Naziv";
        public static String COLUMN_ZARAZENI   = "Broj"; // Broj zaraženih

        public static String CREATE_TABLE_SCRIPT =
                " CREATE TABLE " + TABLE_NAME + " ( " +
                        _ID + " INTEGER PRIMARY KEY,"  +
                        COLUMN_NAME + " TEXT NOT NULL, " +
                        COLUMN_ZARAZENI + " INTEGER " +
                    ")";

    }
}
