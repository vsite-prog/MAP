package com.example.predavanje13;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "download";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel(); // registriraj na Androidu novu skupinu obavijesti
        Intent intent = getIntent();
        if(intent.getAction() == DownloadService.DOWNLOAD_ACTION ){
            String htmlStr = intent.getStringExtra("htmlStr");
            WebView view = findViewById(R.id.webView);
            view.loadData(htmlStr, "text/html", "utf-8");
            /* Prikaži ga u TV-u
            TextView tv = findViewById(R.id.tv1);
            tv.setText(htmlStr);
            */
        }

    }

    public void startajServis(View v){

        String urlStr = "https://vsite.hr"; // Umjesto hardcode-a mogli smo i dati korisniku da unosi
        Intent intent = new Intent(this, DownloadService.class); // eksplicitni intent
        intent.putExtra("url", urlStr);
        // Idemo download
        startService(intent);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
