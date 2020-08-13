package com.amanaggarwal1.instafire;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amanaggarwal1.instafire.models.Post;
import com.amanaggarwal1.instafire.models.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class PostsActivity extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        db = FirebaseFirestore.getInstance();

        CollectionReference pr = db.collection("posts");
        pr.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null || value == null){
                    Log.d("LOGCAT", "Error in getting data");
                    return;
                }

                List<Post> postList = value.toObjects(Post.class);

                for(Post post : postList){
                    Log.d("LOGCAT", "to objects,  desc = " + post.description + " time = " + post.creation_time_ms + " url = " + post.image_url + " username = " + post.user.username + " age = " + post.user.age );                }
            }
        });

        /*
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("LOGCAT", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w("LOGCAT", "Error getting documents.", task.getException());
                        }
                    }
                });

         */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_posts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         if(item.getItemId() == R.id.menu_profile){
             Intent intent = new Intent(this, ProfileActivity.class);
             startActivity(intent);
         }
        return super.onOptionsItemSelected(item);
    }
}