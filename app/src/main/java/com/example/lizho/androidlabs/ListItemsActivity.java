package com.example.lizho.androidlabs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class ListItemsActivity extends AppCompatActivity {

    public static final String LIST_ITEMS_ACTIVITY = "ListItemsActivity";
    //field defines
    ImageButton imageButton;
    Switch switch1;
    CheckBox checkBox;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i(LIST_ITEMS_ACTIVITY, "In onCreate()");

        imageButton = (ImageButton) findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imagePhotoTaking = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (imagePhotoTaking.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(imagePhotoTaking, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
//coding for the switch
        switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CharSequence text="default";
                int duratioin = Toast.LENGTH_SHORT;
            if (switch1.isChecked())
            {
                text=getString(R.string.switchon);
            }
            else
            {
                text=getString(R.string.switchOff);
            }
                Toast.makeText(getApplicationContext(),text,duratioin).show();
        }
        });
//coding for the checkbox
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked())
                {
                    new AlertDialog.Builder(ListItemsActivity.this)
                        .setTitle(R.string.alert)
                        .setMessage(R.string.quitInfo)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent resultIntent = new Intent(getApplicationContext(),StartActivity.class);
                                resultIntent.putExtra("Response",getString(R.string.share));
                                setResult(Activity.RESULT_OK,resultIntent);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                }
                else;
            }
        });

    }//end of on create

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LIST_ITEMS_ACTIVITY, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LIST_ITEMS_ACTIVITY, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LIST_ITEMS_ACTIVITY, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LIST_ITEMS_ACTIVITY, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LIST_ITEMS_ACTIVITY, "In onDestroy()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE || resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButton.setImageBitmap(imageBitmap);
        }
    }
}