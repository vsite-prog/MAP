package hr.vsite.map.predavanje12;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
    }

    /**
     * Čitaj iz našeg Content Provider-a
     * @param v
     */
    public void onCitaj(View v){

        //Prvo kreiramo URI
        Uri uri = Uri.parse("content://" + PticeProvider.AUTHORITY + "/" + PticeProvider.PATH );
        //Idemo pročitati naše ptice
        Cursor cursor = getContentResolver()
                .query( uri,
                        null,
                        null,
                        null,
                        null);
        StringBuilder sb = new StringBuilder();
        while (cursor.moveToNext()){ //Idemo red po red čitati
            sb.append("ID: ");
            sb.append(cursor.getInt(0)); //pročitaj ID
            sb.append(" Naziv: ");
            sb.append(cursor.getString(1)); //pročitaj Naziv
            sb.append(" Vrsta: ");
            sb.append(cursor.getString(2)); //pročitaj ID
            sb.append("\n");
        }
        //Dohvati gdje ćemo prikazi rezultate i upiši
        TextView tv = (TextView)findViewById(R.id.tv_sve_ptice);
        tv.setText(sb.toString());
    }
}
