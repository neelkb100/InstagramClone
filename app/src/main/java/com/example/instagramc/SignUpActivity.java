package com.example.instagramc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submitBtn, goToLoginScreenBtn;
    private EditText edtName,edtEmail,edtPassword;
    private String allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        edtName = findViewById(R.id.editTextName);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(submitBtn);
                }
                return false;
            }
        });

        submitBtn = findViewById(R.id.btnSubmit);
        submitBtn.setOnClickListener(this);

        goToLoginScreenBtn = findViewById(R.id.btnLoginScreen);
        goToLoginScreenBtn.setOnClickListener(this);

        if(ParseUser.getCurrentUser() != null){

            //ParseUser.logOut();

            transactionToSocialMediaActivity();
        }

    }

    public void signUpUser(){


        if(edtName.getText().toString().equals("")|| edtEmail.getText().toString().equals("") ||edtPassword.getText().toString().equals("")){

            FancyToast.makeText(SignUpActivity.this,"Email, Username, Password is required",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();


        }else {

            try {
                final ParseUser userRegister = new ParseUser();
                userRegister.setUsername(edtName.getText().toString());
                userRegister.setEmail(edtEmail.getText().toString());
                userRegister.setPassword(edtPassword.getText().toString());

                userRegister.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){
                            FancyToast.makeText(SignUpActivity.this,userRegister.get("username")+" Successfully Registered",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();

                            transactionToSocialMediaActivity();

                        } else{
                            FancyToast.makeText(SignUpActivity.this,"Error "+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                        }

                    }
                });

            } catch (Exception e){

                FancyToast.makeText(SignUpActivity.this,"Error"+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
            }
        }

    }




    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.btnSubmit:



                ProgressDialog progressDialog = new ProgressDialog(this);

                progressDialog.setMessage("Sign up "+edtName.getText().toString());
                progressDialog.show();

                signUpUser();

                progressDialog.dismiss();


                break;

            case R.id.btnLoginScreen:

                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);

                break;

        }
    }



    public void rootLayoutTapped(View V){

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

        catch (Exception e){

            e.printStackTrace();
        }

    }



    private  void transactionToSocialMediaActivity(){

        Intent intent = new Intent(SignUpActivity.this,SocialMediaActivity.class);
        startActivity(intent);


    }
}
