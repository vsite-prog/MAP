package map.vsite.hr.predavanje7;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpinnerFragment extends Fragment {


    public SpinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_spinner, container, false);
        Spinner spinner = (Spinner) fragment.findViewById(R.id.spinner);
// Definiraj novi adapter, daj mu izvor podataka i template
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.planete, android.R.layout.simple_spinner_item);
// Definiraj template koji ćeš koristi
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast
                        .makeText(getActivity(), parent.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast
                        .makeText(getActivity(), "Ništa...",Toast.LENGTH_SHORT)
                        .show();
            }
        });
// Poveži adapter sa spinnerom
        spinner.setAdapter(adapter);
        return fragment;
    }

}
