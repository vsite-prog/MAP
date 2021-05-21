package hr.vsite.map.predavanje12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.vsite.map.predavanje12.VirusContract.Virus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        Uri.Builder builder = new Uri.Builder(); // Kreirati ćemo URI objekt korak po korak
        builder
                .scheme("content")
                .authority(VirusProvider.AUTHORITY)
                .path(VirusProvider.PATH);
        Uri uri = builder.build(); // content://hr.vsite.map.predavanje12.virusi/virus
        // Čitaj ili piši iz CPa
        if(v.getId() == R.id.bt_citaj){
            String[] kolone = {
                    Virus.COLUMN_NAME,
                    Virus.COLUMN_ZARAZENI
            } ;
            Cursor cursor = getContentResolver().query(uri, kolone, null, null, null);
            String res = "";
            while(cursor.moveToNext()){
                res += "\n" + cursor.getString(cursor.getColumnIndex(Virus.COLUMN_NAME)) + " "  + cursor.getInt(1);
            }
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        } else if(v.getId() == R.id.bt_spremi){
            EditText et_virus = findViewById(R.id.et_naziv);
            EditText et_broj = findViewById(R.id.et_broj);
            String virus = et_virus.getText().toString();
            int broj = Integer.parseInt(et_broj.getText().toString());
            ContentValues values = new ContentValues();
            values.put(Virus.COLUMN_NAME, virus);
            values.put(Virus.COLUMN_ZARAZENI, broj);
            Uri newRowUri = getContentResolver().insert(uri, values);
            String res = newRowUri == Uri.EMPTY ? " NIje ništa uneseno!" : newRowUri.toString();
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
        }
    }
}