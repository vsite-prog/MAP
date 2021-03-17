package hr.vsite.map.predavanje6;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    // Konstanta koja objašnjava tko otvara drugu aktivnost
    final static int DUGME2_REQUEST_CODE = 77;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DUGME2_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                Toast
                        .makeText(this, "Kliknuli ste OK", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.dugme1);
        Button b2 = findViewById(R.id.dugme2);
        // A gdje registrirni slušač
        b.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent namjera = new Intent(this, SecondActivity.class);
        if (v.getId() == R.id.dugme1){
            // Stavi neki tekst u Intent i pošalji ga u drugu aktivnost
            namjera.putExtra("nesto", "Otvara te prvo dugme!");
            // Sada pokreni aktivnost, šaljemo signal Androidu
            startActivity(namjera);
        } else if (v.getId() == R.id.dugme2){
            // Pokreni aktivnost i očekuj nekakav povratak
            namjera.putExtra("nesto", "Otvara te drugo dugme i čeka rezultat!");
            startActivityForResult(namjera, DUGME2_REQUEST_CODE );
        }

    }



}
