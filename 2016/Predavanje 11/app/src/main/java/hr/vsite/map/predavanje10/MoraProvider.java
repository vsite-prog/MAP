package hr.vsite.map.predavanje10;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import hr.vsite.map.predavanje10.MoraContract.Mora;

public class MoraProvider extends ContentProvider {
    public static final String AUTHORITY = "hr.vsite.map.mora_provider";
    public static final String PATH = "mora";

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {  uriMatcher.addURI(AUTHORITY,PATH,1);} //pomaže nam da prepoznmo što se traži u URIu

    public MoraProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if(uriMatcher.match(uri) != 1) { //imamo samo jednog i on nije selektiran
            throw new IllegalArgumentException("Krivi Uri, nije tablica mora!");
        }
        //Sve ok traže se mora
        MoraHelper helper = new MoraHelper(getContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        //napravi upit prema bazi prema podacima iz upita prema Content provideru
        Cursor c = db.query(
                Mora.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
