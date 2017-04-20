package com.tim.googlemap2;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Panzhiev on 19.04.2017.
 */

public class App extends Application {

    public String TAG = "MY_TAG_APP";

    @Override
    public void onCreate() {
        super.onCreate();

        /* Previous version*/
        Firebase.setAndroidContext(this);

        /* New version*/
        if (!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}