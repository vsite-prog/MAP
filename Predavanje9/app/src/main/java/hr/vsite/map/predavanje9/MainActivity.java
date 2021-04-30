package hr.vsite.map.predavanje9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ajde poveži kontekst meni sa layoutom
        registerForContextMenu(findViewById(R.id.layout));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflacija = getMenuInflater();
        inflacija.inflate(R.menu.menu_main, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.opcija1:
                // Toast.makeText(this, "Odabrana: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
                Toast.makeText(this, "Spremljenu SP: " + preferences.getString("zadnje", "Nema ništa"), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.opcija2:
                Toast.makeText(this, "Odabrana: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.vani:
                finish(); // Ovo obično ne stavljamo u glavnu aktivnost
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // Ovo se ne izvodi na početku aktivnosti nego kada korisnik klikne dugo na neki dio akt.
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {// Piši u zajedničke preferncije
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE); // Koristiti će samo ova aktivnost;
        // stavi file od shared p. u edit mode, i spremi za pisanje
        SharedPreferences.Editor editor = sp.edit();
        switch(item.getItemId()){
            case R.id.kopcija1:
                // Toast.makeText(this, "Odabrana: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                editor.putString("zadnje", "Odabrana: " + item.getTitle());
                // Sada spremiti za stalno , tj staviti u file
                editor.apply(); // Može i commit, ali ovo je ansinhrono
                return true;
            case R.id.kopcija2:
                // Toast.makeText(this, "Odabrana: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                editor.putString("zadnje", "Odabrana: " + item.getTitle());
                editor.commit(); // Bolje sa apply
                return true;
            case R.id.kvani:
                finish(); // Ovo obično ne stavljamo u glavnu aktivnost
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void onClick(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            /* kalendar klasa će nam dohvatiti trenutni datum na mobu, to će biti početna vrijednost */
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            // vrati novi instancu TimePicker dialoga
            return new DatePickerDialog(getActivity(), this, year, month,
                    day);
        }
        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Nakon što korisnik odabere vrijeme aktivira se ovaj callback
            TextView tv = getActivity().findViewById(R.id.textView);
            tv.setText(String.format("Dan: %d, mjesec %d , godina %d", day, ++month, year)); // Povećaj mjesec za 1
        }
    }

}