package hr.vsite.map.predvanje8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ajde napuni padajuću listu
        Spinner spinner = findViewById(R.id.spinner);
        // Definiraj novi adapter, daj mu izvor podataka i template
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.virusi, android.R.layout.simple_spinner_item);
        // Definiraj template koji ćeš koristi
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Poveži adapter sa spinnerom
        spinner.setAdapter(adapter);

        OnItemSelectedListener listener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Dohvati odabir po poziciji
                String virus = parent
                        .getItemAtPosition(position)
                        .toString();
                Toast
                        .makeText(getBaseContext(), virus, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "Ništa nije odabrano!", Toast.LENGTH_SHORT).show();
            }
        };

        // Da bi listener slusao treba ga povezati sa Spinner-om
        spinner.setOnItemSelectedListener(listener);
    }

    public void otvori(View v){
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.show(getSupportFragmentManager(), "Odabir datuma");
    }


    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // kalendar klasa će nam dohvatiti trenutno vrijeme na mobu, to će biti početna vrijednost
            final Calendar c = Calendar.getInstance();
            int godina = c.get(Calendar.YEAR);
            int mjesec = c.get(Calendar.MONTH);
            int dan = c.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this, godina, mjesec, dan);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Mjeseci idu od 0-11
            String poruka = String.format(" Odabrali ste datm: %02d.%02d.%d!", dayOfMonth, ++month, year);
            Toast.makeText(getActivity(), poruka, Toast.LENGTH_LONG).show();
        }
    }

}
