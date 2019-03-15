package hr.vsite.map.predavanje10;

import android.provider.BaseColumns;

/**
 * Created by ivan on 08-May-17.
 */
//Klasa za cijelu bazu
public final class PticeContract {
    //Klasa za tablicu ptice
    public static abstract class Ptica implements BaseColumns{
        public static final  String TABLE_NAME = "ptice";
        public static final  String COL_NAZIV = "naziv";
        public static final  String COL_VRSTA = "vrsta";
    }
}
