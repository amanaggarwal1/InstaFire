package com.amanaggarwal1.instafire;

import android.app.Activity;
import android.util.DisplayMetrics;
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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Post> posts;
    Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader, txtRelativeTime;
        public ImageView imageView;

        public ViewHolder(View v) {
            super(v);
            txtHeader = v.findViewById(R.id.postOwnerTV);
            txtRelativeTime = v.findViewById(R.id.postRelativeTimeTV);
            imageView =  v.findViewById(R.id.imageView);

            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width);
            imageView.setLayoutParams(params);
        }
    }

    public void add(int position, Post item) {
        posts.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        posts.remove(position);
        notifyItemRemoved(position);
    }


    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Activity activity, List<Post> myDataset) {
        posts = myDataset;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM, yyyy");

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Post post = posts.get(position);
        holder.txtHeader.setText(post.user.username);
        holder.txtRelativeTime.setText(simpleDateFormat.format(new Date(post.creationTimeMS)));
        Glide.with(activity).load(post.imageUrl).into(holder.imageView);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return posts.size();
    }

}