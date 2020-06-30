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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsername,edtPassword;
    private Button btnLogin,btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        edtUsername = findViewById(R.id.txtViewUserNameLogin);
        edtPassword = findViewById(R.id.txtViewPasswordLogin);

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnLogin);
                }
                return false;
            }
        });

        btnLogin = findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(this);

        btnSignUp =findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(this);


    }


    public void loginMethod(){

        if(edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")){
            FancyToast.makeText(LoginActivity.this,"Username, Password is required",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();
        }else {

            ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if(user != null && e == null){

                        FancyToast.makeText(LoginActivity.this,user.get("username")+" Login Successfully",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                        transactionToSocialMediaActivity();

                    } else{
                        FancyToast.makeText(LoginActivity.this,"Error "+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnlogin:

                ProgressDialog progressDialog = new ProgressDialog(this);

                progressDialog.setMessage("Login  "+edtUsername.getText().toString());
                progressDialog.show();

                loginMethod();

                progressDialog.dismiss();

                break;

            case R.id.btnSignUp:

                Intent i = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);
                break;

        }

    }


    public void loginRootLayoutTapped(View V){

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }

        catch (Exception e){

            e.printStackTrace();
        }

    }


    private  void transactionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);


    }
}