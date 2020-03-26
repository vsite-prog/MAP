package hr.vsite.map.predavanje5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Sada ćemo naš Toast ispaliti na klik drugog dugmeta
        // Idemo kreirati objekt koji će odgovarati na klik, to više nije aktivnost
        OnClickListener slusac = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), R.string.poruka, Toast.LENGTH_SHORT).show();
            }
        };
        // Trebamo reći dugmetu da kad se klikne javi ovome gore
        Button dugme2 = findViewById(R.id.dugme2);
        dugme2.setOnClickListener(slusac);

        // Idemo sada napraviti slično kao i kod XML registracije, neka aktivnost reagira na klik
        Button dugme3 = findViewById(R.id.dugme3);
        dugme3.setOnClickListener(this);
    }


    // Ovo smo povezali u XML-u dugmeta
    public void naKlik(View v){
        // Htjeli bi pročitati sadržaj unosa i prikazati ga u textView-u
        EditText unos1 = (EditText) findViewById(R.id.unos1); // Implicitni kast radi, mogli ga samo pridružiti
        String uneseniTekst = unos1 // Duži način pisanja
                .getText()
                .toString();
        // Dohvati TextView
        TextView prikazPogled = findViewById(R.id.textView1);
        prikazPogled.setText(uneseniTekst);
    }

    @Override
    public void onClick(View v) {
        // Toast.makeText(this, R.string.poruka, Toast.LENGTH_SHORT).show();
        // Otvori novu aktivnost
        Intent intent = new Intent(this, SecondActivity.class);
        // Sada pošalji namjeru Androidu da želimo novu aktivnost
        startActivity(intent);
    }
}
