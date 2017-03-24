package hr.vsite.map.predavanje1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //Dohvati podatak iz Intenta
        String txt = getIntent() //daj intent
                .getStringExtra("txt");// i podatak

        //POstavi text na primljeni podatak
        ((TextView)findViewById(R.id.textView2)).setText(txt);
    }
}
