package hr.vsite.map.predavanje7;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrviFragment extends Fragment {


    public PrviFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_prvi, container, false);

        Button b = v.findViewById(R.id.bt1); // Ova metoda ne radi samo na aktivnosti
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = v.findViewById(R.id.textView);
                Toast // U fragmentu možemo lako doći do reference od aktivnosti
                        .makeText(getActivity(), "Sada smo u fragmentu", Toast.LENGTH_LONG)
                        .show();
            }
        });
        // Moramo vratiti view
        return v;

    }

}
