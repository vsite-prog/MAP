package map.vsite.hr.predavanje2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent in = getIntent();
        int broj = in.getIntExtra("broj", 0); //pročitaj broj
        EditText et = (EditText)findViewById(R.id.editText);
        et.setText(String.valueOf(broj));

    }

    public void posalji(View v){
        EditText et = (EditText)findViewById(R.id.editText);
        //Dohvati što je uneseno
        int broj = Integer.parseInt(et.getText().toString());
        //Kreiraj novi intent za povrat podataka
        Intent in = new Intent();
        in.putExtra("broj", broj);
        //Vrati intent nazad aplikaciji
        setResult(Activity.RESULT_OK, in);
        //Zatvori aktivnost
        finish();
    }

}
