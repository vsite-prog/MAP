package hr.vsite.map.predavanje13;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int IMAGE_REQUEST_CODE = 135;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPhoto(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //sad mi daj lokaciju slike
        Uri uri = getOutputMediaFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        //pokreni slikanje, ali provjeri
        //ima li kamere
        PackageManager packageManager = getPackageManager();
        if(packageManager == null || packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Toast.makeText(this, "Nema kamere...", Toast.LENGTH_LONG).show();
            Log.w("MAP","Nema kamere." ); //Vidi se logcat
        } else if (intent.resolveActivity(packageManager) != null) { //ima li aplikacije kamere
            Toast.makeText(this, "Nema aplikacije kamere...", Toast.LENGTH_LONG).show();
        } else { //Sve OK uzmi sliku
            startActivityForResult(intent,IMAGE_REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == IMAGE_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                //Sve OK daj mi lokaciju slike
                Uri uri = data.getData();
                TextView tv = (TextView) findViewById(R.id.tv);
                tv.setText("Slika je kreirana i nalazi se na: " + uri);
            } else if (resultCode == RESULT_CANCELED){
                //User je otkazao obriši prazni file
                File mediaFile = new File(data.getData().getPath());
                mediaFile.delete(); //Trebalo bi provjeriti (catch) i potencijalnu iznimku
            }
        }

    }

    /** Create a file Uri for saving an image  */
    private static Uri getOutputMediaFileUri(){
        return Uri.fromFile(getOutputMediaFile());
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MAP");

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
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
