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

public class SignupActivity extends AppCompatActivity {
    
    PersonneDao p;
    
    private EditText nom,prenom,username,email,psw,confpsw,tele;
    private Button btnSignup;
    private TextView txtlgn;
    private ImageView backarrow;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        p=new PersonneDao(SignupActivity.this);
        
        nom=findViewById(R.id.editTextname_SignupActivity);
        prenom=findViewById(R.id.editTextprenom_SignupActivity);
        username=findViewById(R.id.editTextusername_SignupActivity);
        email=findViewById(R.id.editTextemail_SignupActivity);
        psw=findViewById(R.id.editTextpsw_SignupActivity);
        confpsw=findViewById(R.id.editTextpswconf_SignupActivity);
        tele=findViewById(R.id.editTexttele_SignupActivity);
        txtlgn=findViewById(R.id.textViewlgn_SignupActivity);
        backarrow=findViewById(R.id.backArrow_SignupActivity);
        btnSignup=findViewById(R.id.btnSignup_SignupActivity);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v)
            {
                boolean test=SignupActivity.this.traitmentInterface();
                Personne p1=p.getPersonne(username.getText().toString());



                if (p1==null)
                {
                    if (test==true)
                    {
                        if (psw.getText().toString().equals(confpsw.getText().toString()))
                        {
                            p.SignIn(new Personne(nom.getText().toString(),prenom.getText().toString(),username.getText().toString(),email.getText().toString(),"u",psw.getText().toString(),tele.getText().toString()));
                        }
                        else
                        {
                            Toast.makeText(SignupActivity.this, "psw not the same !!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(SignupActivity.this, "User name est Deje Existe !!!", Toast.LENGTH_SHORT).show();
                }

            }


        });


        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,MainActivity.class);
                SignupActivity.this.startActivity(intent);
                SignupActivity.this.finish();

            }
        });
        txtlgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this,LoginActivity.class);
                SignupActivity.this.startActivity(intent);
                SignupActivity.this.finish();
            }
        });

    }

    public boolean traitmentInterface()
    {

        if (nom.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Votre Nom !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (prenom.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Votre Prenom !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (username.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Votre User Name !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (email.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Votre Email !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (psw.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Votre PassWord !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (confpsw.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Confirmation De Mote De Pass !!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (tele.getText().toString().equals(""))
        {
            Toast.makeText(this, "S.V.P Tapez Votre Numero De Telephone!!", Toast.LENGTH_SHORT).show();
            return false;
        }
return true;

    }
}
