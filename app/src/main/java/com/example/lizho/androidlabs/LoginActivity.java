package com.example.lizho.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usernameInput;
    EditText passwordInput;
    Button logIn;


    public static final String LOGIN_ACTIVITY = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login);
        Log.i(LOGIN_ACTIVITY,"In onCreate()");

        usernameInput=(EditText)findViewById(R.id.usernameInput);
        passwordInput=(EditText)findViewById(R.id.passwordInput);



        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }
        @Override
        protected void onResume(){
            super.onResume();
            Log.i(LOGIN_ACTIVITY,"In onResume()");
        }
        @Override
        protected void onStart() {
            super.onStart();
            Log.i(LOGIN_ACTIVITY, "In onStart()");
            final SharedPreferences sharedPref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            String name = sharedPref.getString("username", "email@domain.com");
            String password = sharedPref.getString("password", "N/A");
            //  Toast.makeText(this,"username "+name+" password "+password,Toast.LENGTH_LONG).show();
            EditText debug = (EditText) findViewById(R.id.debug);
            debug.setText(name + "  " + password);
            Button button = (Button)findViewById(R.id.logIn);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) { SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("username", usernameInput.getText().toString());
                    editor.putString("password", passwordInput.getText().toString());
                    editor.commit();
                    Intent intent1 = new Intent(getApplicationContext(),StartActivity.class);
                    startActivity(intent1);
                }
            });



        }
        @Override
        protected void onPause(){
            super.onPause();
            Log.i(LOGIN_ACTIVITY,"In onPause()");
        }
        @Override
        protected void onStop(){
            super.onStop();
            Log.i(LOGIN_ACTIVITY,"In onStop()");
        }
        @Override
        protected void onDestroy(){
            super.onDestroy();
            Log.i(LOGIN_ACTIVITY,"In onDestroy()");
        }
}
