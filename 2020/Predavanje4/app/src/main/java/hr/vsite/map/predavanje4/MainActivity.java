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
        // Sa get string metodom možemo dohvatiti resurs
        // Toast.makeText(this, getString(R.string.toast_ime), Toast.LENGTH_LONG).show();
        // Idemo prikazati tekst unesen u EditText, u posebnom TextView
        // Prvo čitamo iz kućice za Unos
        EditText et = findViewById(R.id.et_unos);  // Uzmi referencu na View
        String txt = et.getText().toString(); // Pročitaj tekst
        TextView  tv = findViewById(R.id.tv_prikaz);
        // Upiši u TextView
        tv.setText(txt);
    }
}
