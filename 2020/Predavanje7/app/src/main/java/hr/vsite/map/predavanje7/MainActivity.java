package hr.vsite.map.predavanje7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    boolean first = false;
    final static int DRUGA_AKTIVNOST = 13;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DRUGA_AKTIVNOST ){
            if( resultCode == RESULT_OK){
                // Korisnik nam je vratio nekakav podatak nalazi se u data
                ((TextView)findViewById(R.id.tv_prvi)).setText(data.getStringExtra("vrati"));
            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "Back iz druge aktivnosti", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.dugme1).setOnClickListener(this);
        findViewById(R.id.dugmeFragment).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dugme1){
            Intent intent = new Intent(this, SecondActivity.class);
            // Možemo i dodati i nekakve podatke
            intent.putExtra("poruka", "Hojlaaaaa, haaaj");
            startActivityForResult(intent, DRUGA_AKTIVNOST);
        } else if (v.getId() == R.id.dugmeFragment){
            /*
            getSupportFragmentManager() // Komponenta koja upravlja fragmentima
                    .beginTransaction()
                    .add(R.id.kontejner, new FirstFragment(), "")
                    .commit();

             */
            first = !first; // KOji prikazati fragment
            getSupportFragmentManager() // Komponenta koja upravlja fragmentima
                    .beginTransaction()
                    .replace(R.id.kontejner, first ?  new FirstFragment() : new SecondFragment())
                    .addToBackStack("promjena")
                    .commit();
        }

    }
}
