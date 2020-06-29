package com.example.instagramc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private Button mButtonSubmit, getDataBtn;
    private EditText edtName,edtMobile,edtEmail,edtPassword;
    private TextView textViewGetData;
    private String allData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mButtonSubmit = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.editTextName);
        edtMobile = findViewById(R.id.editTextMobile);
        edtEmail = findViewById(R.id.editTextEmail);
        edtPassword = findViewById(R.id.editTextPassword);

        textViewGetData = findViewById(R.id.txtViewGetData);
        getDataBtn = findViewById(R.id.btnGetAllData);

        textViewGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("RegisterUser");
                parseQuery.getInBackground("WSopk7WhpG", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object != null && e == null){

                            textViewGetData.setText("Name : "+object.get("name")+" - "+ "EMAIL : "+ object.get("email") );
                        }
                    }
                });
            }
        });


        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                allData ="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("RegisterUser");
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e ==null){

                            if(objects.size()>0){

                                for(ParseObject userRegistered: objects){

                                    allData = allData+userRegistered.get("name")+"\n";
                                }

                                FancyToast.makeText(SignUpActivity.this,allData,FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show();
                            }
                            else{
                                FancyToast.makeText(SignUpActivity.this,"Error",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show();

                            }

                        }
                    }
                });
            }
        });
    }

    public void submitRequest(View V){

        try {
            ParseObject userRegister = new ParseObject("RegisterUser");
            userRegister.put("name",edtName.getText().toString());
            userRegister.put("mobile",edtMobile.getText().toString());
            userRegister.put("email",edtEmail.getText().toString());
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
