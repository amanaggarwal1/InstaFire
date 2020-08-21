package com.amanaggarwal1.instafire.profile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.BottomNavigationViewHelper;
import com.amanaggarwal1.instafire.Utils.GridImageAdapter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: ");

        init();

//        tempGridImages();
    }

    private void init(){
        Log.d(TAG, "init: inflating " + getString(R.string.profile_fragment));

        ProfileFragment fragment = new ProfileFragment();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);

        fragmentTransaction.addToBackStack(getString(R.string.profile_fragment));
        fragmentTransaction.commit();
    }

//
//    private void tempGridImages(){
//        ArrayList<String> images = new ArrayList<>();
//
//        images.add("https://i.pinimg.com/originals/ca/76/0b/ca760b70976b52578da88e06973af542.jpg");
//        images.add("https://media.gettyimages.com/photos/global-communication-network-picture-id1038383026?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/dandelion-seed-picture-id157681198?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/beautiful-young-women-on-the-nature-picture-id944634910?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/young-business-boys-in-suits-race-toy-cars-picture-id507282334?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/mother-and-son-in-bedroom-spend-some-quality-time-picture-id1225664036?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/railroad-track-in-india-picture-id980949458?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/modern-father-taking-care-of-his-children-at-home-picture-id1171345492?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/owner-playing-with-dog-making-him-fly-above-the-head-picture-id923051232?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/girls-playing-soccer-during-an-evening-football-match-picture-id1201613805?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/information-concept-picture-id507993976?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/fairy-dancing-in-field-picture-id533433852?s=2048x2048");
//
//
//        images.add("https://i.pinimg.com/originals/ca/76/0b/ca760b70976b52578da88e06973af542.jpg");
//        images.add("https://media.gettyimages.com/photos/global-communication-network-picture-id1038383026?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/dandelion-seed-picture-id157681198?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/beautiful-young-women-on-the-nature-picture-id944634910?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/young-business-boys-in-suits-race-toy-cars-picture-id507282334?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/mother-and-son-in-bedroom-spend-some-quality-time-picture-id1225664036?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/railroad-track-in-india-picture-id980949458?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/modern-father-taking-care-of-his-children-at-home-picture-id1171345492?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/owner-playing-with-dog-making-him-fly-above-the-head-picture-id923051232?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/girls-playing-soccer-during-an-evening-football-match-picture-id1201613805?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/information-concept-picture-id507993976?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/fairy-dancing-in-field-picture-id533433852?s=2048x2048");
//        images.add("https://i.pinimg.com/originals/ca/76/0b/ca760b70976b52578da88e06973af542.jpg");
//        images.add("https://media.gettyimages.com/photos/global-communication-network-picture-id1038383026?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/dandelion-seed-picture-id157681198?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/beautiful-young-women-on-the-nature-picture-id944634910?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/young-business-boys-in-suits-race-toy-cars-picture-id507282334?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/mother-and-son-in-bedroom-spend-some-quality-time-picture-id1225664036?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/railroad-track-in-india-picture-id980949458?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/modern-father-taking-care-of-his-children-at-home-picture-id1171345492?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/owner-playing-with-dog-making-him-fly-above-the-head-picture-id923051232?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/girls-playing-soccer-during-an-evening-football-match-picture-id1201613805?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/information-concept-picture-id507993976?s=2048x2048");
//        images.add("https://media.gettyimages.com/photos/fairy-dancing-in-field-picture-id533433852?s=2048x2048");
//
//
//        setupImageGridView(images);
//    }
//
//    private void setupImageGridView(ArrayList<String> imageURLs){
//        GridImageAdapter adapter = new GridImageAdapter(this, R.layout.item_grid_image, imageURLs);
//        gridView.setAdapter(adapter);
//    }
//


}
