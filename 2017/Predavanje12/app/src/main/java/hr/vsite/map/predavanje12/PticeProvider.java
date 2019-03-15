package hr.vsite.map.predavanje12;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

public class PticeProvider extends ContentProvider {
    public static String AUTHORITY = "hr.vsite.map.predavanje12.ptice";
    public static String PATH = "ptice";
    static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    //Ukoliko je ispravan URI  vrati 1 inače NO_MATCH
    static {
        uriMatcher.addURI(AUTHORITY,PATH,1);
    }

    public PticeProvider() {
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
        Cursor cursor = null;
        //Prva stvar treba provjeriti je li se traži prava tablica
        if (uriMatcher.match(uri) == UriMatcher.NO_MATCH){
            throw new IllegalArgumentException("Uri za content provider nije dobar");
        } else if (uriMatcher.match(uri) == 1){
            //Tražimo ptice i idemo ih vratiti
            PticeHelper helper = new PticeHelper(getContext());
            SQLiteDatabase db = null;
            try{
                db = helper.getReadableDatabase();
                cursor = db.query(PticeContract.Ptice.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
            } catch (SQLiteException ex){
                ex.printStackTrace();
            } finally {
                db.close();
            }        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
