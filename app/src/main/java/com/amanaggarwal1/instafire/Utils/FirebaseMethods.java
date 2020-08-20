package com.amanaggarwal1.instafire.Utils;

import android.util.Log;

import com.amanaggarwal1.instafire.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    private FirebaseAuth mAuth;
    private String userID;

    public FirebaseMethods(){
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() != null)
            userID = mAuth.getCurrentUser().getUid();
    }

    public boolean ifUsernameExists(String username, DataSnapshot dataSnapshot){
        Log.d(TAG, "ifUsernameExists: checking if " + username + " already exists");

        username = StringManipulation.removeSpaces(username).toLowerCase();

        User user = new User();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Log.d(TAG, "ifUsernameExists: data snapshot : " + ds);
            user.setUsername(ds.getValue(User.class).getUsername());
            if(user.getUsername().equals(StringManipulation.condenseUsername(username)))
                return true;
        }

        return false;
    }
}
