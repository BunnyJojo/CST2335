package com.example.lizho.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.id;


public class StartActivity extends AppCompatActivity {

    public static final String START_ACTIVITY = "StartActivity";
    Button button;
    Button chatButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Log.i(START_ACTIVITY, "In onCreate()");

//button info
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListItemsActivity.class);
                startActivityForResult(intent,5);
              //  startActivity(intent);
            }
        });
        //second button function
        chatButton = (Button)findViewById(R.id.startChat);
        chatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                Log.i(START_ACTIVITY,"User clicked Start Chat");
                Intent chatStart = new Intent(StartActivity.this,ChatWindow.class);
                startActivity(chatStart);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(START_ACTIVITY, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(START_ACTIVITY, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(START_ACTIVITY, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(START_ACTIVITY, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(START_ACTIVITY, "In onDestroy()");
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if((requestCode==5) ||(requestCode==Activity.RESULT_OK)){
            if(5== Activity.RESULT_OK) {
                Log.i(START_ACTIVITY, "Returned to StartActivity.onActivityResult");
                Toast.makeText(this, "Result!!!!!!", Toast.LENGTH_LONG).show();
            }
             else
            {
                String messagePassed = data.getStringExtra("Response");
                Toast.makeText(this, "ListItemsActivity passed: "+messagePassed, Toast.LENGTH_LONG).show();

            }

        }
        else Log.i(START_ACTIVITY, "There is a Bug Somewhere");
    }

}
