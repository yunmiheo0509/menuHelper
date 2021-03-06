package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OCRActivity extends AppCompatActivity {
    Bitmap ocrimage;
    private TessBaseAPI mTess;
    String datapath = "";
    String[] menuArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        ImageView image = (ImageView) findViewById(R.id.image);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = getIntent().getByteArrayExtra("img");
        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        image.setImageBitmap(bitmap);

        ocrimage = ((BitmapDrawable) image.getDrawable()).getBitmap();
        datapath = getFilesDir()+ "/tesseract/";

        checkFile(new File(datapath + "tessdata/"));

        String lang = "kor";

        mTess = new TessBaseAPI();
        mTess.init(datapath, lang);
    }
    private void copyFiles() {
        try {
            //location we want the file to be at
            String filepath = datapath + "/tessdata/kor.traineddata";

            //get access to AssetManager
            AssetManager assetManager = getAssets();

            //open byte streams for reading/writing
            InputStream instream = assetManager.open("tessdata/kor.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            //copy the file to the location specified by filepath
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile(File dir) {
        //directory does not exist, but we can successfully create it
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        //The directory exists, but there is no data file in it
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/kor.traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    public void processImage(View view){
        String ocrResult = null;
        mTess.setImage(ocrimage);
        ocrResult = mTess.getUTF8Text();
//        TextView OCRTextView = (TextView) findViewById(R.id.ocrText);

        //한글만 추출한 후 공백으로 구분해 배열에 저장
//        String ocrResult2 = ocrResult.replaceAll("[/[a-z0-9]|[ \\[\\]{}()<>?|`~!@#$%^&*-_+=,.;:\\\"'\\\\]/g]", "");
        String ocrResult2 = ocrResult.replaceAll("^[가-힣\\s]*$", "");
        ocrResult2=ocrResult2.replaceAll("[0-9]", "");

        menuArray = ocrResult2.split(" ");

        for (int i = 0; i < menuArray.length; i++) {
            System.out.println(menuArray[i]);
        }

        Intent intent = new Intent(OCRActivity.this, MenuListActivity.class);

        intent.putExtra("menuarray", menuArray);
        startActivity(intent);

//        OCRTextView.setText(ocrResult);
    }
}
