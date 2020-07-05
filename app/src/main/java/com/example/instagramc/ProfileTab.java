package com.example.instagramc;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class ProfileTab extends Fragment {

  private EditText editTextPName,editTextPBio,editTextPProfession,editTextPHobbies,editTextPFavSports;
  private Button buttonSaveInfo;

    final ParseUser parseUser = ParseUser.getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        editTextPName = view.findViewById(R.id.edtProfileName);
        editTextPBio = view.findViewById(R.id.edtProfileBio);
        editTextPProfession = view.findViewById(R.id.edtProfileProfession);
        editTextPHobbies = view.findViewById(R.id.edtProfileHobbies);
        editTextPFavSports = view.findViewById(R.id.edtProfileFavSports);

        buttonSaveInfo  = view.findViewById(R.id.btnUpdateInfo);

        profileDataCheckAndSet();

        buttonSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName",editTextPName.getText().toString());
                parseUser.put("profileBio",editTextPBio.getText().toString());
                parseUser.put("profileProfession",editTextPProfession.getText().toString());
                parseUser.put("profileHobbies",editTextPHobbies.getText().toString());
                parseUser.put("profileFavSport",editTextPFavSports.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null ){

                            progressDialog.setMessage("Saving data in progress");
                            progressDialog.show();

                            FancyToast.makeText(getContext(),"Info Save Successfully",FancyToast.LENGTH_SHORT,FancyToast.INFO,false).show();
                        }
                        else {

                            FancyToast.makeText(getContext(),e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                        }

                        progressDialog.dismiss();
                    }
                });
            }
        });

        return view;
    }


    public void profileDataCheckAndSet(){

        if(parseUser.get("profileName") == null){

            editTextPName.setText("");
        }
        else {
            editTextPName.setText(parseUser.get("profileName").toString());
        }


        if(parseUser.get("profileBio") == null){

            editTextPBio.setText("");
        }
        else {
            editTextPBio.setText(parseUser.get("profileBio").toString());
        }

        if(parseUser.get("profileProfession") == null){

            editTextPProfession.setText("");
        }
        else {
            editTextPProfession.setText(parseUser.get("profileProfession").toString());
        }

        if(parseUser.get("profileHobbies") == null){

            editTextPHobbies.setText("");
        }
        else {
            editTextPHobbies.setText(parseUser.get("profileHobbies").toString());
        }

        if(parseUser.get("profileFavSport") == null){

            editTextPFavSports.setText("");
        }
        else {
            editTextPFavSports.setText(parseUser.get("profileFavSport").toString());
        }
    }
}