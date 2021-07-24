package com.example.fewactivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final int requestNumber = 1;
    final int PROSBA_O_ZDJECIE = 2;
    final int MOJA_PROSBA_WYSLANIA_SMS = 3;
    final int MOJA_PROSBA_USLUGI = 4;



    TextView textView1;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView);
        imageView1 = findViewById(R.id.imageView);


    }

    public void ChangeActivity(View view){
        Intent intencja = new Intent(this,DrugaAktyw.class);
        intencja.putExtra("text",textView1.getText().toString());
        startActivityForResult(intencja,requestNumber);
    }

    public void ChangeActivity2(View view){
        Intent intencja = new Intent(this,ServiceActivity.class);
        startActivityForResult(intencja,MOJA_PROSBA_USLUGI);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == requestNumber) {
            String result = data.getStringExtra("result");
            textView1.setText(result);
        }

        if (requestCode == PROSBA_O_ZDJECIE) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageView1.setImageBitmap(bitmap);
        }
    }

    public void zrobZdjecie(View view) {
        Intent intencja = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(intencja, PROSBA_O_ZDJECIE);
       } catch (ActivityNotFoundException e) {
            Log.v("klucz",e.getMessage());
        }
   }








}