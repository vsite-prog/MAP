package hr.vsite.map.predavanje10;

import android.provider.BaseColumns;

public final class BazaContract {
    private BazaContract(){} // Konstruktor je privatan, nitko ne može kreirati objekt iz klase
    public static abstract class Tablica1 implements BaseColumns { //Interface potreban za ID stupce, itd
        public static final String TABLE_NAME = "PRVA_TABLICA"; // Table name konstanta
        public static final String COLUMN1_NAME = "PRVA_KOLONA"; // I dalje imena za sve kolone
    }
}
