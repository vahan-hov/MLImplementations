package com.example.vahan.photosapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.IOException;
import java.util.Objects;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private int GALLERY=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button buttonGal = findViewById(R.id.buttonGal);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonGal.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        ImageView image;
        switch (view.getId()) {
            case R.id.button1:
                image=findViewById(R.id.imageView);
                image.setImageResource(R.drawable.baelish);
                Toast.makeText(MainActivity.this, "Image Set!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button2:
                image=findViewById(R.id.imageView);
                image.setImageResource(R.drawable.lannister);
                Toast.makeText(MainActivity.this, "Image Set!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button3:
                image=findViewById(R.id.imageView);
                image.setImageResource(R.drawable.targeryan);
                Toast.makeText(MainActivity.this, "Image Set!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.buttonGal:
                imageView=findViewById(R.id.imageView);
                showPictureDialog();
                break;
                default:
                    Log.e("myTag", "Some error occurred!");
        }
    }

    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery"
        };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                choosePhotoFromGallary();
                        }

                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    Toast.makeText(MainActivity.this, "Image Set!", Toast.LENGTH_SHORT).show();
                    imageView.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            imageView.setImageBitmap(thumbnail);

            Toast.makeText(MainActivity.this, "Image Set!", Toast.LENGTH_SHORT).show();
        }
    }


}
