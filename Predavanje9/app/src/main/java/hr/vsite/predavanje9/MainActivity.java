package hr.vsite.predavanje9;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void spremi (View v){
        String txt  = ((TextView)findViewById(R.id.editText))
                .getText()
                .toString();
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        prefs
                .edit()
                .putString("pojam",txt )
                .commit();

    }

    public void spremiFile (View v){
        String txt  = ((TextView)findViewById(R.id.editText))
                .getText()
                .toString();
        try {
            FileOutputStream fos = openFileOutput("postavke.txt", Context.MODE_PRIVATE);
            fos.write(txt.getBytes());
            fos.close(); //bolje u finaly
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }

    public void citaj (View v){
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        String txt = prefs.getString("pojam", "");
        Toast
                .makeText(this, "Pojam je: " + txt,Toast.LENGTH_LONG)
                .show();
    }

    public void citajFile (View v) {
        try{
            FileInputStream fis = openFileInput("postavke.txt");
            int znak;
            String txt = "";
            while((znak = fis.read()) != -1) {
                txt += String.valueOf((char) znak);
            }
            Toast
                    .makeText(this, "Pojam je: " + txt,Toast.LENGTH_LONG)
                    .show();
            fis.close();
        } catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

}
