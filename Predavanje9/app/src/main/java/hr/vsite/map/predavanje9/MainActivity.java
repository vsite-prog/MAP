package hr.vsite.map.predavanje9;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSpremi(View v){
        String uneseniTxt = ((EditText)findViewById(R.id.editText))
                .getText()
                .toString();
        //Želimo spremiti unos korisnika u Shared preferences
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit(); //on mi treba za pisanje u SP
        editor.putString("tekst",uneseniTxt );
        editor.commit(); //spremi u file

    }

    public void onCitaj(View v) {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String spremljeniTxt = preferences.getString("tekst","cdxfdsfs<"); //drugi param je default value , ako nema podatka
        Toast.makeText(this, spremljeniTxt, Toast.LENGTH_SHORT).show();
    }

    public void onSpremiFile(View v){
        FileOutputStream fos = null; //Ovo je java.io način za pisanje u file preko stream-a
        try { //ovdje bi bilo bolje koristiti try with resources
            String uneseniTxt = ((EditText)findViewById(R.id.editText))
                    .getText()
                    .toString();
            byte[] uneseniBajtovi = uneseniTxt.getBytes();

            fos = openFileOutput("nas.txt", Context.MODE_PRIVATE);

            fos.write(uneseniBajtovi);
        } catch (IOException ex){
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void onCitajFile(View v){
        String txt = "";
        try{
            FileInputStream fis = openFileInput("nas.txt");
            StringBuilder builder = new StringBuilder();
            int znak;
            while((znak = fis.read()) != -1){ //čitaj znak po znak
                builder.append((char)znak); // dodaj znak u niz
            }
            txt = builder.toString(); //završi čitanje file-a i daj tekst
        }  catch (IOException ex) {
            ex.printStackTrace();
        }

        Toast.makeText(this, txt, Toast.LENGTH_LONG).show();
    }
}
