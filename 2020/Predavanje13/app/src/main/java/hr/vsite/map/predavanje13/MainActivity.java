package hr.vsite.map.predavanje13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "Download" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel(); // Ovo moramo da bi slali obavijesti
        // Da li smo došli ovdje preko obavijesti
        Intent intent = getIntent();
        if(intent.getAction() == DownloadService.AKCIJA){
            String htmlSource = getIntent().getStringExtra("htmlSource");
            WebView webView = findViewById(R.id.webView);
            webView.loadData(htmlSource,"text/html", "utf-8");
            Toast.makeText(this, htmlSource, Toast.LENGTH_SHORT).show();
        }

    }

    public void onOtvori(View v){
        // Startaj servis
        Intent intent = new Intent(this, DownloadService.class);
        // POšalji URL stranice servisu
        String urlStr = ((EditText)findViewById(R.id.et_url)).getText().toString();
        intent.putExtra("urlStr", urlStr);
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
