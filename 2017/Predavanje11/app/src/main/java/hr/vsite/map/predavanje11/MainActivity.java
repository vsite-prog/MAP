package hr.vsite.map.predavanje11;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hr.vsite.map.predavanje11.PticeContract.Ptice;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  onSpremi(View v){
        //Pročitaj što je uneseno i to spremi
        String ime = ((EditText)findViewById(R.id.et_ime)).getText().toString();
        String vrsta = ((EditText)findViewById(R.id.et_vrsta)).getText().toString();
        //Pripremi podatke za unos
        ContentValues values = new ContentValues();
        values.put(Ptice.COL_IME, ime);
        values.put(Ptice.COL_VRSTA, vrsta);
        //Kreiraj helper
        PticeHelper helper = new PticeHelper(this);
        SQLiteDatabase db = null;
        try{
            //Otvori bazu za pisanje
            db = helper.getWritableDatabase();
            long id = db.insert(Ptice.TABLE_NAME,null,values); //ako je sve ok onda ćemo dobiti novi id
            String message = id != -1 ? "Unesen novi red":"Nije uspio unos";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        } catch (SQLiteException ex){
            //Puklo je
            ex.printStackTrace();
        } finally {
            db.close(); //Ne treba nam baza
        }
    }

    public void  onCitaj(View v){
        PticeHelper helper = new PticeHelper(this);
        SQLiteDatabase db = null;
        try {
            String[] kolone = { //lista kolona koju će vratiti
                    Ptice._ID,
                    Ptice.COL_IME,
                    Ptice.COL_VRSTA
            };
            //Otvoriti bazu za čitanje
            db = helper.getReadableDatabase();
            Cursor cursor = db.query(
                    Ptice.TABLE_NAME,  //Naziv tablice
                    kolone,
                    null, //Where
                    null, //where drugi dio
                    null, //group by
                    null, //having
                    null //order bgy
            );
            StringBuilder sb = new StringBuilder(); //ubrzavamo
            int brKoloneId = cursor.getColumnIndex(Ptice._ID); //dohvati broj kolone u kursoru
            //Imamo kursor
            while (cursor.moveToNext()){ //Idemo red po red čitati
                sb.append("ID: ");
                sb.append(cursor.getInt(brKoloneId)); //pročitaj ID
                sb.append(" Naziv: ");
                sb.append(cursor.getString(1)); //pročitaj Naziv
                sb.append(" Vrsta: ");
                sb.append(cursor.getString(2)); //pročitaj ID
                sb.append("\n");
            }

            Toast.makeText(this, sb.toString(), Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex){
            //Puklo je
            ex.printStackTrace();
        } finally {
            db.close(); //Ne treba nam baza
        }
    }
}
