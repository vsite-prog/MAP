package hr.vsite.predavanje10;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ContentActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        TextView tv = (TextView)findViewById(R.id.tvLaptopi);
        String txt = "";
        Cursor cursor = getContentResolver()
                .query(LaptopiProvider.CONTENT_URI_LAPTOPI, null,null,null,null);

        while(cursor.moveToNext()) {
            txt += "Naziv: " + cursor.getString(cursor.getColumnIndexOrThrow(LaptopContract.Laptop.COLUMN_NAZIV))
                    + " Tip: " + cursor.getString(cursor.getColumnIndexOrThrow(LaptopContract.Laptop.COLUMN_TIP)) + "\n";

        }

        tv.setText(txt);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);
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
}
