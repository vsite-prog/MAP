package hr.vsite.map.predavanje3;

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

    public void Kl   ik(View v){
        Toast
                .makeText(this, "Nazdravlje dugme!", Toast.LENGTH_LONG)
                .show();
    }
}
