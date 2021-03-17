package hr.vsite.map.predavanje4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void naKlik(View v){
        String tekst = getString(R.string.dugme_tekst);
        Toast.makeText(this, tekst , Toast.LENGTH_LONG).show();
        // Pročitaj iz Edit texta
        EditText et_unos = (EditText) findViewById(R.id.et_unos); // Cast u Edit text nije ni potreban
        String unos = et_unos.getText().toString(); // Ovako se čita iz kućice za unos
        // Idemo promijeniti textview unos
        TextView tv_rezultat = findViewById(R.id.tv_rezultat);
        // Sada mu postavi tekst
        tv_rezultat.setText(unos);
    }
}