package map.vsite.hr.predavanje7;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    boolean spinnerVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){ //prvo otvaranje dodaj nekakav fragment
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame1, new SpinnerFragment())
                    .commit();
        }

        ((Button)findViewById(R.id.button)).setOnClickListener(this);
    }

    /**
     * Svaki put promijeni fragment koji se vidi
     * @param v
     */
    public void onClick(View v){
        Fragment f;
        if(spinnerVisible){
            f = new SecondFragment();
        } else {
            f = new SpinnerFragment();
        }
        spinnerVisible = !spinnerVisible;
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.frame1, f)
                .commit();
    }

    /**
     * A simple {@link Fragment} subclass.
     */
    public static class SecondFragment extends Fragment {

        public SecondFragment() {
            // Required empty public constructor
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_second, container, false);
        }

    }

}
