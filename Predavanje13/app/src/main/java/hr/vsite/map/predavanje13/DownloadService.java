package hr.vsite.map.predavanje13;

import android.app.IntentService;
import android.app.Notification;
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
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {


    // KOnstruktor postavlja ime servisa
    public DownloadService() {
        super("DownloadService");
    }

       @Override
    protected void onHandleIntent(Intent intent) { // Ova metoda će nam pokrenuti drugu nit
        if (intent != null) {
            // Pretpostavimo da smo dobili adresu
            String adresa = intent.getStringExtra("adresa"); // isto kao u aktivnosti
            // Htjeli bi skinuti stranicu
            try {
                URL url = new URL(adresa);
                StringBuilder graditeljTeksta = new StringBuilder(); // Ubrzati će kreiranje stringa
                InputStream is = url.openStream();
                int broj;
                while( (broj = is.read()) != -1){
                    char znak = (char)broj;
                    graditeljTeksta.append(znak); // gradi tekst
                }
                String html = graditeljTeksta.toString();
                // žELIMO vratiti podatke i staviti ćemo ih u intent
                Intent noviIntent = new Intent(this, MainActivity.class);
                // Spremi dobiveni HTML
                noviIntent.putExtra("html", html);
                // Mogli bi staviti akciju ali vidimo iz extra podataka
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 12, noviIntent, 0);
                // Treb anam obavijest
                NotificationCompat.Builder graditeljObavijesti = new NotificationCompat.Builder(this, MainActivity.KANAL_ID );
                graditeljObavijesti
                        .setContentTitle("Download servis")
                        .setContentText("Download je uspješno završio!\n Sve OK!")
                        .setSmallIcon(R.drawable.ic_launcher_foreground) // Ikona koja se pokaže kod obavijesti
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT) //imamo konstante za to
                        .setAutoCancel(true); // Klik na obavijest je briše
                // POslati obavijest, treba nam sistemski servis
                Notification obavijest = graditeljObavijesti.build();
                NotificationManager servisManager =  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                servisManager.notify(1, obavijest);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


}