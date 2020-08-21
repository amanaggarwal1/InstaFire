package com.amanaggarwal1.instafire.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.models.User;
import com.amanaggarwal1.instafire.models.UserAccountSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";
    private static final String defaultDescription = "Your bio here";
    private static final String defaultWebsite = "Add your website here";
    private static final long defaultPhoneNumber = 91;
    private static final String defaultProfilePhoto = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Linearicons_user.svg/1024px-Linearicons_user.svg.png";
    private static final long defaultPosts = 0;
    private static final long defaultFollowers = 0;
    private static final long defaultFollowing = 0;

    private FirebaseAuth mAuth;
    private Context context;
    private String userID;
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public FirebaseMethods(Context context){
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        if(mAuth.getCurrentUser() != null)
            userID = mAuth.getCurrentUser().getUid();

        Log.d(TAG, "FirebaseMethods: current user's ID : " + userID) ;
    }

    public void addNewUser(String username, String displayName, String email){
        User user = new User(username, email, defaultPhoneNumber, userID);
        myRef.child(context.getString(R.string.dbname_users))
                .child(userID)
                .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(username, displayName,
                defaultDescription, defaultWebsite, defaultProfilePhoto, defaultPosts,
                defaultFollowers, defaultFollowing);

        myRef.child(context.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .setValue(settings);
    }

    public void sendVerificationEmail(){
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null)
            return;

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(context, "Couldn't send email, try again later", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public String generateUsername(String username, DataSnapshot snapshot){

        username = username.toLowerCase();
        username = StringManipulation.condenseUsername(username);

        if(ifUsernameExists(username, snapshot)){
            String append = myRef.push().getKey().substring(4,11);
            Log.d(TAG, "onDataChange: username already exists, so appending " + append + " to username");
            username = username + "_" + append;
        }else{
            Log.d(TAG, "generateUsername: username available");
        }

        Log.d(TAG, "generateUsername: new username : " + username);

        return username;
    }

    public boolean ifUsernameExists(String username, DataSnapshot dataSnapshot){
        Log.d(TAG, "ifUsernameExists: checking if " + username + " already exists");

        User user = new User();

        for(DataSnapshot ds : dataSnapshot.child(context.getString(R.string.dbname_users)).getChildren()){
            user.setUsername(ds.getValue(User.class).getUsername());
            Log.d(TAG, "ifUsernameExists: username : " + ds);
            if(user.getUsername().equals(username))
                return true;
        }

        return false;
    }
}
