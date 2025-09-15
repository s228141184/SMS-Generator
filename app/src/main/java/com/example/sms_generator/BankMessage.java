package com.example.sms_generator;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class BankMessage extends AppCompatActivity {

    EditText tfBank, tfAccount, tfDate, tfDescription, tfAmount;
    Spinner transactionType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_institution);

        tfBank = findViewById(R.id.tfBank);
        tfAccount = findViewById(R.id.tfAccount);
        tfDate = findViewById(R.id.tfDate);
        tfDescription = findViewById(R.id.tfDescription);
        transactionType = findViewById(R.id.transactionType);
        tfAmount = findViewById(R.id.tfAmount);
    }

    public void onSend(View view) {

        String bank = tfBank.getText().toString().trim();
        String account = tfAccount.getText().toString().trim();
        String date = tfDate.getText().toString().trim();
        String description = tfDescription.getText().toString().trim();
        String amountText = tfAmount.getText().toString().trim();
        String transactionTypes = transactionType.getSelectedItem().toString();

        if (bank.isEmpty() || account.isEmpty() || date.isEmpty() || description.isEmpty() || amountText.isEmpty()) {
            Toast.makeText(this, "Fill all details", Toast.LENGTH_SHORT).show();
            return;
        }
        float amount = Float.parseFloat(amountText);

        MessageModel message = new MessageModel(bank, account, transactionTypes, date, description, amount);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, 1);
        } else {
            sendSMS("56555", message.toString());
            tfBank.setText("");
            tfAccount.setText("");
            tfDate.setText("");
            tfDescription.setText("");
            tfAmount.setText("");
            transactionType.setSelection(0);
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
