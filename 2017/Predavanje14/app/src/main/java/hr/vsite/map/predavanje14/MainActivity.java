package hr.vsite.map.predavanje14;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {

    //ZA ActivityResult
    final int IMAGE_CAPTURE_CODE = 113;
    //Uri je globalan da ga vidimo i kad ga dobijemo nazad
    Uri picUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Olabavi malo sigurnonosnu politiku, potrebno za Android 6.+
        //Potrebno je za Android 6+ i ručno dati dozvole za Storage u App/settings
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }

    /**
     * Slikajmo sliku da pozovemo drugu aktivnost
     * @param v
     */
    public void onClick(View v){
        //Daj mi komponentu koja će mi reći da li ima kamere
        PackageManager pckmgr = getPackageManager();
        if(pckmgr != null && pckmgr.hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            //Idemo slikati sliku
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File picFile = null;
            if(intent.resolveActivity(pckmgr) != null){
                //Imamo aplikaciju koja će slikati za nas
                //treba nam file
                picFile = getOutputMediaFile();
                if (picFile == null) {
                    Log.d("MAP", "Ne možemo kreirati file, idemo vani!");
                    return;
                }
            }

            picUri = Uri.fromFile(picFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
            //Trebalo bi kazati aplikaciji da spremi sliku u naš file
            startActivityForResult(intent, IMAGE_CAPTURE_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Da li je slika snimljena ili nije
        if(requestCode == IMAGE_CAPTURE_CODE) {
            if (resultCode == RESULT_OK) {
                //Sve je u redu pokaži sliku
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageURI(picUri);
                //POšalji bradcast da i galerija zna za njega
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                mediaScanIntent.setData(picUri);
                this.sendBroadcast(mediaScanIntent);
            } else if (resultCode == RESULT_CANCELED) {
                File picFile = new File(picUri.getPath());
                if (picFile.delete()) {
                    Log.d("MAP", "File izbrisan!");
                } else {
                    Log.w("MAP", "File nije izbrisan, greška!");
                }
            } else {
                Log.w("MAP", "POvratak iz nepoznate aktivosti!");
            }
        }
    }


    /**
     * Napravi novi file u direktoriju slika i vrati ga
     */
    private static File getOutputMediaFile(){

        File  mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MAP");

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("MAP", "Ne mogu kreirati dir sa slikama");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + "SLIKA_"+ timeStamp + ".jpg");
        return mediaFile;
    }

}
