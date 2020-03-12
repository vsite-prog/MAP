import android.provider.BaseColumns;

public final class StudentiUgovor {
    // Ovo je konstruktor da nitko ne može instancirati objekt iz ove klase
    StudentiUgovor(){

    }
    // Ona služi definiranju imena tablica/stupaca
    public final static class StudentiTablica implements BaseColumns {
        public final static String NAZIV_TABLiCE = "Student";
        public final static String STUPAC_IME = "Ime";
        public final static String STUPAC_OIB = "OIB"; //..

    }
}
