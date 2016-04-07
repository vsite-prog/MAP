package map.vsite.hr.predavanje6;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_second, container, false);
        Button b = (Button)v.findViewById(R.id.button2);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Button 2!", Toast.LENGTH_SHORT).show();
            }
        };
        b.setOnClickListener(listener);
        return v;
    }

    /* Nažalost ne radi, idemo nešto drugačije
    public void onClick(View v){
        Toast.makeText(getActivity(), "Button 2!", Toast.LENGTH_SHORT).show();
    }
    */

}
