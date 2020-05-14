package com.example.instagramc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpActivity extends AppCompatActivity {

    Button mButtonSubmit;
    EditText edtName,edtMobile,edtEmail,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mButtonSubmit = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.editTextName);
        edtMobile = findViewById(R.id.editTextMobile);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);

    }

    public void submitRequest(View V){

        try {
            ParseObject userRegister = new ParseObject("Register");
            userRegister.put("name",edtName.getText().toString());
            userRegister.put("mobile",edtMobile.getText().toString());
            userRegister.put("mobile",edtMobile.getText().toString());
            userRegister.put("password",edtPassword.getText().toString());
            userRegister.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if(e == null){
                        FancyToast.makeText(SignUpActivity.this,"Successfully Registered",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                    } else{

                        FancyToast.makeText(SignUpActivity.this,"Error"+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();
                    }
                }
            });
        } catch (Exception e){

            FancyToast.makeText(SignUpActivity.this,"Error"+ e.getMessage(),FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

        }




    }
}
