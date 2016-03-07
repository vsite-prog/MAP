package hr.vsite.predavanje13;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    final int IMAGE_REQUEST_CODE = 17;
    String filePath; //Photo file path

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  public void onTakePhoto(View v) {
        PackageManager packageManager = getPackageManager();
        if (packageManager == null || !packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) ) {
            Toast
                    .makeText(this, "Nema kamere, bye...", Toast.LENGTH_LONG)
                    .show();
            return;

        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Hoće li neka aplikacija uhvatiti ovaj intent
        if (intent.resolveActivity(packageManager) != null){
            //kreiraj file
            File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MAP");
            //Kreiraj direktorij
            dir.mkdirs();
            //Kreiraj novu fotku
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            try {
                File fImage = File.createTempFile(
                        timeStamp,
                        ".jpg",
                        dir
                );
                filePath = fImage.getAbsolutePath();
                //Dodaj file u koji smo spremili
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fImage));
                startActivityForResult(intent, IMAGE_REQUEST_CODE );
            } catch ( IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast
                        .makeText(this, "Slika kreirana: " + filePath , Toast.LENGTH_LONG)
                        .show();

            } else if (resultCode == RESULT_CANCELED) {
                File f = new File(filePath);
                f.delete();
                Toast
                        .makeText(this, "Slika nije spremljena ", Toast.LENGTH_LONG)
                        .show();

            }
        }
    }
}
