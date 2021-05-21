package hr.vsite.map.predavanje12;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.strictmode.SqliteObjectLeakedViolation;

import hr.vsite.map.predavanje12.VirusContract.Virus;

public class VirusProvider extends ContentProvider {
    public static final String AUTHORITY = "hr.vsite.map.predavanje12.virusi";
    public static final String PATH = Virus.TABLE_NAME;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, PATH, 1);
    }

    public VirusProvider() {
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
        if( uriMatcher.match(uri) == 1 ) {
            VirusHelper helper = new VirusHelper(getContext());
            try (SQLiteDatabase db = helper.getWritableDatabase()){
                long id = db.insert(Virus.TABLE_NAME, null, values);
                return ContentUris.withAppendedId(uri, id);

            } catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return Uri.EMPTY; // Nešto nije dobro

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if( uriMatcher.match(uri) == 1 ) {
            VirusHelper helper = new VirusHelper(getContext());
            try{
                SQLiteDatabase db = helper.getReadableDatabase();
                Cursor c = db.query(Virus.TABLE_NAME, projection, null, null, null, null, null);
                return c;
            } catch (SQLException ex){
                ex.printStackTrace();
            }
        } else if (uriMatcher.match(uri) == UriMatcher.NO_MATCH){ // Tražimo tablčicu koja ne postoji
            throw new IllegalArgumentException("Kriva tablica!");
        }
        return null; // Ne bi ni smjeli doći do ovdje
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}