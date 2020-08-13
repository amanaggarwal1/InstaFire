package com.amanaggarwal1.instafire;

import android.app.Activity;
import android.text.format.DateUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amanaggarwal1.instafire.models.Post;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<Post> posts;
    Activity activity;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtHeader, txtRelativeTime, txtDescription;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            txtHeader = v.findViewById(R.id.postOwnerTV);
            txtRelativeTime = v.findViewById(R.id.postRelativeTimeTV);
            imageView =  v.findViewById(R.id.imageView);
            txtDescription = v.findViewById(R.id.postDescriptionTV);

            Display display = activity.getDisplay();
            int width = display.getWidth();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width);
            imageView.setLayoutParams(params);
        }
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PostsAdapter(Activity activity, List<Post> myDataset) {
        posts = myDataset;
        this.activity = activity;
    }

    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Post post = posts.get(position);
        holder.txtHeader.setText(post.user.username);
        holder.txtRelativeTime.setText(DateUtils.getRelativeTimeSpanString(post.creationTimeMS));
        Glide.with(activity).load(post.imageUrl).into(holder.imageView);
        holder.txtDescription.setText(post.description);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return posts.size();
    }

}