package com.example.mylibrary;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG ="MainActivity";
    private Spinner countryspinner ;
    private EditText edtTxtName,edtTxtEmail,edtTxtPwdRepeat,edtTxtPwd ;
    private Button btnPickimage,btnRegister, signup;
    private TextView txtwarnpwdrpt,txtwarnemail,txtwarnname ;
    private RadioGroup rgGender ;
    private CheckBox agreementCheck;
    private ConstraintLayout parent ;
    DBhelper DB ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        btnPickimage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "yet to talk about ", Toast.LENGTH_SHORT).show();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtTxtName.getText().toString();
                String pass = edtTxtPwd.getText().toString();
                String repass = edtTxtPwdRepeat.getText().toString();
                String email = edtTxtEmail.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser== false){
                            Boolean insert = DB.insertData(user,pass,email);
                            if(insert==true ){
                                Toast.makeText(MainActivity.this, "registred successfully", Toast.LENGTH_SHORT).show();



                            }else{
                                Toast.makeText(MainActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "user already exists please signin!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "password not matching", Toast.LENGTH_SHORT).show();
                        edtTxtPwdRepeat.setText("");
                    }


                }


            }
        });
      btnRegister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getApplicationContext(), loginActivity.class);
              startActivity(intent);
          }
      });

        countryspinner = findViewById(R.id.countryspinner);
        countryspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this,countryspinner.getSelectedItem().toString()+" selected ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        utils.getInstance(this);

    }




    private void initViews(){

        Log.d(TAG,"initViews:started");
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtName= findViewById(R.id.edtTxtName);
        edtTxtPwd = findViewById(R.id.edtTxtPwd);
        edtTxtPwdRepeat = findViewById(R.id.edtTxtPwdRepeat);

        btnPickimage = findViewById(R.id.btnPickimage);
        btnRegister = findViewById(R.id.btnRegister);

        txtwarnemail = findViewById(R.id.txtwarnemail);
        txtwarnname = findViewById(R.id.txtwarnname);
        txtwarnpwdrpt = findViewById(R.id.txtwarnpwdrpt);

        rgGender = findViewById(R.id.rgGender);
        agreementCheck=findViewById(R.id.agreementCheck);
        parent = findViewById(R.id.parent);
        DB = new DBhelper(this);
        signup = findViewById(R.id.btnsignup);


    }
}