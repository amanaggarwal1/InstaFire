package com.amanaggarwal1.instafire.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.amanaggarwal1.instafire.R;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class UniversalImageLoader {

    private static final int defaultImage = R.drawable.ic_android;
    private Context context;

    public UniversalImageLoader(Context context) {
        this.context = context;
    }

    public ImageLoaderConfiguration getConfig(){
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageForEmptyUri(defaultImage)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300))
                .build();

        return new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(displayImageOptions)
                .memoryCache(new WeakMemoryCache())
                .diskCacheSize(100 * 1024 * 1024)
                .build();

    }


    /**
     * This method can be used to set images which are static.
     * It can't be used if images are being changed in fragment / activity or if they are being
     * used in a list or grid
     * @param imageURL
     * @param image
     * @param progressBar
     * @param appendType
     */
    public static void setImage(String imageURL, ImageView image, final ProgressBar progressBar, String appendType){
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(appendType + imageURL, image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(progressBar != null){
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
