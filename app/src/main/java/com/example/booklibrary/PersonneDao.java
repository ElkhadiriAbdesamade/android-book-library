package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PersonneDao extends MyDataBase
{
    Context context;
    public PersonneDao(Context c) {
        super(c);
        this.context=c;
    }





    //pers
    public static final String TABLE_NAME="Personne";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_NOM="nom";
    public static final String COLUMN_PRENOM="prenom";
    public static final String COLUMN_USERNAME="username";
    public static final String COLUMN_EMAIL="email";
    public static final String COLUMN_TYPE="type";
    public static final String COLUMN_PASSWORD="password";
    public static final String COLUMN_TELE="tele";

    public static final String CREATE_TABLE=
            "create table "+TABLE_NAME +"("+COLUMN_ID+
                    " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    +COLUMN_NOM + " TEXT,"
                    +COLUMN_PRENOM +" TEXT,"
                    +COLUMN_USERNAME+" TEXT,"
                    +COLUMN_EMAIL+" TEXT,"
                    +COLUMN_TYPE+" TEXT,"
                    +COLUMN_TELE+" TEXT,"
                    +COLUMN_PASSWORD+" TEXT);";
    public static final String DELETE_QUERY = "DROP table if exists " + TABLE_NAME;


    public void SignIn(Personne p)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_NOM,p.getNom());
        values.put(COLUMN_PRENOM,p.getPrenom());
        values.put(COLUMN_USERNAME,p.getUser_name());
        values.put(COLUMN_EMAIL,p.getEmail());
        values.put(COLUMN_TYPE,"u");
        values.put(COLUMN_TELE,p.getTele());
        values.put(COLUMN_PASSWORD,p.getPassword());



        long result =db.insert(TABLE_NAME,null,values);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<Personne> getAllInList() {
        ArrayList<Personne> personnes = new ArrayList<>();

        String select_query = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String nom = cursor.getString(cursor.getColumnIndex(COLUMN_NOM));
                String prenom = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
                String username = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
                String tele = cursor.getString(cursor.getColumnIndex(COLUMN_TELE));
                String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));



                Personne personne=new Personne(id,nom,prenom,username,email,type,password,tele);
                personnes.add(personne);

            } while (cursor.moveToNext());

        }

        return personnes;
    }



    public Personne getPersonne(String username) {

        Personne personne = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NOM, COLUMN_PRENOM, COLUMN_USERNAME,COLUMN_EMAIL,COLUMN_TYPE,COLUMN_TELE,COLUMN_PASSWORD}, "username=?",
                new String[]{String.valueOf(username)}, null, null, null, null);

        if (cursor.moveToFirst()) {

            int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String nom = cursor.getString(cursor.getColumnIndex(COLUMN_NOM));
            String prenom = cursor.getString(cursor.getColumnIndex(COLUMN_PRENOM));
            String user_name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String type = cursor.getString(cursor.getColumnIndex(COLUMN_TYPE));
            String tele = cursor.getString(cursor.getColumnIndex(COLUMN_TELE));
            String password = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));

            personne=new Personne(id,nom,prenom,user_name,email,type,password,tele);
        }
        return personne;

    }
}
