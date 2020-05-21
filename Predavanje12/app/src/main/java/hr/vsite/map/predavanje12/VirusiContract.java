package hr.vsite.map.predavanje12;

import android.provider.BaseColumns;

public final class VirusiContract {
    private VirusiContract(){} // Nema objekta iz ove klase
    public static final String IME_BAZE = "Virusi";
    public static abstract class Virus implements BaseColumns { // Ovo je interface BaseColumn koji dodaje _id stupac
        public static final String IME_TABLICE = "Virus";
        public static final String STUPAC_NAZIV = "naziv";
        public static final String STUPAC_BROJ = "broj";
    }
}
