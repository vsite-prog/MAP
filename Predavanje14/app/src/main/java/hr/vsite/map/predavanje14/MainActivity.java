package hr.vsite.map.predavanje14;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    final static String LOG_TAG = "Predavanja";
    Uri uri;
    final static int TAKE_PHOTO_REQUEST = 17;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == TAKE_PHOTO_REQUEST && resultCode == RESULT_OK) {

            // Bitmap image = (Bitmap) data.getExtras().get("data"); //Thumbnail koji vraća aktivnost
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageURI(uri); // Prikaži cijelu sliku
            galleryAddPic();
            //imageView.setImageBitmap(image); // prikaži thumbnail
        } else {
            Log.i(LOG_TAG, "Korisnik nije slikao, Cancel!");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) { // Moramo hvatati iznimku ako nema memorije ili je neki drugi problem
            e.printStackTrace();
        }
        uri =  FileProvider.getUriForFile(this,"hr.vsite.map.provider",photoFile );
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        try {
            startActivityForResult(intent, TAKE_PHOTO_REQUEST);
        }catch (ActivityNotFoundException ex){ // Nismo našli aktivnost za kameru
            Log.i(LOG_TAG, "Nema aplikacije kamere na uređaju");
            Toast.makeText(this, ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }



    }

    String currentPhotoPath;
    /**
     * Iz Android tutorial-a, kreiraj ime slike prema trenutnom vremenu
     * file je jpg tipa
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException {
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
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * Pošalji signal svim aplikacijama koje prate media file-ove da imamo novu sliku
     */
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}