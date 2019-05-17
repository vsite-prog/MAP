package com.example.predavanje11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.predavanje11.AutoContract.Auto;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSpremi(View v) {
        // Spremaj u bazu
        // Pročitaj unos
        EditText et_marka = findViewById(R.id.et_marka);
        EditText et_tip = findViewById(R.id.et_tip);
        String marka = et_marka.getText().toString();
        String model = et_tip.getText().toString();
        // Koristimo Content values
        ContentValues values = new ContentValues();
        values.put(Auto.STUPAC_MARKA, marka);
        values.put(Auto.STUPAC_MODEL, model);

        try {
            AutoHelper helper = new AutoHelper(this);
            // Otvori za pisanje
            SQLiteDatabase baza = helper.getWritableDatabase();
            // Sada ide insert
            long id = baza.insert(Auto.IME_TABLICE, "", values);
            // Da li je uspjelo
            String poruka = id != -1 ? "Novi red unesen" : "Nije uspjelo!";
            // Javi korisniku
            Toast.makeText(this, poruka, Toast.LENGTH_LONG).show();
        } catch (SQLiteException ex) {
            ex.printStackTrace(); // Logcat
        }
    }

        public void onCitaj(View v){
            // Čitaj iz baze
            try {
                AutoHelper helper = new AutoHelper(this);
                // Otvori za pisanje
                SQLiteDatabase baza = helper.getReadableDatabase();
                // SElektirani stupci
                String[] stupci = {
                        Auto.STUPAC_MARKA,
                        Auto.STUPAC_MODEL
                };
                Cursor cursor = baza.query(
                        Auto.IME_TABLICE,
                        stupci,
                        null,
                        null,
                        null,
                        null,
                        null);
                // String builder kako bi ovo radilo brže
                StringBuilder builder = new StringBuilder();
                // sada bi trbalo i nešto pročitati iz kursora
                // Dohvati indekse stupaca iz kursora po imenima
                int ix_marka = cursor.getColumnIndexOrThrow(Auto.STUPAC_MARKA);
                int ix_model = cursor.getColumnIndexOrThrow(Auto.STUPAC_MODEL);
                while(cursor.moveToNext()){ // Idi jedan po jedan
                    builder.append(cursor.getString(ix_marka));
                    builder.append("  ");
                    builder.append(cursor.getString(ix_model));
                    builder.append("\n"); // Jedan auto spod drugog
                }
                // Idemo upisati u labelu
                TextView tv_auti = findViewById(R.id.tv_auti);
                tv_auti.setText(builder.toString());

            }catch (SQLiteException ex){
                ex.printStackTrace(); // Logcat
            }

        }
    }
