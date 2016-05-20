package hr.vsite.map.predavanje10;

import android.provider.BaseColumns;

/**
 * Created by ivan on 03-May-16.
 * Ova klasa samo definira nazive kako ih ne bi hardkodirali
 */
public final class MoraContract {
    MoraContract(){}
    public static abstract class Mora implements BaseColumns{
        public static final String TABLE_NAME = "mora";
        public static final String COL_NAME_SIFRA = "sifra";
        public static final String COL_NAME_NAZIV = "naziv";
    }
}
