package hr.vsite.map.predavanje8;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Definiraj novi adapter, daj mu izvor podataka i template
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.ljubimci, android.R.layout.simple_spinner_item);
        // Definiraj template koji ćeš koristi
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Poveži adapter sa spinnerom
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Javi nam što je odabrano, mora biti nešto
                String odabir = parent
                        .getItemAtPosition(position)
                        .toString();
                Toast
                        .makeText(getBaseContext(), "Odabrali ste ljubimca: " + odabir, Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //korisnik se vratio bez da je išta odabrao
                Toast
                        .makeText(getBaseContext(), "Ništa niste odabrali", Toast.LENGTH_LONG)
                        .show();

            }
        };
        //registriraj event handler objekt na padajuću listu
        spinner.setOnItemSelectedListener(listener);
    }

    //Ovo neka se izvrši na klik za otvaranje kalendara
    public void OpenCalendar(View v){
        //kreiraj novi fragment za prikaz kalendara
        DatePickerFragment fragment = new DatePickerFragment();
        //neka se i prikaže
        fragment.show(getFragmentManager(), "DatePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // kalendar klasa će nam dohvatiti trenutni datum na mobu, to će biti početna vrijednost
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // vrati novi instancu TimePicker dialoga
            return new DatePickerDialog(getActivity(),this,year, month,day);
        }
        public void onDateSet(DatePicker view, int year, int month,int day) {
            // Nakon što korisnik odabere vrijeme aktivira se ovaj callback, mjeseci idu od 0-11?
            String dateStr = day + "." + (++month) + "." + year;
            Toast
                    .makeText(getActivity(), "odabrali ste datum: " + dateStr, Toast.LENGTH_LONG)
                    .show();

        }
    }

}
