package com.example.tt.smsdemo1;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReceiveSMSActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    public static ReceiveSMSActivity  inst;
    ArrayList<String> smsMessages = new ArrayList<String>();
    ListView smsList;
    ArrayAdapter arrayAdapter;

    public static ReceiveSMSActivity instance() {
        return inst;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_sms);
        smsList = (ListView) findViewById(R.id.smsList);

        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, smsMessages);

        smsList.setAdapter(arrayAdapter);
        smsList.setOnItemClickListener(this);
        refreshSMSList();
    }

    public void refreshSMSList() {
        ContentResolver cotentResolver = getContentResolver();
        Cursor smsInboxcursor = cotentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        int body = smsInboxcursor.getColumnIndex("body");
        int address = smsInboxcursor.getColumnIndex("address");
        int timemillis = smsInboxcursor.getColumnIndex("date");

        Date date = new Date();
        String dateMess =  new SimpleDateFormat("MM/dd/yyyy").format(new Date(timemillis));
        if (body<0||!smsInboxcursor.moveToFirst()) {
            return;
        }
        arrayAdapter.clear();
        do {
            String str = smsInboxcursor.getString(address)  + "\n"
                    + smsInboxcursor.getString(body)  + "\n"
                    + dateMess +"\n";
            arrayAdapter.add(str);
        } while (smsInboxcursor.moveToNext());


    }
    public void updateList(String mess) {
        arrayAdapter.insert(mess,0);
        arrayAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {

            String[]  smsMgs =  smsMessages.get(position).split("\n")  ;
            String  address = smsMgs[0];
            String message = "";
            for (int i = 1; i < smsMgs.length; i++) {
                message += smsMgs[i];
            }
            String messageStr = address + "\n";
            messageStr += message;
            //Toast.makeText(this,  smsMessages.get((int) id)+ "Thuc", Toast.LENGTH_SHORT).show();
            Toast.makeText(this, messageStr+ "tt2",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void goToCompose(View v) {
        Intent intent = new Intent(ReceiveSMSActivity.this, SendSMSActivity.class);
        startActivity(intent);
    }
}
