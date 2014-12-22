package hr.vsite.predavanje10;

import android.provider.BaseColumns;

/**
 * Created by IBritvic on 15.12.2014..
 */
public final class LaptopContract {
    public LaptopContract() {
    }
    public static  String DATABASE_NAME = "laptopi";
    public static int DATABASE_VERSION = 1;


    public static abstract class Laptop implements BaseColumns {
        public static final String TABLE_NAME = "laptop";
        public static final String COLUMN_NAZIV = "naziv";
        public static final String COLUMN_TIP = "tip";
    }
}
