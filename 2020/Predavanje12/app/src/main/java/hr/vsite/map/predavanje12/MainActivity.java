package hr.vsite.map.predavanje12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.vsite.map.predavanje12.VirusiContract.Virus;

public class MainActivity extends AppCompatActivity {
    Uri uri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Daj kreiraj uri do našeg CP-a
        Uri.Builder graditeljUri = new Uri.Builder();
        graditeljUri
                .scheme("content") // Uri na CP
                .encodedAuthority(VirusiProvider.AUTHORITY)
                .appendPath(VirusiProvider.PATH_VIRUS);
        uri = graditeljUri.build();

    }

    public void naSpremi(View v){
        EditText et_virus = findViewById(R.id.et_virus);
        EditText et_broj = findViewById(R.id.et_broj);
        String virus = et_virus.getText().toString();
        int broj = Integer.parseInt( et_broj.getText().toString());
        ContentValues values = new ContentValues();
        values.put(Virus.STUPAC_NAZIV, virus);
        values.put(Virus.STUPAC_BROJ, broj);
        ContentResolver resolver = getContentResolver();
        Uri noviRedUri = resolver.insert(uri, values );
        String poruka = noviRedUri == Uri.EMPTY ? "Nije uspio unos!": noviRedUri.toString();
        Toast.makeText(this, poruka, Toast.LENGTH_SHORT).show();
    }

    public void naCitaj(View v){
        // TRebamo se spojiti na bazu ali nećemo direktno već pomoću CP-a
        ContentResolver resolver = getContentResolver();
        String[] stupci = new String[]{
                Virus.STUPAC_NAZIV,
                Virus.STUPAC_BROJ
        };
        Cursor cursor = resolver.query(uri, stupci,null, null, null);
        if (cursor == null) return; // Sada bi trebali neku poruku, greška u čitanju i sl.
        StringBuilder builder = new StringBuilder();
        // Skrolaj kroz redove
        while(cursor.moveToNext()) {
            builder.append("Virus: ");
            builder.append(cursor.getString(cursor.getColumnIndex(Virus.STUPAC_NAZIV)));
            builder.append("  broj zaraženih: ");
            builder.append(cursor.getInt(cursor.getColumnIndex(Virus.STUPAC_BROJ)));
            builder.append("\n");
        }
        ((TextView)findViewById(R.id.tv_virusi)).setText(builder.toString());

    }
}
