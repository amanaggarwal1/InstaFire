package com.amanaggarwal1.instafire;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.amanaggarwal1.instafire.models.Post;
import com.amanaggarwal1.instafire.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private PostsAdapter adapter;
    private  RecyclerView recyclerView;
    private User signedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        setUpAdapter();
        fetchPostsFromFireStore();
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
             intent.putExtra("USERNAME", signedInUser.username);
             startActivity(intent);
         }
        return super.onOptionsItemSelected(item);
    }

    private void setUpAdapter(){
        recyclerView = findViewById(R.id.postsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new PostsAdapter(this, new ArrayList<Post>());
        recyclerView.setAdapter(adapter);
    }

    private void fetchPostsFromFireStore() {
        db = FirebaseFirestore.getInstance();

        getCurrentUser();

        String username = getIntent().getStringExtra("USERNAME");
        Query postsReference;
        if(username == null) {
            postsReference = db.collection("posts")
                    .limit(20)
                    .orderBy("creation_time_ms", Query.Direction.DESCENDING);
        }else{
            getSupportActionBar().setTitle(username);
            postsReference = db.collection("posts")
                    .limit(20)
                    .orderBy("creation_time_ms", Query.Direction.DESCENDING)
                    .whereEqualTo("user.username", username);
        }



        postsReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null || value == null){
                    Log.d("LOGCAT", "Error in getting data");
                    return;
                }

                List<Post> postList = value.toObjects(Post.class);
                adapter.setPosts(postList);
                for(Post post : postList){
                    Log.d("LOGCAT", "to objects,  desc = " + post.description + " time = " + post.creationTimeMS + " url = " + post.imageUrl + " username = " + post.user.username + " age = " + post.user.age );                }
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

    private void getCurrentUser() {
        db.collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        signedInUser = documentSnapshot.toObject(User.class);
                        Log.d("LOGCAT", "user signed in = " + signedInUser.username);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("LOGCAT", "Something went wrong in fetching current user " + e.getMessage());
            }
        });


    }
}