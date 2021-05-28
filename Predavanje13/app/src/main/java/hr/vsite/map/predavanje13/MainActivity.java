package hr.vsite.map.predavanje13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String KANAL_ID = "Download";
    public static String KANAL_IME = "Download servis";
    public static String KANAL_OPIS = "Download servis, bla, bla!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();
        // Da li smo došli iz obavijesti
        String html = getIntent().getStringExtra("html");
        if(html != null){
            // Dobili smo stranicu koju smo skinuli
            Toast
                    .makeText(this, html, Toast.LENGTH_LONG)
                    .show();
        }



    }

    public void onClick(View v){
        // Pokreni servis kao što se pokreće aktibnost, intentom
        Intent intent = new Intent(this, DownloadService.class);
        // Nađi adresu koja je unesena
        EditText et_adresa = findViewById(R.id.et_adresa);
        intent.putExtra("adresa", et_adresa.getText().toString());
        startService(intent); //servis pokrenut
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(KANAL_ID, KANAL_IME, importance);
            channel.setDescription(KANAL_OPIS);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}