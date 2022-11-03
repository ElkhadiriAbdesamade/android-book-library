package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBase extends SQLiteOpenHelper
{

    private static final String DB_NAME = "Library_db";
    private static final int DB_VESION = 2;



    public MyDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VESION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(LivreDao.CREATE_TABLE);

        db.execSQL(PersonneDao.CREATE_TABLE);





    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(LivreDao.DELETE_QUERY);
        db.execSQL(PersonneDao.DELETE_QUERY);

        onCreate(db);



    }
}
