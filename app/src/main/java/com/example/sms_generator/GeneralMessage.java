package com.example.sms_generator;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GeneralMessage extends AppCompatActivity {
    private EditText tfMessage, tfStartDate, tfEndDate, tfNumberOfBatch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        tfMessage = findViewById(R.id.tfMessage);
        tfStartDate = findViewById(R.id.tfStartDate);
        tfEndDate = findViewById(R.id.tfEndDate);
        tfNumberOfBatch = findViewById(R.id.tfNumberOfBatch);

    }

    public void onSend(View view) {

        String message = tfMessage.getText().toString().trim();
        String startDate = tfStartDate.getText().toString().trim();
        String endDate = tfEndDate.getText().toString().trim();

        if (message.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || tfNumberOfBatch.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Fill all details", Toast.LENGTH_SHORT).show();
            return;
        }
        int numOfBatch = Integer.parseInt(tfNumberOfBatch.getText().toString());

        MessageModel messages = new MessageModel(message, startDate, endDate, numOfBatch);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
        } else {
            while (numOfBatch > 0){
                sendSMS("56555", message);
                numOfBatch --;
            }
            tfMessage.setText("");
            tfStartDate.setText("");
            tfEndDate.setText("");
            tfNumberOfBatch.setText("");
        }
    }
    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
