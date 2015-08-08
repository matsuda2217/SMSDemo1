package com.example.tt.smsdemo1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToCompose(View view) {
        Intent intent = new Intent(MainActivity.this, SendSMSActivity.class);
        startActivity(intent);

    }

    public void goToInbox(View view) {
        Intent intent = new Intent(MainActivity.this, ReceiveSMSActivity.class);
        startActivity(intent);

    }

}
