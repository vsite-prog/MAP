package map.vsite.hr.predavanje2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    //Kod kojim ćemo prepoznati odgovor iz aktivnosti
    public static final int THIRD_ACTIVITY_CODE = 223;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.button2);
        b.setOnClickListener(this);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), getResources().getString( R.string.butt3), Toast.LENGTH_LONG).show();
            }
        };

        Button b2 = (Button)findViewById(R.id.button3);
        b.setOnClickListener(listener);

    }
/*
    public void onClick(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        //Toast.makeText(this, getResources().getString( R.string.butt2), Toast.LENGTH_LONG).show();
    }
    */

    public void otvoriMe(View v){
        Intent in = new Intent(this, ThirdActivity.class);
        int broj = 15; //NIje pametno ali hoćemo poslati u drugu aplikaciju
        //Dodaj podatak u intent
        in.putExtra("broj", broj);
        startActivityForResult(in, THIRD_ACTIVITY_CODE );
    }

    public void onClick(View v){
        //Želimo otvoriti drugu aktivnost
        Intent in = new Intent(this, ThirdActivity.class);
        int broj = 15; //NIje pametno ali hoćemo poslati u drugu aplikaciju
        //Dodaj podatak u intent
        in.putExtra("broj", broj);
        startActivityForResult(in, THIRD_ACTIVITY_CODE );

        /*
        EditText et = (EditText)findViewById(R.id.editText);
        //String txt = et.getText().toString();
        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText(et.getText());
        */
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // If the request went well (OK) and the request was THIRD_ACTIVITY_CODE   
        if (resultCode == Activity.RESULT_OK && requestCode == THIRD_ACTIVITY_CODE) {
            int broj = data.getIntExtra("broj",0);
            Toast
                    .makeText(this, String.valueOf(broj), Toast.LENGTH_LONG )
                    .show();
        }
    }


}
