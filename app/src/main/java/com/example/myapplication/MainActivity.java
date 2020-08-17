package com.example.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

//import android.support.v7.app.AppCompatActivity;


//public class MainActivity extends Activity implements View.OnClickListener
//{
//    private static final int PICK_FROM_CAMERA = 0;
//    private static final int PICK_FROM_ALBUM = 1;
//    private static final int CROP_FROM_CAMERA = 2;
//
//    private Uri mImageCaptureUri;
//    private ImageView mPhotoImageView;
//    private Button mButton;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mButton = (Button) findViewById(R.id.cameraBtn);
//        mPhotoImageView = (ImageView) findViewById(R.id.image);
//
//        mButton.setOnClickListener(this);
//    }
//
//    /**
//     * 카메라에서 이미지 가져오기
//     */
//    private void doTakePhotoAction()
//    {
//        /*
//         * 참고 해볼곳
//         * http://2009.hfoss.org/Tutorial:Camera_and_Gallery_Demo
//         * http://stackoverflow.com/questions/1050297/how-to-get-the-url-of-the-captured-image
//         * http://www.damonkohler.com/2009/02/android-recipes.html
//         * http://www.firstclown.us/tag/android/
//         */
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        // 임시로 사용할 파일의 경로를 생성
//        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
//        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
//
//        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
//        // 특정기기에서 사진을 저장못하는 문제가 있어 다음을 주석처리 합니다.
//        //intent.putExtra("return-data", true);
//        startActivityForResult(intent, PICK_FROM_CAMERA);
//    }
//
//    /**
//     * 앨범에서 이미지 가져오기
//     */
//    private void doTakeAlbumAction()
//    {
//        // 앨범 호출
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
//        startActivityForResult(intent, PICK_FROM_ALBUM);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        if(resultCode != RESULT_OK)
//        {
//            return;
//        }
//
//        switch(requestCode)
//        {
//            case CROP_FROM_CAMERA:
//            {
//                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
//                // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에
//                // 임시 파일을 삭제합니다.
//                final Bundle extras = data.getExtras();
//
//                if(extras != null)
//                {
//                    Bitmap photo = extras.getParcelable("data");
//                    mPhotoImageView.setImageBitmap(photo);
//                }
//
//                // 임시 파일 삭제
//                File f = new File(mImageCaptureUri.getPath());
//                if(f.exists())
//                {
//                    f.delete();
//                }
//
//                break;
//            }
//
//            case PICK_FROM_ALBUM:
//            {
//                // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.
//                // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.
//
//                mImageCaptureUri = data.getData();
//            }
//
//            case PICK_FROM_CAMERA:
//            {
//                // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.
//                // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.
//
//                Intent intent = new Intent("com.android.camera.action.CROP");
//                intent.setDataAndType(mImageCaptureUri, "image/*");
//
//                intent.putExtra("outputX", 90);
//                intent.putExtra("outputY", 90);
//                intent.putExtra("aspectX", 1);
//                intent.putExtra("aspectY", 1);
//                intent.putExtra("scale", true);
//                intent.putExtra("return-data", true);
//                startActivityForResult(intent, CROP_FROM_CAMERA);
//
//                break;
//            }
//        }
//    }
//
//    @Override
//    public void onClick(View v)
//    {
//        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                doTakePhotoAction();
//            }
//        };
//
//        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                doTakeAlbumAction();
//            }
//        };
//
//        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener()
//        {
//            @Override
//            public void onClick(DialogInterface dialog, int which)
//            {
//                dialog.dismiss();
//            }
//        };
//
//        new AlertDialog.Builder(this)
//                .setTitle("업로드할 이미지 선택")
//                .setPositiveButton("사진촬영", cameraListener)
//                .setNeutralButton("앨범선택", albumListener)
//                .setNegativeButton("취소", cancelListener)
//                .show();
//    }}


public class MainActivity extends AppCompatActivity {
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
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                //이미지 잘라내기 위한 크기
//                intent.putExtra("crop", "true");
//                intent.putExtra("aspectX", 0);
//                intent.putExtra("aspectY", 0);
//                intent.putExtra("outputX", 200);
//                intent.putExtra("outputY", 150);


                try {
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent,PICK_FROM_CAMERA);
                    }
//                    intent.putExtra("return-data", true);
//                    startActivityForResult(intent, PICK_FROM_CAMERA); // Error!! -> manifest파일에 CAMERA권한 없애면 카메라 실행은 됨
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
                //실행안되는상태
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 150);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
                } catch (ActivityNotFoundException e) {
                    System.out.println("예외발생!!");
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FROM_CAMERA) {
//            Bundle extras = data.getExtras();
            if (resultCode == RESULT_OK) {
                try {
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
//
//                    InputStream in = getContentResolver().openInputStream(data.getData());
//                    Bitmap bitmap = BitmapFactory.decodeStream(in);
//                    in.close();
                    Intent intent = new Intent(MainActivity.this, OCRActivity.class);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    float scale = (float) (1024 / (float) bitmap.getWidth());
                    int image_w = (int) (bitmap.getWidth() * scale);
                    int image_h = (int) (bitmap.getHeight() * scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("img", byteArray);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        //JPEG 이미지는 안되는 상태
        if (requestCode == PICK_FROM_GALLERY) {

//            if (extras2 != null) {
            if (resultCode == RESULT_OK) {
//                Bitmap bitmap = extras2.getParcelable("data");    // 오류남
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                    Intent intent = new Intent(MainActivity.this, OCRActivity.class);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    float scale = (float) (1024 / (float) bitmap.getWidth());
                    int image_w = (int) (bitmap.getWidth() * scale);
                    int image_h = (int) (bitmap.getHeight() * scale);
                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    resize.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("img", byteArray);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}