package hr.vsite.predavanje3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button butt = (Button)findViewById(R.id.butt1);
/*
        OnClickListener listener = new OnClickListener() {
            public void onClick(View v){
                Toast
                        .makeText(getBaseContext(), "Event handler, interface...",Toast.LENGTH_LONG)
                        .show();
            }
        };
        */

        Button butt2 = (Button)findViewById(R.id.butt2);
        butt2.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public  void onClick(View v)
    {
        Toast
                .makeText(this, "Event handler, interface...",Toast.LENGTH_LONG)
                .show();
    }

    public void otvoriNovu(View v){
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }
}
