package com.example.fewactivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS = 123;
    private Button buttonSendSms;
    private EditText phoneNumber;
    private EditText smsContent;
    private String phoneNo;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonSendSms = findViewById(R.id.sendSMS);
        phoneNumber = findViewById(R.id.phoneNumber);
        smsContent = findViewById(R.id.smsContent);

        buttonSendSms.setOnClickListener(this::sendSMSMessage);
    }
    public void sendSMSMessage(View view) {

        if(!checkPermission()) {
            Toast.makeText(getApplicationContext(), "Brak uprawnień", Toast.LENGTH_LONG).show();
            askForPermission();
            return;
        }

        phoneNo = phoneNumber.getText().toString();
        message = smsContent.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, message, null, null);
        Toast.makeText(getApplicationContext(), "SMS wysłany", Toast.LENGTH_LONG).show();
    }
    public boolean checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.v("Błąd zezwolenia: ", "Nie otrzymano zezwolenia");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
            return false;
        } else {
            Log.v("Zezwolenie: ", "jest zezwolenie");
            return true;
        }
    }
    public void askForPermission() {

        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    phoneNo = phoneNumber.getText().toString();
                    message = smsContent.getText().toString();

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getApplicationContext(), "SMS wysłany", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS failed, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

}