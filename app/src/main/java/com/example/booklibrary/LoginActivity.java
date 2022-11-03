package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity
{

    private List<Personne> personnes;
    PersonneDao p;
    public static String USER_NAME="";

    private Button btnlgn,btnadmin,btnuser;
    private ImageView backarrow;
    private TextView txtsignup;
    private EditText txtusernam,txtpsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        p=new PersonneDao(LoginActivity.this);

        personnes=p.getAllInList();

        btnlgn=findViewById(R.id.btnlgn_LoginActivity);
        btnadmin=findViewById(R.id.btnadmin);
        btnuser=findViewById(R.id.btnuser);
        txtsignup=findViewById(R.id.textViewregister_LoginActivity);
        backarrow=findViewById(R.id.backArrow_LoginActivity);
        txtusernam=findViewById(R.id.editTextusername_LoginActivity);
        txtpsw=findViewById(R.id.editTextpsw_LoginActivity);

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,AdminActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }
        });

        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,UserActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }
        });


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();
            }
        });

        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();

            }
        });

        btnlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String type = authentifier();
                    if (type != "") {
                        switch (type) {
                            case ("a"):
                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                LoginActivity.this.startActivity(intent);
                                LoginActivity.this.finish();
                                break;
                            case ("u"):
                                Intent intent1 = new Intent(LoginActivity.this, UserActivity.class);
                                LoginActivity.this.startActivity(intent1);
                                LoginActivity.this.finish();
                                break;
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "password or username Inccorect !!", Toast.LENGTH_SHORT).show();
                    }
            }
        });


    }
    public String authentifier()
    {
        String type="";

        for (int i=0;i<personnes.size();i++)
        {
            if (txtusernam.getText().toString().equals(personnes.get(i).getUser_name().toString()) && txtpsw.getText().toString().equals(personnes.get(i).getPassword().toString()))
            {
                USER_NAME=personnes.get(i).getUser_name().toString();
                type=personnes.get(i).getType().toString();
            }
        }
        return type;
    }



}
