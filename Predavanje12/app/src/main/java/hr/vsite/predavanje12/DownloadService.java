package hr.vsite.predavanje12;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by IBritvic on 12.1.2015..
 */
public class DownloadService extends IntentService {
    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Sada idemo raditi nešto

        try {
            URL url = new URL("http://www.vsite.hr");
            StringBuilder sb = new StringBuilder();
            InputStream stream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while( (line = reader.readLine()) != null ) {
                sb.append(line);
            }
            reader.close();



            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setContentTitle("Download gotov")
                    .setContentText("Kliknite za prikaz downloada...")
                    .setSmallIcon(R.drawable.ic_launcher);

            //Dodaj kod za otvaranje aktivnosti
            Intent intentActivity = new Intent(this, DownloadActivity.class);
            intent.putExtra("downloadText", sb.toString());

            PendingIntent pendo = PendingIntent.getActivity(this, 12, intentActivity, 0);

            builder.setContentIntent(pendo);
            NotificationManager nManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            nManager.notify(1333, builder.build() );

        }catch(MalformedURLException ex) {
            ex.printStackTrace();
        }catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
