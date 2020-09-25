package com.amanaggarwal1.instafire.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.models.UserSettings;
import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";
    private androidx.appcompat.widget.Toolbar toolbar;



    //widgets
    private CircleImageView profilePhoto;
    private TextView changeProfilePhotoTV;
    private EditText usernameET, displayNameET, descriptionET, websiteET;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        setUpWidgets(view);
        return view;

    }

    private void setupToolbar() {
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit_profile_save_button :
                        getActivity().finish();
                        return true;
                    case R.id.edit_profile_cancel_button :
                        getActivity().finish();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    private void setUpWidgets(View view){
        toolbar = view.findViewById(R.id.profile_toolbar);
        profilePhoto = view.findViewById(R.id.profile_photo);
        changeProfilePhotoTV = view.findViewById(R.id.change_profile_photo_tv);
        usernameET = view.findViewById(R.id.edit_profile_username);
        displayNameET = view.findViewById(R.id.edit_profile_display_name);
        descriptionET = view.findViewById(R.id.edit_profile_description);
        websiteET = view.findViewById(R.id.edit_profile_website);

        setupToolbar();
        setProfilePhoto();


    }

    private void setProfilePhoto() {
        String imageURL = "https://images.idgesg.net/images/article/2017/08/android_robot_logo_by_ornecolorada_cc0_via_pixabay1904852_wide-100732483-large.jpg";
        //UniversalImageLoader.setImage(imageURL, profilePhoto, null, "");
        Glide.with(getActivity()).load(imageURL).into(profilePhoto);
    }
}
