package hr.vsite.map.predavanje10;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Spremi u file
     * @param v
     */
    public void onSpremi(View v){
        //Čitaj iz TextView-a
        EditText et = (EditText)findViewById(R.id.editText);
        String txt = et.getText().toString();

        //Dohvati stream za novi file
        FileOutputStream fos = null;
        try {
            //Otvori novi ili postojeći file
            fos = openFileOutput("spremi.txt", Context.MODE_PRIVATE);
            fos.write(txt.getBytes());
            Toast
                    .makeText(this,"Uspješno upisano!", Toast.LENGTH_SHORT)
                    .show();
        } catch(FileNotFoundException ex){
            //Ne može se otvoriti naša datoteka
            ex.printStackTrace();
        } catch (IOException ex){
            //Ne može se otvoriti naša datoteka
            ex.printStackTrace();
        } finally {
            try { //Ovo bi bilo kraće s java try with resources
                //obvezno zatvoriti
                fos.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Čitaj iz file-a u internal storage-a
     * @param v
     */
    public void onCitaj(View v) {
        FileInputStream fis = null;
        try{
            //Otvori datoteku za čitanje
            fis = openFileInput("spremi.txt");
            //Da ubrzamo
            StringBuilder sb = new StringBuilder();
            int znak; // sprema privremeno byte po byte
            while(( znak = fis.read()) != -1){
                sb.append((char)znak);
            }
            //Imamo spremljeni string
            Toast
                    .makeText(this, sb.toString(),Toast.LENGTH_LONG)
                    .show();
        } catch(FileNotFoundException ex){
            //Ne može se otvoriti naša datoteka
            ex.printStackTrace();
        } catch (IOException ex){
            //Ne može se otvoriti naša datoteka
            ex.printStackTrace();
        } finally {
            try{
                fis.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }
}
