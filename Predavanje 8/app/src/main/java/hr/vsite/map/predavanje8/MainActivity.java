package hr.vsite.map.predavanje8;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Idemo postaviti spinner
        Spinner spinner =  findViewById(R.id.predmeti);
        // Definiraj novi adapter, daj mu izvor podataka i template
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.svi_predmeti, android.R.layout.simple_spinner_item);
        // Definiraj template koji ćeš koristi
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Poveži adapter sa spinnerom
        spinner.setAdapter(adapter);

        // Trebalo bi reagirati i kad se nešto desi
        // Treba mi objekt koji će reagirati na selekciju
        Spinner.OnItemSelectedListener listener = new  Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String predmet = parent
                        .getItemAtPosition(position) // Ovo smo mogli i sa selected item
                        .toString();
                TextView rezultat = findViewById(R.id.result);
                rezultat.setText(predmet);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getBaseContext(), "Ništa nije selektirsano!", Toast.LENGTH_SHORT).show();
            }
        };

        // Na kraju ga moraš registrirsti
        spinner.setOnItemSelectedListener(listener);
    }

    public void odaberiDatum(View v){
        // Izbaci Datepicker
        DatumOdabirFragment fragment = new DatumOdabirFragment();
        fragment.show(getFragmentManager(), "Datum");

    }

    // Treba mi novi fragment, kreirati ću unutarnju klasu
    public static class DatumOdabirFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            // Idemo upisati rezultat u TextView
            TextView rezultat = (TextView)getActivity().findViewById(R.id.result);
            rezultat.setText(String.format("Datum: %d.%d.%d.", dayOfMonth, ++month, year)); // mjesec ide od 0-11
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Dohvati trenutni dadan, mjesec, godinu
            // Koristi Java kalendar
            Calendar calendar = Calendar.getInstance();
            int godina, mjesec, dan;
            godina = calendar.get(Calendar.YEAR);
            mjesec = calendar.get(Calendar.MONTH);
            dan = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(),this, godina, mjesec, dan );
        }
    }
}
