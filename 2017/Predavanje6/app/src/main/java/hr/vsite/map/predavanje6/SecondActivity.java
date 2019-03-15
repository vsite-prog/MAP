package hr.vsite.map.predavanje6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    /**
     * vrati u glavnu aktivnost ono što je korisnik unio u ETxt
     * @param v
     */
    public void onClick(View v){
        //Što je uneseno
        EditText et =  (EditText) findViewById(R.id.editText);
        String uneseniTxt = et.getText().toString();
        //Zapamti što je korisnik uni
        Intent data = new Intent();
        data.putExtra("uneseno",uneseniTxt);
        //Neka povratni rezultat bude ok
        this.setResult(RESULT_OK, data);
        //povratak
        finish();
    }
}
