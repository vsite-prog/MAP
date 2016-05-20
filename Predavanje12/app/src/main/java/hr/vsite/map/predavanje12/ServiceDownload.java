package hr.vsite.map.predavanje12;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceDownload extends IntentService {
    public static final String ACTION_COMPLETED = "completed";

    public ServiceDownload() {
        super("ServiceDownload");
    }

    /**
     * Servis koji radi download nekakvog URL-a
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
          String urlStr = intent.getStringExtra("url"); //ovo je stranica koju trebamo skinuti
            try {
                URL url = new URL(urlStr);
                InputStream is = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line;
                while( (line = reader.readLine()) != null ) {
                    sb.append(line);
                }
                reader.close();
                //OK kreirti ćemo notifikaciju ali gdje je intent
                Intent in = new Intent(this, MainActivity.class);
                in.putExtra("stranica", sb.toString()); //Dodajmo mu podatke koje smo downloadali
                in.setAction(ACTION_COMPLETED); // Da prepoznamo u aktivnosti dda li smo je mi otvrili
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 23, in, 0);
                //Imamo stranicu sad dajmo obavijest
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("Completed")
                        .setContentText("Download of: " + urlStr + " completed!")
                        .setSmallIcon(R.drawable.ic_euro_symbol_black_24dp) //pokaži ikonu
                        .setContentIntent(pendingIntent)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(5555, notification);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
