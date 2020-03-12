package hr.vsite.map.predavanje4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.bt2); // mogli bi i bez casta u novoj verziji Jave
        // Idemo umjesto aktivnosti kreirati novi objekt koji će reagirati na klik
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast
                        .makeText(getApplicationContext(), R.string.text1, Toast.LENGTH_LONG)
                        .show();
            }
        };
        // Jednostavno, registraraj ovaj objekt da sluša na klik
        b.setOnClickListener(listener);
    }

    public void naKilikanje(View v){
        //Klik na button, idemo pročitati što je upisano
        EditText et = (EditText) findViewById(R.id.unos);
        String unos = et
                .getText()
                .toString();
        // Sad nazdravi
        Toast
                .makeText(this, unos, Toast.LENGTH_LONG)
                .show();
    }
}
