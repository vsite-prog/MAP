package hr.vsite.map.predavanje7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        Aktivnost vidi sve fragmente koji se nalaze na njoj
        Ovo nije lijepo, sve se radi o fragmentu pa zašto onda nije unutra
        Button b = findViewById(R.id.bt1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = findViewById(R.id.textView);
                Toast
                        .makeText(getBaseContext(), tv.getText().toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });
        */
        // Hoćemo na klik dugmeta dodavati ovaj naš fragment
        Button b = findViewById(R.id.button_main);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Promijeni fragmente
                DrugiFragment frag = new DrugiFragment(); // Kako ćemo ga dodati
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container, frag)
                        .addToBackStack(null)  // poništi ovu transakcijju sa back
                        .commit();
            }
        });

        // Hoćemo na klik dugmeta dodavati ovaj naš fragment
        b = findViewById(R.id.button_replace);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Promijeni fragmente
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new PrviFragment())
                        .addToBackStack(null)  // poništi ovu transakcijju sa back
                        .commit();
            }
        });
    }
}
