package hr.vsite.map.predavanje9;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Pronađi toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        //Sada ga postavi da bude traka
        setSupportActionBar(toolbar);

        //Idemo postaviti i kontekst meni
        //Prvo dohvati cijeli layout
        View body = findViewById(R.id.body);
        //sad ga registriraj
        registerForContextMenu(body);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Ovdje kreiraj moj meni
        MenuInflater inflater = getMenuInflater();
        //Kreiraj stvarno meni
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //Ovdje kreiraj moj meni
        MenuInflater inflater = getMenuInflater();
        //Kreiraj stvarno meni
        inflater.inflate(R.menu.menu_context,menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //što je odabrano?
        int odabir = item.getItemId();
        //A sad napravi nešto
        switch(odabir){ //Ovo i nije bilo potrebno ponavljati ali
            case R.id.action1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action3:
                Toast.makeText(this, "Juhuuuuuuu", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int zbroj;
        int odabir = item.getItemId();
        //A sad napravi nešto
        switch(odabir){ //Ovo i nije bilo potrebno ponavljati ali
            case R.id.context_action1: //Ovo je zbroji
                //Što je uneseno u ETxt
                String uneseniTxt = ((EditText)findViewById(R.id.editText)).getText().toString();
                int uneseno = Integer.parseInt(uneseniTxt);
                //Prvo moramo pročitati  dosadažnji zbroj
                SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
                //sad čitaj
                zbroj = preferences.getInt("zbroj",0);
                zbroj += uneseno;
                //Idemo nešto i upisati
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("zbroj", zbroj);
                //sada spremi u file
                editor.commit();
                return true;
            case R.id.context_action2:
                //sad čitaj
                zbroj = getPreferences(Context.MODE_PRIVATE).getInt("zbroj",0);
                Toast.makeText(this, String.valueOf(zbroj), Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
