package com.example.booklibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ModifierlivreActivity extends AppCompatActivity {

    LivreDao l;
    EditText txttitre,txtediteur,txtgenre,txtdescp;
    ImageView cover,backarrow;
    Button btnModCover,btnModLivre;
    final int REQUEST_CODE_GALLERY=999;
    byte[] image;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifierlivre);

        backarrow=findViewById(R.id.backArrow_ModLivre);
        txttitre=findViewById(R.id.txttitre_ModLivre);
        txtediteur=findViewById(R.id.txtediteur_ModLivre);
        txtgenre=findViewById(R.id.txtgenre_ModLivre);
        txtdescp=findViewById(R.id.txtdescription_ModLivre);
        cover=findViewById(R.id.imageViewCover__ModLivre);
        btnModCover=findViewById(R.id.btnModCover__ModLivre);
        btnModLivre=findViewById(R.id.btnModifLivre__ModLivre);


        l=new LivreDao(ModifierlivreActivity.this);
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");

         Livre livre=l.getLivre(Title);
        id=livre.getId();
        txttitre.setText(livre.getTitre().toString());
        txtediteur.setText(livre.getEditeur().toString());
        txtdescp.setText(livre.getDescription().toString());
        txtgenre.setText(livre.getGenre().toString());
        cover.setImageBitmap(BitmapFactory.decodeByteArray(livre.getImage(),0,livre.getImage().length));
        image=livre.getImage();
        btnModCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        ModifierlivreActivity.this,
                        new  String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);

            }
        });

        btnModLivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean test=ModifierlivreActivity.this.traitmentInterface();


                if (test==true )
                {
                    String titre,editeur,genre,descrip;
                    byte[] cover;
                    titre=txttitre.getText().toString();
                    editeur=txtediteur.getText().toString();
                    genre=txtgenre.getText().toString();
                    descrip=txtdescp.getText().toString();
                    cover=image;
                    Livre livre1=new Livre(id,titre,editeur,genre,descrip,cover);
                    l.updateLivre(livre1);
                }



            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(ModifierlivreActivity.this,AdminActivity.class);
                ModifierlivreActivity.this.startActivity(intent1);
                ModifierlivreActivity.this.finish();
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
