package hr.vsite.map.predavanje10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    final String FILE_NAME = "spremi.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSpremi(View v){
        // Pročitaj iz TV-a
        EditText et = findViewById(R.id.et_unos);
        String uneseno = et.getText().toString();
        // stavi novi red iza unosa
        uneseno += "\n";
        // Upiši sada u file
        // Otvori stream za pisanje
        try (OutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND)) {
            fos.write(uneseno.getBytes()); // evo ga piši
            // Ako smo ovdje - upisano
            et.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onCitaj(View v) {
        // Idemo pročitati iz file-a i upisati u naš textview
        // Može biti dosta znakova u samom file-u a  mi ćemo čitati byte po byte
        // Da ne bi svaki put konkatenirali  na string (koji je immutable) korisni StringBuilder
        StringBuilder builder = new StringBuilder();
        try (InputStream fis = openFileInput(FILE_NAME)) {
            int i; // ovo je binarni broj u byte-u
            while((i = fis.read()) != -1){
                char c = (char)i; // Dobili bi gomilu brojeva da append-amo integer a mi želimo znakove koji predstavljaju
                builder.append(c);
            }
        } catch (FileNotFoundException e) {
            Toast
                    .makeText(this, "Daj upiši nešto prije čitanja!", Toast.LENGTH_LONG).show();
            e.printStackTrace(); // Zapiši kao grešku u LogCat
        } catch (IOException e) {
            e.printStackTrace(); // Ima file-a ali ne mogu čitati iz njega
        }
        // OK, sve je pročitano
        TextView tv = findViewById(R.id.tv_file);
        tv.setText(builder.toString()); // ajde sad fraditelju sve spoji u tekst i to ćemo upisati
    }
}
