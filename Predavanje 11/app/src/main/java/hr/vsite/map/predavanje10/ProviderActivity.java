package hr.vsite.map.predavanje10;

import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import android.widget.ListView;
import android.widget.TextView;

public class ProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        //Odmah pri otvaranju pročitaj sve iz content providera
        Uri uri = Uri.parse("content://" + MoraProvider.AUTHORITY + "/" + MoraProvider.PATH);   //prvo kreiraj URi objekt
        Cursor c = getContentResolver().query(uri,null,null,null,null);
        /* TODO: Correct
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.list_view_item,c,null,null,0);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(adapter);
        */
        String mora = "";
        while(c.moveToNext()) { //idi rekord po rekord
            mora += c.getString(c.getColumnIndex(MoraContract.Mora.COL_NAME_SIFRA))  //dohvati vrijednost sifre za ovaj rekord
                    + " "
                    + c.getString(c.getColumnIndex(MoraContract.Mora.COL_NAME_NAZIV))
                    + "\n";
        }
        //Upiši u text view
        ((TextView)findViewById(R.id.textView)).setText(mora);
    }
}
