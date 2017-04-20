package com.tim.googlemap2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfUsers extends AppCompatActivity {

    final String TAG = "ListOfUsers";

    private DatabaseReference mUserReference;
    private ValueEventListener mUserListListener;
    ArrayList<String> usernamelist = new ArrayList<>();
    ArrayAdapter arrayAdapter;

    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        mUserReference = FirebaseDatabase.getInstance().getReference().child("users");
        onStart();
        mListView = (ListView) findViewById(R.id.listview);

//        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
//                ListOfUsers.this,
//                String.class,
//                android.R.layout.simple_list_item_1,
//                mUserReference) {
//            @Override
//            protected void populateView(View v, String model, int position) {
//                TextView textView = (TextView) v.findViewById(android.R.id.text1);
//                textView.setText(model);
//            }
//        };
//        mListView.setAdapter(firebaseListAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();final ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usernamelist = new ArrayList<>((ArrayList) dataSnapshot.getValue());
                Log.i(TAG, "onDataChange: "+ usernamelist.toString());
                arrayAdapter = new ArrayAdapter(ListOfUsers.this,android.R.layout.simple_list_item_1, usernamelist);
                mListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "onCancelled: ",databaseError.toException());
                Toast.makeText(ListOfUsers.this, "Failed to load User list.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        mUserReference.addValueEventListener(userListener);
        mUserListListener = userListener;
    }
}
