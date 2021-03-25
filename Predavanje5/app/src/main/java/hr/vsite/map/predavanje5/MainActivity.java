package hr.vsite.map.predavanje5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Ajde neka ova moja aktivnost reagira na klik dugmeta
        Button b2 = findViewById(R.id.dugme2);
        b2.setOnClickListener(this);

        // Idemo kreirati nekakav dummy (anonimni) objekt koji će samo reagirati klik
        Button b3 = findViewById(R.id.dugme3);
        b3.setOnClickListener(new OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Toast
                                              .makeText(getBaseContext(), R.string.tv_name, Toast.LENGTH_SHORT)
                                              .show();
                                  }
                              }
        );
    }

    public void naKlik(View v){
        // Objekt namjere, otvori iz ove aktivnosti SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        // Pošaljimo ga Androidu
        startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        Toast
                .makeText(this, R.string.poruka, Toast.LENGTH_SHORT)
                .show();
    }
}