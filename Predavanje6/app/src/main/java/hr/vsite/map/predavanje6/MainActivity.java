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

    // RC označava koja aktivnost se vratila
    final int DRUGA_AKTIVNOST_REQUEST_CODE = 12321;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DRUGA_AKTIVNOST_REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK){
                // Pročitaj podatke iz druge aktivnosti
                String unos = data.getStringExtra("unos");
                Toast.makeText(this, "Uneseno je: " + unos,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = findViewById(R.id.dugme);
        b1.setOnClickListener(this);
        // Aktivnost sluša i klik na 2. dugme
        Button b2 = findViewById(R.id.dugme2);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DrugaActivity.class);
        switch(v.getId()){ // Vidi tkoje kliknut
            case R.id.dugme:
                // Otvori drugu aktivnost
                // POruka iz naše aktivnosti
                intent.putExtra("poruka", getString(R.string.poruka1));
                startActivity(intent);
            case R.id.dugme2:
                // Otvori drugu aktivnost
                // POruka iz naše aktivnosti
                intent.putExtra("poruka", getString(R.string.poruka2));
                startActivityForResult(intent, DRUGA_AKTIVNOST_REQUEST_CODE);

        }
    }
}