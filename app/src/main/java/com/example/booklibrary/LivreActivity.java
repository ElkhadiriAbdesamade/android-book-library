package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LivreActivity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,txtgenre,txtediteur;
    private ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livre);



        tvtitle =  findViewById(R.id.txttitle_VoirUnlivr);
        txtediteur =  findViewById(R.id.txtediteur_VoirUnlivr);
        tvdescription = findViewById(R.id.txtDesc_VoirUnlivr);
        txtgenre =  findViewById(R.id.txtCat_VoirUnlivr);
        img =  findViewById(R.id.cover_VoirUnlivr);




        // Recieve data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String editeur = intent.getExtras().getString("Editeur");
        String genre = intent.getExtras().getString("Genre");
        String descrep = intent.getExtras().getString("Descrep");
        byte[] image = intent.getExtras().getByteArray("Cover") ;


        // Setting values

        tvtitle.setText(Title);
        txtediteur.setText(editeur);
        txtgenre.setText(genre);
        tvdescription.setText(descrep);
        img.setImageBitmap(BitmapFactory.decodeByteArray(image,0,image.length));


    }
}
