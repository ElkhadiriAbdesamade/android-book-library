package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {


    private EditText txtchercher;
    private Button btncherch,btnvoir;
    LivreDao ldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ldb=new LivreDao(this);


        txtchercher=findViewById(R.id.txtchercher_UserActivity);
        btncherch=findViewById(R.id.btnCherch_UserActivity);
        btnvoir=findViewById(R.id.btnvoirlivres_UserActivity);





        btnvoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserActivity.this,VoirlivresActivity.class);
                UserActivity.this.startActivity(intent);
            }
        });

        btncherch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ldb.getLivre(txtchercher.getText().toString())!=null)
                {
                    if (txtchercher.getText().toString().equals(""))
                    {
                        Toast.makeText(UserActivity.this, "Taper Le Titre De Livre !!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        {
                        Livre livre = ldb.getLivre(txtchercher.getText().toString());


                        Intent intent = new Intent(UserActivity.this, LivreActivity.class);

                        // passing data to the book activity

                        intent.putExtra("Title", livre.getTitre());
                        intent.putExtra("Editeur", livre.getEditeur());
                        intent.putExtra("Genre", livre.getGenre());
                        intent.putExtra("Descrep", livre.getDescription());
                        intent.putExtra("Cover", livre.getImage());
                        // start the activity
                        UserActivity.this.startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(UserActivity.this, "Livre Est ne existe pas !!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
