package com.example.lizho.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ChatDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Chats.db";
    public static final int VERSION_NUM = 3;
    public static final String TABLE_CHAT = "Chat_Table";


    public static final String KEY_ID = "_id";
    public static final String KEY_MESSAGE = "message";


    public static final String SQL_CREATE = " CREATE TABLE " + TABLE_CHAT + " ( " +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KEY_MESSAGE + " TEXT " + " );";

    public static final String SQL_DELETE = "DROP TABLE IF EXISTS "+TABLE_CHAT;


    public ChatDatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_CREATE);
        Log.i("ChatDatabaseHelper","Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer)
    {
        db.execSQL(SQL_DELETE);
        onCreate(db);
        Log.i("ChatDatabaseHelper","Calling onUpgrade, oldVersion"+oldVer+"newVersion="+newVer);
    }
}
