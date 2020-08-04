package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

//import android.support.v7.app.AppCompatActivity;

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
                intent.putExtra(MediaStore.EXTRA_OUTPUT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
                //이미지 잘라내기 위한 크기
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 150);

                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(intent, PICK_FROM_CAMERA); // Error!! -> manifest파일에 CAMERA권한 없애면 카메라 실행은 됨
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
            Bundle extras = data.getExtras();
            if (resultCode == RESULT_OK) {
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

        //JPEG 이미지는 안되는 상태
        if (requestCode == PICK_FROM_GALLERY) {
            Bundle extras2 = data.getExtras();
            System.out.println(extras2);
//            if (extras2 != null) {
            if (resultCode == RESULT_OK) {
//                System.out.println("extras2 낫 널 실행중");
//                Bitmap bitmap = extras2.getParcelable("data");    // 오류남
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    in.close();
                    Intent intent = new Intent(MainActivity.this, OCRActivity.class);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                    float scale = (float) (1024 / (float) bitmap.getWidth());
//                    int image_w = (int) (bitmap.getWidth() * scale);
//                    int image_h = (int) (bitmap.getHeight() * scale);
//                    Bitmap resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
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