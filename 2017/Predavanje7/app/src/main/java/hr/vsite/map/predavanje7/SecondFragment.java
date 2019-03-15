package hr.vsite.map.predavanje7;


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
        View view =  inflater.inflate(R.layout.fragment_second, container, false);

        //Unutra fragmenta pronađi button
        Button b = (Button)view.findViewById(R.id.button2);
        //registrirati listener na click
        Button.OnClickListener listener = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast
                        .makeText(getActivity(), R.string.toastText, Toast.LENGTH_SHORT)
                        .show();
            }
        };
        b.setOnClickListener(listener);
        return view;
    }

}
