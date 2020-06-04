package hr.vsite.map.predavanje14;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final static int ACTION_CAMERA = 13;
    final static String APP_TAG = "MAP";
    File nasa_slika = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ACTION_CAMERA){
            if(resultCode==RESULT_OK){
                Bundle vraceno = data.getExtras(); // Ovdje bi trebala biti negdje slika
                Bitmap thumbnail = (Bitmap) vraceno.get("data");
                ImageView iv = findViewById(R.id.imageView);
                iv.setImageBitmap(thumbnail);
            } else { // RESULT CANCELED
                Log.i(APP_TAG, "Odustao kor od slikanja!");
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void naSlikaj(View v){
        // Idemo vidjeti je li kamera živa ili nije
        PackageManager pm = getPackageManager();
        // Ova komponenta ima informaciju što je na našem uređaju
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            // Pokreni novi intent koji hoće izvesti akciju slikanja, implicitni
            Intent  intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(pm) != null){
                try {
                    nasa_slika = createImageFile();
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            nasa_slika);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, )
                    startActivityForResult(intent, ACTION_CAMERA);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Log.i(APP_TAG, "Nema apllikacije kamere");
                Toast.makeText(this, "Aplikacija kamere se ne može koristiti!", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Nema slike, nema kamere!", Toast.LENGTH_LONG).show();
        }

    }
    private File createImageFile() throws IOException    {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        // currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
