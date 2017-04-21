package com.tim.googlemap2.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tim.googlemap2.R;
import com.tim.googlemap2.adapters.ListOfUsersAdapter;
import com.tim.googlemap2.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListOfUsers extends AppCompatActivity {

    final String TAG = "ListOfUsers";

    private DatabaseReference mUserReference;
    ListView mListView;

    //a list to store all the artist from firebase database
    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        mUserReference = FirebaseDatabase.getInstance().getReference().child("users");
        mListView = (ListView) findViewById(R.id.listview);

        //list to store artists
        users = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mUserReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous user list
                users.clear();

                //iterating through all the nodes
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    //getting user
                    User user = userSnapshot.getValue(User.class);
                    //adding user to the list
                    users.add(user);
                }

                //creating adapter
                ListOfUsersAdapter listOfUsersAdapter = new ListOfUsersAdapter(ListOfUsers.this, users);

                //attaching adapter to the listview
                mListView.setAdapter(listOfUsersAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: ", databaseError.toException());
                Toast.makeText(ListOfUsers.this, "Failed to load User list.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
