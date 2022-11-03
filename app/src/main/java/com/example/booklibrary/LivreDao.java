package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LivreDao extends MyDataBase {
    Context context;

    public LivreDao(Context c) {
        super(c);
        this.context = c;
    }

    private static final String KEY_ID = "id";
    private static final String KEY_TITRE = "titre";
    private static final String KEY_EDITEUR = "editeur";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_IMG = "image";

    private static final String TABLE_LIVRE = "livre";

    public static final String CREATE_TABLE = "create table " + TABLE_LIVRE + "(" + KEY_ID + " integer primary key autoincrement,"
            + KEY_TITRE + " TEXT ,"
            + KEY_EDITEUR + " TEXT ,"
            + KEY_GENRE + " TEXT ,"
            + KEY_DESCRIPTION + " TEXT ,"
            + KEY_IMG + " blob)";

    public static final String DELETE_QUERY = "DROP table if exists " + TABLE_LIVRE;


    public void addLivre(Livre livre) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE, livre.getTitre());
        values.put(KEY_EDITEUR, livre.getEditeur());
        values.put(KEY_GENRE, livre.getGenre());
        values.put(KEY_DESCRIPTION, livre.getDescription());
        values.put(KEY_IMG, livre.getImage());

        db.insert(TABLE_LIVRE, null, values);

    }

    public ArrayList<Livre> getAllLivres() {
        ArrayList<Livre> contacts = new ArrayList<>();

        String select_query = "select * from " + TABLE_LIVRE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        if (cursor.moveToFirst()) {

            do {

                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                String titre = cursor.getString(cursor.getColumnIndex(KEY_TITRE));
                String editeur = cursor.getString(cursor.getColumnIndex(KEY_EDITEUR));
                String genre = cursor.getString(cursor.getColumnIndex(KEY_GENRE));
                String description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
                byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

                Livre livre = new Livre(id, titre, editeur,genre,description,image);

                contacts.add(livre);

            } while (cursor.moveToNext());

        }

        return contacts;
    }



    public Livre getLivre(String titre) {

        Livre livre = null;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LIVRE, new String[]{KEY_ID, KEY_TITRE, KEY_EDITEUR, KEY_GENRE,KEY_DESCRIPTION,KEY_IMG}, "titre=?",
                new String[]{String.valueOf(titre)}, null, null, null, null);

        if (cursor.moveToFirst()) {

            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String titre1 = cursor.getString(cursor.getColumnIndex(KEY_TITRE));
            String editeur = cursor.getString(cursor.getColumnIndex(KEY_EDITEUR));
            String genre = cursor.getString(cursor.getColumnIndex(KEY_GENRE));
            String description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(KEY_IMG));

            livre = new Livre(id, titre1, editeur,genre,description,image);
        }
        return livre;
    }



    public void updateLivre(Livre livre) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITRE, livre.getTitre());
        values.put(KEY_EDITEUR, livre.getEditeur());
        values.put(KEY_GENRE, livre.getGenre());
        values.put(KEY_DESCRIPTION, livre.getDescription());
        values.put(KEY_IMG, livre.getImage());

        db.update(TABLE_LIVRE, values, "id=?", new String[]{String.valueOf(livre.getId())});

        Toast.makeText(context, "Modifier successfully !!", Toast.LENGTH_SHORT).show();
    }

    public void deletLivre(String titre) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_LIVRE, "titre=?", new String[]{String.valueOf(titre)});
        Toast.makeText(context, "Supprimer successfully !!", Toast.LENGTH_SHORT).show();
    }

}
