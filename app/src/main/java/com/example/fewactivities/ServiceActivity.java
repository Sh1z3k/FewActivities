package com.example.fewactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ServiceActivity extends AppCompatActivity {
    final int MOJA_PROSBA_POWROTU_MAIN = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }


    public void ChangeActivityToMain(View view){
        Intent intencja = new Intent(this,MainActivity.class);
        startActivityForResult(intencja,MOJA_PROSBA_POWROTU_MAIN);
    }
}