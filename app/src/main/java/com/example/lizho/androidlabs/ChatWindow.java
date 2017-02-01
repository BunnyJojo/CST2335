package com.example.lizho.androidlabs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Adapter;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {
    TextView textView;
    Button button;
    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
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
                arrayList.add(info);
                messageAdapter.notifyDataSetChanged();
                textView.setText("");
            }
        });
//adapter

    }//end of onCreate

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
                TextView tv =(TextView)result.findViewById(R.id.message_textin);
                tv.setText(getItem(position));
                ImageView iv=(ImageView)findViewById(R.id.call_comingin);
            }
            else {
                result = inflater.inflate(R.layout.chat_row_outgoing, null);
                TextView tv =(TextView)result.findViewById(R.id.message_text);
                tv.setText(getItem(position));
                ImageView iv=(ImageView)findViewById(R.id.call_outgoing);
            }
            return result;
        }

    }
}
