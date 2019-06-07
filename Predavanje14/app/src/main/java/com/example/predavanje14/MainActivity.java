package com.example.predavanje14;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final int TAKE_PICTURE_REQUEST = 13;
    Uri pictureUri = null;
    File file = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == TAKE_PICTURE_REQUEST){ //Vratila nam se kamera
            if(resultCode == RESULT_OK){ // Slikano
                // Hoćemo sliku
                Log.i("MAP", "Slikano!");
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageURI(pictureUri);

                /*
                Bitmap thumbnail =  (Bitmap) data.getExtras().get("data"); // a dobili thumbnail
                ImageView imageView = findViewById(R.id.imageView);
                imageView.setImageBitmap(thumbnail);
                */
            } else if (resultCode == RESULT_CANCELED){ // KOrisnik odustao
                Log.d("MAP", "Odustaao od snimanja...");
                // I dalje postoji prazni file
                file.deleteOnExit(); // Manje agresivno od samog brisanja
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSlikaj(View v){
        // I dalje nisam siguran je li kamera funkcionira
        PackageManager packageManager = getPackageManager();
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Da li ima aplikacije koja će slikati za nas
            if( intent.resolveActivity(packageManager) != null ) {
                // Hoćemo sliku pa ćemo sami kreirati file
                try{
                    file = createImageFile();
                    if(file != null){
                        pictureUri = FileProvider.getUriForFile(this,
                                "com.example.android.fileprovider",
                                file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

                        // Sada možemo startati kameru
                        startActivityForResult(intent, TAKE_PICTURE_REQUEST );
                    } else {
                        Log.e("MAP", "Ne mogu kreirari datoteku...");
                    }

                } catch (IOException ex){
                    Log.e("MAP", "Ne mogu kreirari sliku...");
                    ex.printStackTrace(); // Da vidimo što se desilo
                }


            }

        } else {
            Toast.makeText(this, "Nema kamere...", Toast.LENGTH_LONG).show();
            Log.w("MAP", "Nema kamere..."); // KLasa sa pisanje logova, preegled kroz Logcat
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "MAP_App_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES); // Ovo je dir od naše aplikacije
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }
}
