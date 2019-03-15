package hr.vsite.map.predavanje11;

import android.provider.BaseColumns;

/**
 * Created by ivan on 15-May-17.
 */

public final class PticeContract {
    private PticeContract(){} //Ne može se instancirati objekt iz klase

    //Ovo je klasa koja opisuje sve nazive u tablici ptice
    public static abstract class Ptice implements BaseColumns{
        public final static String TABLE_NAME = "Ptice";
        public final static String COL_IME = "ime";
        public final static String COL_VRSTA = "vrsta";
    }
}
