package hr.vsite.predavanje7;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

// Definiraj novi adapter, daj mu izvor podataka i template
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gradovi, android.R.layout.simple_spinner_item);
// Definiraj template koji ćeš koristi
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Poveži adapter sa spinnerom
        spinner.setAdapter(adapter);



    }

    public void promijeniFragment(View v) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new FragmentDva())
                .addToBackStack(null) //Zapamti fragment u kom smo bili
                .commit();

    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
