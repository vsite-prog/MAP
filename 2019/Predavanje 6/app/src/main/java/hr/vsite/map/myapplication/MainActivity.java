package hr.vsite.map.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Konstanta
    final static int MY_REQUEST_CODE = 8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Registriraj klik
        Button b1 = findViewById(R.id.bt1);
        b1.setOnClickListener(this);
        // Registriraj klik
        Button b2 = findViewById(R.id.bt2);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);

        if(v.getId() == R.id.bt1){
            // Spremi nešto za drugu aktivmost
            intent.putExtra("poruka", "Otvorilo te prvo dugme!");
            // Otvori 2. aktivnost
            startActivity(intent);
        } else {
            // Spremi nešto za drugu aktivmost
            intent.putExtra("poruka", "Otvorilo te drugo dugme!");
            // Request code je u biti samo šifra po kojoj ćemo prepoznati callback kad aktivnost
            startActivityForResult(intent, MY_REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       // Provjeri je li to naša druga aktivnost
        if(requestCode == MY_REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){ // Ako je sve bilo ok, idemo nešto napraviti
                // Gdje su podaci
                String odgovor = data.getStringExtra("odgovor");
                Toast
                        .makeText(this, odgovor, Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
