package hr.vsite.map.predavanje5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Dohvati button
        Button b1 = (Button)findViewById(R.id.b1);
        // Kreiraj anonimni objekt klase generirane iz sučelja
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast
                        .makeText(getBaseContext(), R.string.toast1, Toast.LENGTH_LONG)
                        .show();

            }
        };
        // Zadnji korak poveži dugme i slušač
        b1.setOnClickListener(listener);
        // Idemo sad drugo dugme povezati s aktivnosti
        Button b2 = (Button)findViewById(R.id.b2);
        b2.setOnClickListener(this);

        Button b3 = (Button)findViewById(R.id.b3);
        b3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // Drugi i treći button će ovo pozvati
        if(v.getId() == R.id.b2){
            EditText et = findViewById(R.id.et1);
            String txt = et
                    .getText()
                    .toString();
            Toast
                    .makeText(getBaseContext(), txt, Toast.LENGTH_LONG)
                    .show();
        } else {
            // SAd bi stvarno htio ići u drugu aktivnost
            Intent intent = new Intent(this, SecondActivity.class);
            // da bi startali moramo poslati taj intent
            startActivity(intent);
        }

    }
}
