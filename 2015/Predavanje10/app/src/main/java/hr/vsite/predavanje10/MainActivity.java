package hr.vsite.predavanje10;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.vsite.predavanje10.LaptopContract.Laptop;


public class MainActivity extends ActionBarActivity {

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onKreiraj(View v) {
        String naziv = ((EditText)findViewById(R.id.et_naziv)).getText().toString();
        String tip = ((EditText)findViewById(R.id.et_tip)).getText().toString();
        LaptopiHelper helper = new LaptopiHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase(); //kreiraj ako nema, ili daj postojeću;
        ContentValues values = new ContentValues();
        values.put(Laptop.COLUMN_NAZIV, naziv);
        values.put(Laptop.COLUMN_TIP, tip);
        try {

            long id = db.insert( Laptop.TABLE_NAME,
                                   "",
                                    values
           );
            String txt = (id != -1)?"Sve pet!":"Nije uspjelo!";
            Toast
                    .makeText(this, txt,Toast.LENGTH_LONG)
                    .show();
        }catch ( Exception ex){
            ex.printStackTrace();
        } finally {
            db.close();
        }

    }

    public void onCitaj(View v) {
        LaptopiHelper helper = new LaptopiHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String[] stupci = {
                Laptop.COLUMN_NAZIV,
                Laptop.COLUMN_TIP
        };

        Cursor c = db.query(Laptop.TABLE_NAME,
                            stupci,
                            null,
                            null,
                            null,
                            null,
                            Laptop.COLUMN_NAZIV);

        String txt = "";
        while(c.moveToNext()) {
            txt += "Naziv: " + c.getString(c.getColumnIndexOrThrow(Laptop.COLUMN_NAZIV))
                   + " Tip: " + c.getString(c.getColumnIndexOrThrow(Laptop.COLUMN_TIP)) + "\n";

        }
        TextView tv = (TextView)findViewById(R.id.tv_laptopi);
        tv.setText(txt);
    }


}
