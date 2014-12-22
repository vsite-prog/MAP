package hr.vsite.predavanje10;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * Created by IBritvic on 22.12.2014..
 */
public class LaptopiProvider extends ContentProvider {
    private static final String AUTHORITY = "hr.vsite.predavanje10.content_provider";
    private static final String PATH = "laptopi";
    public static final Uri CONTENT_URI_LAPTOPI = Uri.parse("content://" + AUTHORITY + "/" + PATH) ;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static { uriMatcher.addURI(AUTHORITY, PATH, 1);   }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (uriMatcher.match(uri) != 1){
            //Krivi URI je poslan
            throw new IllegalArgumentException("Content Uri???");
        }

        return getLaptopi();

    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private Cursor getLaptopi() {
        LaptopiHelper helper = new LaptopiHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] stupci = {
                LaptopContract.Laptop.COLUMN_NAZIV,
                LaptopContract.Laptop.COLUMN_TIP
        };

        Cursor c = db.query(LaptopContract.Laptop.TABLE_NAME,
                stupci,
                null,
                null,
                null,
                null,
                null);

        return c;
    }

}
