package com.example.fewactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DrugaAktyw extends AppCompatActivity {

    int CHANGE_ACTIVITY_TO_SMS = 4;
    String textFieldValue;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga_aktyw);

        editText = findViewById(R.id.editTextTextPersonName);

        Intent intent = getIntent();

        if(intent != null) {
            Bundle extras = intent.getExtras();

            String text = extras.getString("text");

            editText.setText(text);
        }
    }
    public void onClick(View view) {
        Intent zwrotna=new Intent();
        zwrotna.putExtra("wynik",editText.getText().toString());
        setResult(RESULT_OK, zwrotna);
        finish();}



    public void ChangeToFirstActivity(View view){
        Intent intencja = new Intent(this, MainActivity.class);

        startActivity(intencja);
    }
    public void ChangeActivityToSMS(View view){
        Intent intencja = new Intent(this,MainActivity2.class);

        startActivityForResult(intencja,CHANGE_ACTIVITY_TO_SMS);
    }

}