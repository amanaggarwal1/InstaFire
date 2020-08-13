package com.amanaggarwal1.instafire.models;
import androidx.annotation.NonNull;
import com.google.firebase.firestore.PropertyName;

public class Post {
    public String description = "User";
    @PropertyName("creation_time_ms")
    public long creationTimeMS = 0;
    @PropertyName("image_url")
    public String imageUrl = "";
    public User user;
}
