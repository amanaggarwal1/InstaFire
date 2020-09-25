package com.amanaggarwal1.instafire.profile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.BottomNavigationViewHelper;
import com.amanaggarwal1.instafire.Utils.FirebaseMethods;
import com.amanaggarwal1.instafire.models.UserSettings;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    private static final String TAG = "ProfileFragment";

    private TextView postsTV, followersTV, followingTV, usernameTV, displayNameTV, descriptionTV, websiteTV, editProfileTV;
    private BottomNavigationView bottomNavigationView;
    private CircleImageView profilePhoto;
    private ProgressBar progressBar;
    private GridView gridView;
    private androidx.appcompat.widget.Toolbar toolbar;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseMethods firebaseMethods;

    private UserSettings settings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        defineWidgets(view);

        editProfileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
                intent.putExtra("calling_method", "editProfile");
                startActivity(intent);
            }
        });

        BottomNavigationViewHelper.enableNavigation(getActivity(), bottomNavigationView, BottomNavigationViewHelper.ACTIVITY_NUMBER_PROFILE);
        setupToolbar();
        fetchDataFromDatabase();

        return view;
    }


    private void defineWidgets(View view) {

        postsTV = view.findViewById(R.id.posts_number_tv);
        followersTV = view.findViewById(R.id.followers_number_tv);
        followingTV = view.findViewById(R.id.following_number_tv);
        usernameTV = view.findViewById(R.id.profile_toolbar_username);
        displayNameTV = view.findViewById(R.id.profile_display_name);
        descriptionTV = view.findViewById(R.id.profile_description);
        websiteTV = view.findViewById(R.id.profile_website);
        editProfileTV = view.findViewById(R.id.profile_edit_profile_tv);

        toolbar = view.findViewById(R.id.profile_toolbar);
        bottomNavigationView = view.findViewById(R.id.bottom_nav_view_bar);
        profilePhoto = view.findViewById(R.id.profile_photo);
        gridView = view.findViewById(R.id.profile_grid_view);
        progressBar = view.findViewById(R.id.profile_progress_bar);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
        firebaseMethods = new FirebaseMethods(getActivity());
    }

    private void setupToolbar(){
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile_menu :
                        Intent intent = new Intent(getActivity(), AccountSettingsActivity.class);
                        startActivity(intent);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void fetchDataFromDatabase(){
        progressBar.setVisibility(View.VISIBLE);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                settings =  firebaseMethods.getUserSettings(snapshot);
                Log.d(TAG, "onDataChange: settings : data updated" + settings.toString());
                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Couldn't reload data, please try later", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void updateUI() {
        usernameTV.setText(settings.getUserAccountSettings().getUsername());
        displayNameTV.setText(settings.getUserAccountSettings().getDisplay_name());
        descriptionTV.setText(settings.getUserAccountSettings().getDescription());
        websiteTV.setText(settings.getUserAccountSettings().getWebsite());
        postsTV.setText(String.valueOf(settings.getUserAccountSettings().getPosts()));
        followersTV.setText(String.valueOf(settings.getUserAccountSettings().getFollowers()));
        followingTV.setText(String.valueOf(settings.getUserAccountSettings().getFollowing()));
        Glide.with(getActivity())
                .load(settings.getUserAccountSettings().getProfile_photo())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(profilePhoto);
    }
}
