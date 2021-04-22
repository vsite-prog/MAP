package hr.vsite.map.predavanje7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String pozdrav = getIntent().getStringExtra("pozdrav");
        TextView tv = findViewById(R.id.textView);
        tv.setText(pozdrav);
    }
}