package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    EditText username, password , email ;
    Button btnlogin ;
    DBhelper DB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        btnlogin =  findViewById(R.id.btnsignin1);
        email = findViewById(R.id.email);
        DB = new DBhelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String user = username.getText().toString();
                String pass = password.getText().toString();


                if(user.equals("")||pass.equals("")||em.equals(""))
                    Toast.makeText(loginActivity.this, "please enter all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user,pass,em);
                    if(checkuserpass==true){
                        Toast.makeText(loginActivity.this, "signin successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),homeActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(loginActivity.this, "Invalid", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}
