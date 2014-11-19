package hr.vsite.predavanje6;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 *
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_second, container, false);

        Button b = (Button) v.findViewById(R.id.bt1);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast
                        .makeText(getActivity(), "Hello stvarno iz drugog fraga...", Toast.LENGTH_SHORT)
                        .show();
            }
        };

        b.setOnClickListener(listener);
        return v;
    }

    /* Aktivnost hvata ove evente
    public void onClick(View v) {
        Toast
                .makeText(getActivity(),"Hello iz drugog fraga...", Toast.LENGTH_SHORT)
                .show();
    }
    */


}
