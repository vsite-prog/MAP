package hr.vsite.map.predavanje1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements  OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Prvo smo ga importali
        //Interface!!! kreiramo objekt anonimne klase koja ima taj interface
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast
                        .makeText(MainActivity.this, "U drugom smo objektu", Toast.LENGTH_SHORT)
                        .show();
            }
        };
        //Registriraj klik na button2 za obradu
        //Dohvati view i cast-aj ga u button
        Button b2 = (Button)findViewById(R.id.button2);
        b2.setOnClickListener(listener);

        //Dohvati b3
        Button b3 = (Button)findViewById(R.id.button3);
        b3.setOnClickListener(this);

        //Dohvati b4
        Button b4 = (Button)findViewById(R.id.button4);
        b4.setOnClickListener(this);
    }


    public void naKlikanje(View v) {
        Toast
                .makeText(this,"Zašte me klikate?", Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onClick(View v) {
        //vidi koji je button u pitanju i nešto napravi
        if(v.getId() == R.id.button3){
            //Dohvati ono što je korisnik napisao
            EditText et = (EditText) findViewById(R.id.editText);
            String txt = et.getText().toString();
            //Sad dohvati view u koji ćeš to upisati
            TextView tv = (TextView)findViewById(R.id.textView);
            //Piši
            tv.setText(txt);
        } else {
            //Namjera nam je otvoriti drugu aktivnost
            Intent intent = new Intent(this, SecondActivity.class); //explicitno
            //Pošalji neki podatak u drugu aktivnost
            intent.putExtra("txt","Pozdrav iz prve aktivnosti");
            startActivity(intent); //sada pokreni drugu

        }

    }
}
