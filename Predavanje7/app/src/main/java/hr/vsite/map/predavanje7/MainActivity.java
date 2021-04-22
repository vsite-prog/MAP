package hr.vsite.map.predavanje7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int SECOND_REQ = 13;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SECOND_REQ && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Vratili smo se sa back iz druge aplikacije", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button)findViewById(R.id.dugme)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =  new Intent(this, SecondActivity.class); // Mogla bi biti main activity ali bolje ne
        // Idemo poslati podatke u drugu aktivnost
        intent.putExtra("pozdrav", "POzdrav iz Glavne!");
        startActivityForResult(intent, SECOND_REQ);
    }

    public void naKlik(View v){
        Fragment fragment = new FirstFragment();
        FragmentManager manager = getSupportFragmentManager(); // Upravljač fragmentima
        manager
                .beginTransaction() // Kreni mijenjati fragmente
                .add( R.id.frame1, fragment) // Dodaj fragment na aktivnost
                .commit(); // sada prikaži
    }
}