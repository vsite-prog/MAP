package hr.vsite.map.predavanje1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void naKlikanje(View v) {
        Toast
                .makeText(this,"Zašte me klikate?", Toast.LENGTH_LONG)
                .show();
    }

}
