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

import hr.vsite.map.predavanje11.VirusContract.Virus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        // Htjeli bi pisati u našu aplikaciju
        // Otvori helper
        VirusHelper pomagac = new VirusHelper(this);
        if (v.getId() == R.id.bt_spremi){
            try {
                // Otvori bazu za pisanje
                SQLiteDatabase baza = pomagac.getWritableDatabase();
                // Pročitaj ono što je korisnik unio
                EditText et_naziv = findViewById(R.id.et_naziv);
                String naziv = et_naziv.getText().toString();
                EditText et_broj = findViewById(R.id.et_broj);
                int broj = Integer.parseInt(et_broj.getText().toString());
                // Pripremi podatke za pisanje
                // Parove naziv kolone : vrijednost
                ContentValues vrijednosti = new ContentValues();
                vrijednosti.put(Virus.COLUMN_NAME, naziv);
                vrijednosti.put(Virus.COLUMN_ZARAZENI, broj);

                long id = baza.insert(Virus.TABLE_NAME, null, vrijednosti);
                String poruka;
                if (id == -1) // nije vraćen ID
                    poruka = " Nije uspjelo upisivanje";
                else
                    poruka = "Unesen novi virus sa ID-om: " + id;
                Toast.makeText(this, poruka, Toast.LENGTH_LONG).show();
            } catch (SQLException ex){
                ex.printStackTrace(); // Pokaži u LogCatu
                Toast.makeText(this,ex.getMessage(), Toast.LENGTH_LONG ).show();
            }
        } else if (v.getId() == R.id.bt_citaj){
            // Otvori bazu za čitanje, i ovde bi trebao try catch
            SQLiteDatabase baza = pomagac.getReadableDatabase();

            String[] kolone = new String[]{ // Obično polje tekstova
                    Virus.COLUMN_NAME,
                    Virus.COLUMN_ZARAZENI
            };
            Cursor kursor = baza.query(
                    Virus.TABLE_NAME,
                    kolone,
                    null,
                    null,
                    null,
                    null,
                    null);
            StringBuilder graditelj = new StringBuilder(); // Ubrzati ćemo stvari
            while(kursor.moveToNext()){
                // Idi na rekord po rekord
                graditelj.append("\nVirus: ");
                graditelj.append(kursor.getString(kursor.getColumnIndex(Virus.COLUMN_NAME))); //pročitaj ime
                graditelj.append(" broj zaraženih: ");
                graditelj.append(kursor.getInt(kursor.getColumnIndex(Virus.COLUMN_ZARAZENI)));
            }
            TextView tv_baza = findViewById(R.id.tv_baza);
            tv_baza.setText(graditelj.toString());
        }



    }
}