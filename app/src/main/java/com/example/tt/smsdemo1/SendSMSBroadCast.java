package com.example.tt.smsdemo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TT
 */
public class SendSMSBroadCast extends BroadcastReceiver {
    public static final String SMS_BUNDLE = "pdus";


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] sms  = (Object[])  bundle.get(SMS_BUNDLE);
            String mess = "";
            for (int i = 0; i < sms.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i] );
                String body = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress().toString();
                long timemills = smsMessage.getTimestampMillis();
                String dates =  new SimpleDateFormat("MM/dd/yyyy").format(new Date(timemills));
                mess += body + "at" + "\n";
                mess += address + "at" + "\n";
                mess += dates + "at" + "\n";
            }
            Toast.makeText(context, mess, Toast.LENGTH_SHORT).show();
            Log.d("TAG", mess);
            ReceiveSMSActivity inst = ReceiveSMSActivity.instance();
            if (inst != null) {
                inst.updateList(mess);
            }
        }
    }
}
