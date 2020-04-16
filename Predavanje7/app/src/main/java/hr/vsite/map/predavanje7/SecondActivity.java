package hr.vsite.map.predavanje7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Pročitaj što je došlo iz prve aktivnosti
        ((TextView)findViewById(R.id.tv_druga_aktivnost)).setText(getIntent().getStringExtra("poruka"));

        findViewById(R.id.dugme2).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(); // On je prazan, ne otvara nikakvu aktivnost
                intent.putExtra("vrati", "Pozdrav iz druge aktivnosti!"); // nego sprema podatke
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
