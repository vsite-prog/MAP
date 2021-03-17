package hr.vsite.map.predavanje6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String nesto = getIntent() // Sa ovim intentom smo otvoreni
                            .getStringExtra("nesto");
        // Zatvori aktivnost na klik
        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        // Drugi način reakcije na klik
        ((Button)findViewById(R.id.dugme1)).setOnClickListener(listener);
    }

    public void vrati(View v){
        setResult(Activity.RESULT_OK);
        finish();
    }
}
