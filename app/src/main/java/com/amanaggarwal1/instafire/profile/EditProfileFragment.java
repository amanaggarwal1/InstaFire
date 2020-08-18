package com.amanaggarwal1.instafire.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.amanaggarwal1.instafire.R;
import com.amanaggarwal1.instafire.Utils.UniversalImageLoader;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    private static final String TAG = "EditProfileFragment";
    private CircleImageView profilePhoto;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        profilePhoto = view.findViewById(R.id.profile_photo);
        toolbar = view.findViewById(R.id.profile_toolbar);

        setupToolbar();
        initiateImageLoader();
        setProfilePhoto();

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

    private void initiateImageLoader(){
        UniversalImageLoader loader = new UniversalImageLoader(getActivity());
        ImageLoader.getInstance().init(loader.getConfig());
    }

    private void setProfilePhoto() {
        String imageURL = "https://images.idgesg.net/images/article/2017/08/android_robot_logo_by_ornecolorada_cc0_via_pixabay1904852_wide-100732483-large.jpg";
        UniversalImageLoader.setImage(imageURL, profilePhoto, null, "");
       // Glide.with(getActivity()).load(imageURL).into(profilePhoto);
    }
}
