package hr.vsite.map.predavanje8;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //rergistriraj kontekst meni
        registerForContextMenu(findViewById(R.id.tv1));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_nesto:
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(), "timePicker");
                return true;
            case R.id.action_nista:
                Toast.
                        makeText(this,"Ništa nije kliknuto", Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.action_traka:
                Toast.
                        makeText(this,"Nalazimo se na traci...", Toast.LENGTH_SHORT)
                        .show();
                return true;
        }
        /*noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_cut) {
            Toast.
                    makeText(this,"Režem...", Toast.LENGTH_SHORT)
                    .show();
            return true;
        } else if(id == R.id.action_paste) {
            Toast.
                    makeText(this,"Lijepim...", Toast.LENGTH_SHORT)
                    .show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // kalendar klasa će nam dohvatiti trenutno vrijeme na mobu, to će biti početna vrijednost
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            // vrati novi instancu TimePicker dialoga
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Nakon što korisnik odabere vrijeme aktivira se ovaj callback
            Toast
                    .makeText(getActivity(),"Sat: " + String.valueOf(hourOfDay) + " minuta: " + String.valueOf(minute),Toast.LENGTH_LONG)
                    .show();
        }
    }

}
