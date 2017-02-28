package com.example.lizho.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.lizho.androidlabs.ChatDatabaseHelper.TABLE_CHAT;

public class ChatWindow extends AppCompatActivity {
    TextView textView;
    Button button;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();

    //    SQLiteDatabase database;
    protected ChatDatabaseHelper dbHelper;
    protected SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        dbHelper = new ChatDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        Toast.makeText(this, "database created", Toast.LENGTH_SHORT).show();


        String mQuery = "SELECT * From "+TABLE_CHAT;
        Cursor results = db.rawQuery(mQuery, new String[]{});
        int rows = results.getCount();
        int numOfColumn = results.getColumnCount();
        Log.i("ChatWindow","Cursor's column count ="+numOfColumn);
        results.moveToFirst();

        for (int i=1;i<=rows;i++)
        {
            int num = results.getColumnIndex(ChatDatabaseHelper.KEY_ID);
            String message = results.getString(results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE));
            arrayList.add(num,message);
            results.moveToNext();
        }

        for (int i=0;i<numOfColumn;i++)
        {
            Log.i("ChatWindow",results.getColumnName(i));
        }


        //db.query(false,ChatDatabaseHelper.DATABASE_NAME,new String[]{ChatDatabaseHelper.KEY_ID,"IndexNumber>2",new Sting[]{"4",null}});


      /*
        int numColumns = results.getColumnCount();
        int colmn = results.getColumnIndex(ChatDatabaseHelper.KEY_ID);
        int priceIndex= results.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);

        while(!results.isAfterLast())
        {
            int price = results.getInt(priceIndex);
//            Log.d("")
            results.moveToNext();
        }


*/


        //initializing
        textView = (TextView) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.listView);

        final ChatAdapter messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

//coding for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = textView.getText().toString();

                ContentValues newValue = new ContentValues();
                newValue.put(dbHelper.KEY_MESSAGE,info);
                db.insert(dbHelper.TABLE_CHAT,null,newValue);

                arrayList.add(info);
                messageAdapter.notifyDataSetChanged();
                textView.setText("");
            }
        });


//adapter

    }//end of onCreate
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    //Adapter
    private class ChatAdapter extends ArrayAdapter<String> {
        private ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return arrayList.size();
        }

        public String getItem(int position) {
            return arrayList.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0) {
                result = inflater.inflate(R.layout.chat_row_incoming, null);
                TextView tv = (TextView) result.findViewById(R.id.message_textin);
                tv.setText(getItem(position));
                ImageView iv = (ImageView) findViewById(R.id.call_comingin);
            } else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
                TextView tv = (TextView) result.findViewById(R.id.message_text);
                tv.setText(getItem(position));
                ImageView iv = (ImageView) findViewById(R.id.call_outgoing);
            }
            return result;
        }

    }
}
