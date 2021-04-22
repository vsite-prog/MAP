package hr.vsite.map.predavanje8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    boolean first = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);
            // Definiraj novi adapter, daj mu izvor podataka i template
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.predmeti, android.R.layout.simple_spinner_item);
        // Definiraj template koji ćeš koristi
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Poveži adapter sa spinnerom
        spinner.setAdapter(adapter);
        // Neka aktivnost sluša odabir item-a
        spinner.setOnItemSelectedListener(this);

    }

    public void onClick(View v){
        // Idemo dodati ovaj naš fragment
        Fragment fragment = !first ? new FirstFragment() : new SecondFragment();
        first = !first;
        FragmentManager fm = getSupportFragmentManager();
        fm
                .beginTransaction()
                // .add(R.id.frame, new FirstFragment())
                .replace(R.id.frame, fragment)
                .addToBackStack("frag")
                .commit();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String odabrano = parent.getItemAtPosition(position).toString(); // Dobili smo odabrani item
        Toast.makeText(this, odabrano, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Ništa", Toast.LENGTH_LONG).show();
    }
}