package hr.vsite.map.predavanje12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent.getAction() == ServiceDownload.ACTION_COMPLETED){ //Ako smo došli iz Notifikacije prikaži što smo skinuli
            String stranica = intent.getStringExtra("stranica");
            ((TextView)findViewById(R.id.textView)).setText(stranica);
        }
    }

    public void OnServisKlik(View v){
        Intent intent = new Intent(this, ServiceDownload.class);
        //daj nekakav URL
        intent.putExtra("url", "http://www.vsite.hr");
        startService(intent);
    }
}
