package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddlivreActivity extends AppCompatActivity {

    LivreDao db;

    private EditText txttitre,txtediteur,txtdescp,txtgenre;
    private Button btnAddCover,btnAddLivre;
    private ImageView cover,backarrow;
    final int REQUEST_CODE_GALLERY=999;
    byte[] image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlivre);
        db=new LivreDao(AddlivreActivity.this);



        txttitre=this.findViewById(R.id.txttitre_AddLivre);
        txtediteur=this.findViewById(R.id.txtediteur_AddLivre);
        txtdescp=this.findViewById(R.id.txtdescription_AddLivre);
        txtgenre=this.findViewById(R.id.txtgenre_AddLivre);
        btnAddCover=findViewById(R.id.btnAddCover__AddLivre);
        btnAddLivre=findViewById(R.id.btnAddLivre__AddLivre);
        cover=findViewById(R.id.imageViewCover__AddLivre);
        backarrow=findViewById(R.id.backArrow_AddLivre);

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddlivreActivity.this,AdminActivity.class);
                AddlivreActivity.this.startActivity(intent);
                AddlivreActivity.this.finish();
            }
        });

        btnAddCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddlivreActivity.this,
                        new  String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
            }
        });

        btnAddLivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titre = txttitre.getText().toString();
                String descp = txtdescp.getText().toString();
                String genre = txtgenre.getText().toString();
                String editeur = txtediteur.getText().toString();

                BitmapDrawable drawable = (BitmapDrawable) cover.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);


                Livre livre = new Livre(titre, editeur,genre,descp, image);

                Boolean test= AddlivreActivity.this.traitmentInterface();
                if(test==true)
                {
                if (db.getLivre(titre)==null)
                {

                        db.addLivre(livre);

                        Toast.makeText(AddlivreActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(AddlivreActivity.this, "Livre Est Deja Exist !!", Toast.LENGTH_LONG).show();
                }
                }
               // finish();
                txttitre.setText("");
                txtediteur.setText("");
                txtgenre.setText("");
                txtdescp.setText("");
               txttitre.requestFocus();
            }
        });

    }



    public boolean traitmentInterface()
    {

        if (txttitre.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Taper Le titre !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtediteur.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Taper Le nom De L'editeur !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtgenre.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Taper Le genre !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (txtdescp.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Taper La Description !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (image==null)
        {
            Toast.makeText(this, "S.V.P Taper Ajouter une Image !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    public void openGalleries() {

        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, REQUEST_CODE_GALLERY);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode==REQUEST_CODE_GALLERY)
        {

            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                openGalleries();
            }
            else
            {
                Toast.makeText(this, "you dont have permission", Toast.LENGTH_SHORT).show();
            }
            return;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_GALLERY) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                cover.setImageBitmap(decodeStream);

                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }

        }
    }
}
