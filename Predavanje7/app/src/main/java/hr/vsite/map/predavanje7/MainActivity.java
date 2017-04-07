package hr.vsite.map.predavanje7;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    static boolean FRAG_FIRST = false; //ovo nam je flag koji pokazuje je li se vidi fragment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v){
        //Sakrij ili prikaži fragment
        FragmentManager manager = getFragmentManager();
        //Sada prikaži ili sakrij naš fragment s kodom
        Fragment fragment;
        if(!FRAG_FIRST){
            fragment = new SecondFragment();
        } else {
           fragment = new FirstFragment();
        }
        //primijeni sad i varijablu
        FRAG_FIRST = !FRAG_FIRST;
        //Sad stvarno zamijeni fragment, ako nema ništa mogli smo ga u dodati
        manager
                .beginTransaction()
                .replace(R.id.fragContainer, fragment)
                .commit();

    }
}
