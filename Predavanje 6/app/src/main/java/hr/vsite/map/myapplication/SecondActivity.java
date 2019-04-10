package hr.vsite.map.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity   implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // Pročitaj poruku iz prve aktivnosti
        Intent intent = getIntent();
        String poruka = intent.getStringExtra("poruka");
        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText(poruka);
        // KLik registriraj
        // Registriraj klik
        Button b1 = findViewById(R.id.bt1);
        b1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // Pročitaj što je upisano i vrati lijepo nazad
        String odgovor = ((EditText)findViewById(R.id.editText)).getText().toString();
        // Spremi podatke
        Intent podaci = new Intent();
        podaci.putExtra("odgovor", odgovor);
        // Reci androidu da je sve bilo ok i daj mu podatke za nazad
        setResult(Activity.RESULT_OK, podaci);

        // idemo nazad
        finish();
    }
}
