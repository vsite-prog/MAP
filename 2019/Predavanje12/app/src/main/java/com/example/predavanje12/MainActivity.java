package com.example.predavanje12;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.predavanje12.AutoContract.Auto;
import com.example.predavanje12.AutoProvider;

import static com.example.predavanje12.AutoProvider.AUTHORITY;
import static com.example.predavanje12.AutoProvider.PATH_AUTO;

public class MainActivity extends AppCompatActivity {
    // Ovdje je veza prema CP-u, bilo bi ljepše u buiulder
    final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + PATH_AUTO );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // Sad radimo preko CP-a
    public void onCitaj(View v){
        String[] stupci = {
                Auto.STUPAC_MARKA,
                Auto.STUPAC_MODEL
        };
        Cursor cursor = getContentResolver()
                .query(CONTENT_URI, stupci, null, null, null);
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
    }
    public void onSpremi(View v){
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
        Uri uri = getContentResolver().insert(CONTENT_URI, values);
        String poruka = uri != Uri.EMPTY ? uri.toString() : "Došlo je do greške";
        Toast.makeText(this, poruka, Toast.LENGTH_LONG).show();

    }
}
