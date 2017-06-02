package hr.vsite.map.predavanje13;

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
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {
        public final static String ACTION_DOWNLOAD = "Download";

    /**
     * Konstruktor našeg servisa
     */
    public DownloadService() {
        super("DownloadService");
    }


    /**
     * Naš download servis
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //idemo sada download-ati kroz java.net
            String urlString = intent.getStringExtra("url");
            try {
                URL url = new URL(urlString);
                InputStream is = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuilder sb = new StringBuilder();
                String line = ""; //Ovdje ćemo spremati liniju po liniju
                while( (line = reader.readLine()) != null ) {
                    sb.append(line);
                }
                //Kreiramo pending intent za naš download
                //Ako je sve OK imamomo file
                Intent intentActivity = new Intent(getBaseContext(), MainActivity.class);
                intentActivity.putExtra("stranica", sb.toString());
                intentActivity.setAction(ACTION_DOWNLOAD);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),12, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                //sada idemo napraviti obavijest
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext());
                //Stavi sve komponente i idemo
                builder
                        .setContentTitle("Download")
                        .setContentText("Download gotov!")
                        .setSmallIcon(android.R.drawable.stat_sys_download_done)
                        .setContentIntent(pendingIntent);

                //Okini notifikaciju
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(12, builder.build() ); //12 je ID i služi za definiranje notif. kakko bi je mogli ažurirati

            } catch (MalformedURLException ex){
                //Pokaži nam u čemu je greška, nema Toast-a
                ex.printStackTrace();
            } catch (IOException ex){
                //Pokaži nam u čemu je greška, nema Toast-a
                ex.printStackTrace();
            }

        }
    }

}
