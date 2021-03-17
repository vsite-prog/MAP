package hr.vsite.map.predavanje3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Event handler mora biti ovakvog potpisa
    public void kliknutSam(View v){
        // Ovo je kratka poruka na uređaju
        Toast
                .makeText(this, "Hvala što ste kliknuli!", Toast.LENGTH_LONG)
                .show();
    }
}
