package com.example.predavanje13;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class DownloadService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.predavanje13.action.FOO";
    private static final String ACTION_BAZ = "com.example.predavanje13.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.predavanje13.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.predavanje13.extra.PARAM2";

    public DownloadService() {
        super("DownloadService");
    }

    public static final String DOWNLOAD_ACTION = "download";
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) { // Ovaj servis ima samo jednu akciju a to je download
           String urlStr = intent.getStringExtra("url");
           try {
               // Malo standardne jave
               URL url = new URL(urlStr);
               InputStream inputStream = url.openStream();
               StringBuilder stringBuilder = new StringBuilder();
               // Čitamo znak po znak
               int c;
               while((c = inputStream.read()) != -1){ // Ovo je užasno sporo, trebalo bi koristiti buffer
                   stringBuilder.append((char) c);
               }
               String htmlStr = stringBuilder.toString();
               Intent notIntent = new Intent(this, MainActivity.class);
               notIntent.putExtra("htmlStr", htmlStr);
               // Idemo staviti akciju da se vidi tko otvara aktivnost
               notIntent.setAction(DOWNLOAD_ACTION);
               // Treba nam pending Intent
               PendingIntent pendingIntent = PendingIntent.getActivity(this,0, notIntent, 0);

               NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                       .setChannelId(MainActivity.CHANNEL_ID) // Od Android 8 svaka not. mora biti u kanalu
                       .setContentTitle("Download")
                       .setContentText("Download complete!")
                       .setSmallIcon(R.drawable.ic_launcher_foreground)
                       .setContentIntent(pendingIntent)
                       .setAutoCancel(true); // Gotov je notification, briše se
               NotificationManager  mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
               mNotificationManager.notify(11,builder.build());

           } catch(IOException ex) {
                ex.printStackTrace();
           }

        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
