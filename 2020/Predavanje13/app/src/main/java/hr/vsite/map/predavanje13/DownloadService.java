package hr.vsite.map.predavanje13;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

import androidx.core.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
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
    public static final String AKCIJA = "Download";

    public DownloadService() {
        super("DownloadService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            // Ovdje ćemo skidati neke web stranice
            // Pročitaj URL upisan u aktivnost
            String urlStr = intent.getStringExtra("urlStr");
            // Standardni Java net, idemo skinuti stranicu
            try {
                URL url = new URL(urlStr);
                InputStream inputStream =  url.openStream();
                StringBuilder builder = new StringBuilder(); // Da ubrza dodavanja u string
                int znak;
                while( (znak = inputStream.read()) != -1){ // Ovo je sporo ima i boljih načina čitanja, vidi java.net
                    builder.append((char)znak);
                }
                String htmlSource = builder.toString();
                // Ovo bi vratili aktivnosti
                Intent intentAktivnost = new Intent(this, MainActivity.class);
                intentAktivnost.putExtra("htmlSource", htmlSource);
                // POstaviti ću akciju
                intentAktivnost.setAction(AKCIJA);
                // Treba nam pending Intent da bi pokrenuli ovo iz obavijesti
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 1,  intentAktivnost,  0);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this, MainActivity.CHANNEL_ID)
                        .setContentTitle("Download")
                        .setContentText("Download service completed!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true); // Kad klikne na nju ona se briše

                mNotificationManager.notify(109, nBuilder.build());
                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
