package com.example.instagramc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class UsersTab extends Fragment {

    private ListView listViewUsersList;
    private View view;
    private ArrayList arrayList;
    private ArrayAdapter arrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_users_tab, container, false);

        listViewUsersList = view.findViewById(R.id.userListView);

        arrayList = new ArrayList();

        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,arrayList);

        final TextView textViewLoading = view.findViewById(R.id.loadingTxtView);

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {

                if(e == null){

                    if(users.size() >0){

                        for (ParseUser user : users){

                            arrayList.add(user.getUsername());

                        }

                        listViewUsersList.setAdapter(arrayAdapter);
                        textViewLoading.animate().alpha(0).setDuration(2000);
                        listViewUsersList.setVisibility(View.VISIBLE);

                    }
                }
            }
        });


        return view ;
    }
}