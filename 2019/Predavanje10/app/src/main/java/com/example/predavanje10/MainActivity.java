package com.example.predavanje10;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    public void onSpremi(View v){
        // Idemo pročitati tekst i spremiti ga u naš file
        EditText et = findViewById(R.id.editText);
        String unos = et.getText().toString();
        unos += "\n"; // Neka bude jedno ispod drugoga

        try (FileOutputStream fos = openFileOutput("podaci.txt", Context.MODE_APPEND)){
            // Otvorili smo novi ili postojeći file idemo sada pisati
            fos.write(unos.getBytes());
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public void onCitaj(View v){

        // Idemo otvoriti file za čitanje
        try (FileInputStream fos = openFileInput("podaci.txt")){
            // Nećemo konkatenirati nego ćemo biti malo brži
            StringBuilder builder = new StringBuilder();
            // Idedmo čitati iz file-a
            // Ovdje će biti svaki znak
            int sifra;
            char znak;
            while((sifra =fos.read()) != -1){
                // pretvori iznos byte-a u char tip
                znak = (char)sifra;
                builder.append(znak);
            }
            TextView tv_unos = findViewById(R.id.spremljeno);
            tv_unos.setText(builder.toString());
        } catch (FileNotFoundException exception){
            exception.printStackTrace();
        } catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
