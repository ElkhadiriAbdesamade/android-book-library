package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    Button btnadd,btnmod,btndel,btnvoir,btndelet;
    EditText txtmod,txtdelet;
    LivreDao db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db=new LivreDao(AdminActivity.this);

        txtmod=findViewById(R.id.txttitremod_AdminActivity);
        txtdelet=findViewById(R.id.txttitredel_AdminActivity);
        btnadd=findViewById(R.id.btnAdd_AdminActivity);
        btnmod=findViewById(R.id.btnModif_AdminActivity);
        btndel=findViewById(R.id.btndele_AdminActivity );
        btnvoir=findViewById(R.id.btnvoirlivres_AdminActivity );
        btndelet=findViewById(R.id.btndele_AdminActivity);

        btndelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtdelet.getText().toString().equals(""))
                {
                    Toast.makeText(AdminActivity.this, "Taper Le Titre de livre tu va Supprimer !!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (AdminActivity.this.existLivre(txtdelet.getText().toString())==true)
                    {
                        db.deletLivre(txtdelet.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(AdminActivity.this, "Livre ne Existe pas !!!", Toast.LENGTH_SHORT).show();
                    }

                }




            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminActivity.this,AddlivreActivity.class);
                AdminActivity.this.startActivity(intent);
            }
        });
        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (AdminActivity.this.existLivre(txtmod.getText().toString())==true)
                {
                    if (txtmod.getText().toString().equals(""))
                    {
                        Toast.makeText(AdminActivity.this, "Taper Le Titre de livre tu va modifier !!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String titre=txtmod.getText().toString();
                        Livre l=db.getLivre(titre);
                        Intent intent=new Intent(AdminActivity.this,ModifierlivreActivity.class);
                        intent.putExtra("Title",l.getTitre());
                        AdminActivity.this.startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(AdminActivity.this, "Livre ne Existe pas !!!", Toast.LENGTH_SHORT).show();
                }



            }
        });
        btnvoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminActivity.this,VoirlivresActivity.class);
                AdminActivity.this.startActivity(intent);
            }
        });

    }

    public boolean existLivre(String titre)
    {
        Boolean test=true;
        Livre livre=db.getLivre(titre);
        if (livre==null)
        {
            test=false;
        }
        return test;

    }
}
