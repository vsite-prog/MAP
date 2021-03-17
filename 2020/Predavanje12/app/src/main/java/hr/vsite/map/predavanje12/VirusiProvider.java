package hr.vsite.map.predavanje12;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import hr.vsite.map.predavanje12.VirusiContract.Virus;

public class VirusiProvider extends ContentProvider {
    public  static final String AUTHORITY = "hr.vsite.map.predavanje12.provider";
    public  static final String PATH_VIRUS = "virus";
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static final int VIRUS_CODE = 13;
    static {
        uriMatcher.addURI(AUTHORITY, PATH_VIRUS, VIRUS_CODE);
    }

    public VirusiProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // Ova metoda vraća nekakav file, ako to CP radi
        throw new IllegalArgumentException("Ne vraćamo datoteke!");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        VirusiHelper helper = new VirusiHelper(getContext());
        try {
            // otvori bazu za pisanje
            SQLiteDatabase baza = helper.getWritableDatabase();
            // Android ima pomoćnu klasu u kojoj ćemo navesti nazive kolona i njihove vrijednosti
            // Sada možemo upisati i podatke, trebao bi kao rezultat vratiti novi ID
            long id = baza.insert(Virus.IME_TABLICE, null, values);

            return ContentUris.withAppendedId(uri, id);

        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return  Uri.EMPTY; // Uri na ništa
    }

    @Override
    public boolean onCreate() {
        // Inicijalizacija nekih stvari u prvovideru
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // Idemo čitati iz SQL baze prema zahtjevu i vratiti podatke u kursoru
        switch (uriMatcher.match(uri)) { // Koju tablicu u CP-u traže
            case VIRUS_CODE:
                // Spoji se na bazu
                VirusiHelper helper = new VirusiHelper(getContext());

                try {
                    SQLiteDatabase baza = helper.getReadableDatabase();
                    // Ekvivalent SELECT * FROM Virus
                    Cursor cursor =  baza.query(Virus.IME_TABLICE, projection,null, null,null,null,null);
                    return cursor;
                } catch (SQLException ex){
                    ex.printStackTrace();
                }
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Krivi URI za Content provider!");
        }
        return null; // Nisam uspio pročitati podatke
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
