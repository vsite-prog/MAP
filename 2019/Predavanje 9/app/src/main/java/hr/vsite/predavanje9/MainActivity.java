package hr.vsite.predavanje9;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // registriraj kontekstualni meni kako bi se prikazao
        registerForContextMenu(findViewById(R.id.layout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Komponenta koja će kreirati meni iz XML-a
        MenuInflater inflater = getMenuInflater();
        // pročitaj xml i upiši ga na akcijsku traku
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // Trebali bi dohvatiti id view-a i vidjeti kojij meni treba ići ali kako ima samo jedan..
        // Komponenta koja će kreirati meni iz XML-a
        MenuInflater inflater = getMenuInflater();
        // pročitaj xml i upiši ga na akcijsku traku
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.ContextItem1:
                Toast
                        .makeText(this, "Odabrana prva opcija!", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.ContextItem2:
                Toast
                        .makeText(this, "Odabrana druga opcija!", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.ContextItem3:
                Toast
                        .makeText(this, "Odabrana treća opcija!", Toast.LENGTH_SHORT)
                        .show();
                return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.MenuItem1:
                SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
                String txt = preferences.getString("spremi", "Ništa");
                Toast
                        .makeText(this, txt, Toast.LENGTH_LONG)
                        .show();
                return true;
            case R.id.MenuItem2:
                Toast
                        .makeText(this, "Odabrana druga opcija!", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.MenuItem3:
                Toast
                        .makeText(this, "Odabrana treća opcija!", Toast.LENGTH_SHORT)
                        .show();
                return true;
        }
        return false;
    }

    public void onClick(View v){
        EditText et = findViewById(R.id.editText);
        String uneseno = et.getText().toString();
        // Idemo spremiti u SP, samo mi možemo koristiti
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String txt = preferences.getString("spremi", "Ništa");
        uneseno += "/n" + txt;
        preferences
                .edit() // Idemo nešto mijenati u file
                .putString("spremi", uneseno)
                .apply(); // sada se stvarno sprema, možemo i sa commit

    }
}
