package hr.vsite.map.predavanje9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.view.MenuInflater;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerForContextMenu(findViewById(R.id.main_layout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Dobija prazni meni od aktivnosti i kaže napuni ga definicijom iz XML-a
        getMenuInflater()
                .inflate(R.menu.main_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Zanima nas ID, i po njemu vidimo što je kliknuto
        switch(item.getItemId()){
            case R.id.prvi_izbor:
                // Idemo nešto spremiti u SP
                SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE); // samo ja mogu čitati i pisati iz njega
                SharedPreferences.Editor editor = preferences.edit(); // Daj mi komponentu koja može pisati u SP
                editor.putString("spremljeno", getString(R.string.context_izbor)); // Spremi bezvezni string u SP
                editor.commit(); // Hajde ovo zapeci u file
                Toast.makeText(this, getString(R.string.prvi_izbor), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drugi_izbor:
                Toast.makeText(this, getString(R.string.drugi_izbor), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.treci_izbor:
                Toast.makeText(this, getString(R.string.treci_izbor), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.context_izbor:
                String procitano = getPreferences(Context.MODE_PRIVATE).getString("spremljeno", "nema ništa");
                // Idemo ovdje nešto čitati iz SP
                Toast.makeText(this, "Pročitano iz shared pref. " + procitano , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.prvi_izbor:
                Toast.makeText(this, getString(R.string.prvi_izbor), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.drugi_izbor:
                Toast.makeText(this, getString(R.string.drugi_izbor), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.treci_izbor:
                Toast.makeText(this, getString(R.string.treci_izbor), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

