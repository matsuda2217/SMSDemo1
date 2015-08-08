package com.example.tt.smsdemo1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendSMSActivity extends ActionBarActivity {
    EditText phoneNo ;
    EditText smsMessage;
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_sms);

        phoneNo = (EditText) findViewById(R.id.editPhoneNumber);
        smsMessage = (EditText) findViewById(R.id.editSmsMessage);

        btnSend = (Button) findViewById(R.id.btnSendSMS);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();

            }
        });
    }

    public void sendSMS() {
        String phoneNum = phoneNo.getText().toString();
        String smsMsg = smsMessage.getText().toString();
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNum,null,smsMsg,null,null);
            Toast.makeText(getApplicationContext(),"SMS Sent" + phoneNum + " "+ smsMsg ,Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void goToInbox(View v) {
        Intent intent = new Intent(SendSMSActivity.this, ReceiveSMSActivity.class);
        startActivity(intent);
    }
}
