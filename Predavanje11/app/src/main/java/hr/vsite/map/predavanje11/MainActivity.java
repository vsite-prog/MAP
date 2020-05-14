package hr.vsite.map.predavanje11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.vsite.map.predavanje11.VirusiUgovor.Virus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void naSpremi(View v){
        // Htjeli bi spremiti podatke u bazu
        // Prvo ćemo ih pročitati
        EditText et_virus = findViewById(R.id.et_virus);
        EditText et_broj = findViewById(R.id.et_broj);
        String virus = et_virus.getText().toString();
        int broj = Integer.parseInt( et_broj.getText().toString());

        VirusiHelper helper = new VirusiHelper(this);

        try {
            // otvori bazu za pisanje
            SQLiteDatabase baza = helper.getWritableDatabase();
            // Android ima pomoćnu klasu u kojoj ćemo navesti nazive kolona i njihove vrijednosti
            ContentValues values = new ContentValues();
            values.put(Virus.STUPAC_NAZIV, virus);
            values.put(Virus.STUPAC_BROJ, broj);
            // Sada možemo upisati i podatke, trebao bi kao rezultat vratiti novi ID
            long id = baza.insert(Virus.IME_TABLICE, null, values);
            // Ako je id -1, nema unosa
            String poruka = id != -1 ? "Unesen novi red s iD-om: " + id : "Nije uspio unos!";
            Toast.makeText(this, poruka, Toast.LENGTH_LONG).show();
        } catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public void naCitaj(View v) {
        // Nekad je zgodno bazu držati otvoreno
        VirusiHelper helper = new VirusiHelper(this);
        String[] stupci = new String[]{
               Virus.STUPAC_NAZIV,
               Virus.STUPAC_BROJ
        };
        StringBuilder builder = new StringBuilder();
        try {
            // otvori bazu za čitanje
            SQLiteDatabase baza = helper.getReadableDatabase();
            // Ekvivalent SELECT * FROM Virus
            Cursor cursor =  baza.query(Virus.IME_TABLICE, stupci,null, null,null,null,null);

            // Skrolaj kroz redove
            while(cursor.moveToNext()){
                builder.append("Virus: ");
                builder.append(cursor.getString(cursor.getColumnIndex(Virus.STUPAC_NAZIV)));
                builder.append("  broj zaraženih: ");
                builder.append(cursor.getInt(cursor.getColumnIndex(Virus.STUPAC_BROJ)));
                builder.append("\n");
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        ((TextView)findViewById(R.id.tv_virusi)).setText(builder.toString());
    }
}
