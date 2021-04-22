package hr.vsite.map.predavanje8;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class FirstFragment extends Fragment {


    // Prazan konstruktor
    public FirstFragment() {
        // Required empty public constructor
    }



    // Nastanak fragmenta
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // Kreiranje UI-a koji se šalje aktivnisti
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        Button b = v.findViewById(R.id.dugme_fragment);
        b.setOnClickListener(vi -> Toast.makeText(getActivity(), R.string.poruka_fragment2, Toast.LENGTH_LONG).show());


        return v;
    }
}