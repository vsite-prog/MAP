package com.example.predavanje12;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.widget.Toast;

import com.example.predavanje12.AutoContract.Auto;

public class AutoProvider extends ContentProvider {
    final static String AUTHORITY = "com.example.predavanje12.provider";
    final static String PATH_AUTO = "Auto";
    final static int AUTO_CODE = 11;

    // Klasa sa metodama uparivanja URI-ja
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, PATH_AUTO, AUTO_CODE);
    }
    public AutoProvider() {
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
        // Sada CP unosi podatke u bazu
        Uri povratniUri = Uri.EMPTY;
        switch (uriMatcher.match(uri)) {
            case AUTO_CODE:
                try {
                    AutoHelper helper = new AutoHelper(getContext());
                    // Otvori za pisanje
                    SQLiteDatabase baza = helper.getWritableDatabase();
                    // Sada ide insert
                    long id = baza.insert(Auto.IME_TABLICE, "", values);
                    povratniUri = ContentUris.withAppendedId(uri, id);
                } catch (SQLiteException ex) {
                    ex.printStackTrace(); // Logcat
                }
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Kriv URI");
        }
        return povratniUri;
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
        // Netko traži podatke kod ovog provider-a
        switch (uriMatcher.match(uri)) {
            case AUTO_CODE:
                    try {
                        AutoHelper helper = new AutoHelper(getContext());
                        // Otvori za pisanje
                        SQLiteDatabase baza = helper.getReadableDatabase();
                        // SElektirani stupci
                         cursor = baza.query(
                                Auto.IME_TABLICE,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
                    }catch (SQLiteException ex){
                        ex.printStackTrace(); // Logcat
                    }
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Kriv URI");
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
