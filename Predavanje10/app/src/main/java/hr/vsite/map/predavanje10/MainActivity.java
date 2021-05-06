package hr.vsite.map.predavanje10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSpremi(View v) throws IOException {
        // Priočitaj što je uneseno u edit text
        EditText uneseni_tekst = findViewById(R.id.uneseni_tekst);
        String uneseno = uneseni_tekst.getText().toString() + "\n"; // Idi u novi red
        FileOutputStream fos = null;
        // Spremi u file koji ćeš kreirati
        try {
            // fos = openFileOutput("spremljeno.txt", Context.MODE_PRIVATE); // Sve ćemo pregaziti
            fos = openFileOutput("spremljeno.txt", Context.MODE_APPEND);
            fos.write(uneseno.getBytes());
        } catch (FileNotFoundException e) {
            // Piši u log Androida
            // A to vidimo preko logcat-a
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fos.close();
        }

        /*

         SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("spremljeno", uneseno );
        // Bez commita ništa ili bolje apply
        editor.apply();
        */
    }

    public void onCitaj(View v){
        try(FileInputStream fis = openFileInput("spremljeno.txt")) { //Ovako bi u biti trebalo pisati try/catch a ne kao gore
            StringBuilder builder = new StringBuilder(); // Ovako ćemo ubrzati stvari
            int c;
            while((c = fis.read()) != -1) { // -1 je kraj file-a i petlja je završila
                builder.append((char) c); // Znak je int prenbaci u character
            }
            String spremljeno = builder.toString();
            // Toast.makeText(this, spremljeno, Toast.LENGTH_SHORT ).show();
            TextView tv_spremljeno = findViewById(R.id.tv_spremljeno);
            tv_spremljeno.setText(spremljeno);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String spremljeno = preferences.getString("spremljeno", "Default ništa");
        Toast.makeText(this, spremljeno, Toast.LENGTH_SHORT).show();
         */
    }
}