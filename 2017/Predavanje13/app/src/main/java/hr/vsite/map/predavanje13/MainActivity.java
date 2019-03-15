package hr.vsite.map.predavanje13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //jesmo li došli kroz notifikaciju
        String action = getIntent().getAction();
        if(action == DownloadService.ACTION_DOWNLOAD){ //Došli smo iz notifikacije i idemo prikazati stranicu
            String html = getIntent().getStringExtra("stranica");
            WebView vebView = (WebView)findViewById(R.id.webView);
            vebView.loadData(html, "text/html", "utf-8");
        }
    }

    public void onClick(View v){
        //Download kroz servis
        String url = ((EditText)findViewById(R.id.et_url))
                .getText()
                .toString();
        //Servis startamo na uobičajen način
        Intent intent = new Intent(this, DownloadService.class);

        intent.putExtra("url", url);
        startService(intent);
    }
}
