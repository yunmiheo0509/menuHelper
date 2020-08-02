package com.example.myapplication;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;

//import android.support.v7.app.AppCompatActivity;

public class MainActivity extends Activity {
    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cameraBtn = findViewById(R.id.cameraBtn);
        Button galleryBtn = findViewById(R.id.galleryBtn);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //카메라 호출
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                //이미지 잘라내기 위한 크기
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 150);

                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PICK_FROM_CAMERA);
                } catch (ActivityNotFoundException e) {
                    System.out.println("예외발생!!");
                }
            }
        });
        galleryBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                //Gallery호출
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                //잘라내기 셋팅
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 150);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent.createChooser(intent,"Complete action using"), PICK_FROM_GALLERY);
                } catch (ActivityNotFoundException e) {
                    System.out.println("예외발생!!");
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("온액티비티리저트");
        System.out.println(data);
        if (requestCode == PICK_FROM_CAMERA) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bitmap = extras.getParcelable("data");
                Intent intent = new Intent(MainActivity.this, OCRActivity.class);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                float scale = (float) (1024 / (float) bitmap.getWidth());
                int image_w = (int) (bitmap.getWidth() * scale);
                int image_h = (int) (bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image", byteArray);
                startActivity(intent);

//                byte[] byteArray = stream.toByteArray();
////                Intent intent = new Intent(MainActivity.this, OCRActivity.class);
////                intent.putExtra("data", photo);
////                startActivity(intent);
//                imgview.setImageBitmap(photo);
            }
        }
        if (requestCode == PICK_FROM_GALLERY) {
            Bundle extras2 = data.getExtras();
            System.out.println("이프문 안에는 들어옴");
            System.out.println(extras2);
            if (extras2 != null) {
                System.out.println("extras2 낫 널 실행중");
                Bitmap bitmap = extras2.getParcelable("data");
                Intent intent = new Intent(MainActivity.this, OCRActivity.class);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                float scale = (float) (1024 / (float) bitmap.getWidth());
                int image_w = (int) (bitmap.getWidth() * scale);
                int image_h = (int) (bitmap.getHeight() * scale);
                Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent.putExtra("image", byteArray);
                startActivity(intent);


                //                intent.putExtra("data", photo);
//                startActivity(intent);
////                imgview.setImageBitmap(photo);
            }
        }
    }
}


//        //orc구성
//        imageView2 = findViewById(R.id.image2);
//
//        Bundle extras  = getIntent().getExtras();
//        byte[] byteArray = getIntent().getByteArrayExtra("image");
//
////        image2 = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
////        imageView1.setImageBitmap(image);
//
//        image = BitmapFactory.decodeResource(getResources(), R.drawable.testimage);
//
//        datapath = getFilesDir()+ "/tesseract/";
//
//        checkFile(new File(datapath + "tessdata/"));
//
//        String lang = "kor";
//        mTess = new TessBaseAPI();
//        mTess.init(datapath, lang);
//
//        //갤러리구성
//        imageView1 = (ImageView)findViewById(R.id.image);
//        gallery= (Button)findViewById(R.id.button);
//        gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, 1);
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // Check which request we're responding to
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1) {
//            // Make sure the request was successful
//            if (resultCode == RESULT_OK) {
//                try {
//                    // 선택한 이미지에서 비트맵 생성
//                    InputStream in = getContentResolver().openInputStream(data.getData());
//                    Bitmap img = BitmapFactory.decodeStream(in);
//                    in.close();
//                    // 이미지 표시
//                    imageView1.setImageBitmap(img);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    private void copyFiles() {
//        try {
//            //location we want the file to be at
//            String filepath = datapath + "/tessdata/kor.traineddata";
//
//            //get access to AssetManager
//            AssetManager assetManager = getAssets();
//
//            //open byte streams for reading/writing
//            InputStream instream = assetManager.open("tessdata/kor.traineddata");
//            OutputStream outstream = new FileOutputStream(filepath);
//
//            //copy the file to the location specified by filepath
//            byte[] buffer = new byte[1024];
//            int read;
//            while ((read = instream.read(buffer)) != -1) {
//                outstream.write(buffer, 0, read);
//            }
//            outstream.flush();
//            outstream.close();
//            instream.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void checkFile(File dir) {
//        //directory does not exist, but we can successfully create it
//        if (!dir.exists()&& dir.mkdirs()){
//            copyFiles();
//        }
//        //The directory exists, but there is no data file in it
//        if(dir.exists()) {
//            String datafilepath = datapath+ "/tessdata/kor.traineddata";
//            File datafile = new File(datafilepath);
//            if (!datafile.exists()) {
//                copyFiles();
//            }
//        }
//    }
//
//    public void processImage(View view){
//        String OCRresult = null;
//        mTess.setImage(image);
//        OCRresult = mTess.getUTF8Text();
//        TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
//
//        OCRTextView.setText(OCRresult);
//    }
//}
