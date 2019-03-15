package hr.vsite.map.predavanje6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    //Konstanta za prepoznavanje aktivnosti koja je napravila callback
    public final static int SECOND_ACTIVITY_CODE = 32;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void klik(View v){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Ovo je iz druge aktivnosti
        if(requestCode == SECOND_ACTIVITY_CODE && resultCode == RESULT_OK){
            //Pročitaj podatak
            String uneseniTxt = data.getStringExtra("uneseno");
            Toast
                    .makeText(this, "Unijeli ste: " + uneseniTxt, Toast.LENGTH_SHORT)
                    .show();
        }

    }
}
