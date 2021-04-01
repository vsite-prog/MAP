package hr.vsite.map.predavanje6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DrugaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga);
        // Čitanje podataka iz intenta
        String poruka = getIntent().getStringExtra("poruka");
        TextView tv = findViewById(R.id.tv_poruka);
        tv.setText(poruka);

        ((Button)findViewById(R.id.dugme)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = findViewById(R.id.et_unos);
                String unos = et.getText().toString();
                // Ovaj intent ne otvara ništa, već će samo spremiti podatke
                Intent data = getIntent().putExtra("unos", unos);
                // OK status i pošalji podatke
                setResult(Activity.RESULT_OK, data);
                finish(); // Vrati se nazad
            }
        });
    }
}