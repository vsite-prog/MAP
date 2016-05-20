package hr.vsite.map.predavanje10;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.vsite.map.predavanje10.MoraContract.Mora;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_provider:
                Intent i = new Intent(this, hr.vsite.map.predavanje10.ProviderActivity.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


        public void onSpremaj(View v){
        //Pročitaj što je korisik unio
        String sifra = ((EditText)findViewById(R.id.edit_sifra)).getText().toString();
        String naziv = ((EditText)findViewById(R.id.edit_naziv)).getText().toString();
        SQLiteDatabase db = null;
        MoraHelper helper = new MoraHelper(this);
        try {
            db = helper.getWritableDatabase(); //otvori bazu za pisanje
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(Mora.COL_NAME_SIFRA, sifra);
            values.put(Mora.COL_NAME_NAZIV, naziv);

// Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(
                    Mora.TABLE_NAME, //naziv tablice u koju ubacujemo
                    null, //što ako su vrijednosti prazne
                    values);
            String txt = newRowId != -1? Mora.TABLE_NAME + " unesen novi red!" : "Nešto nije u redu";
            Toast.makeText(this,txt,Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex){
            ex.printStackTrace();
        } finally {
            db.close(); //zatvori bazu
        }
    }

    public void onCitaj(View v){
        SQLiteDatabase db = null;
        MoraHelper helper = new MoraHelper(this);
        try {
            db = helper.getReadableDatabase();
            String[] projection = {
                    Mora._ID,
                    Mora.COL_NAME_NAZIV,
                    Mora.COL_NAME_SIFRA
            };

            Cursor c = db.query(
                    Mora.TABLE_NAME,  // The table to query
                    projection,                               // The columns to return
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );
            String mora = "";
            while(c.moveToNext()) { //idi rekord po rekord
                mora += c.getString(c.getColumnIndex(Mora.COL_NAME_SIFRA))  //dohvati vrijednost sifre za ovaj rekord
                        + " "
                        + c.getString(c.getColumnIndex(Mora.COL_NAME_NAZIV))
                        + "\n";
            }
            //Upiši u text view
            ((TextView)findViewById(R.id.mora)).setText(mora);
        } catch (SQLiteException ex){
            ex.printStackTrace();
        } finally {
            db.close(); //zatvori bazu
        }
    }
}
