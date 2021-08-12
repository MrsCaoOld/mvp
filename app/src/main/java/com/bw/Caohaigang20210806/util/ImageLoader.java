package com.bw.Caohaigang20210806.util;

import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

public class ImageLoader {
    public ImageLoader() {
    }
    public static ImageLoader imageLoader;

    public static ImageLoader getImageLoader() {
        if(imageLoader!=null){
            synchronized (ImageLoader.class){
                if(imageLoader!=null){
                    imageLoader = new ImageLoader();
                }
            }
        }
        return imageLoader;
    }
    //加载图片
    public static void imageLoader(String url, ImageView imageView){
        Glide.with(Utils.getApp()).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
    //加载圆角图片
    public static void imageRoundedLoader(String url, int i,ImageView imageView){
        Glide.with(Utils.getApp()).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new RoundedCorners(i))
                .into(imageView);
    }
    //加载圆形图片
    public static void imageCircleLoader(String url, ImageView imageView){
        Glide.with(Utils.getApp()).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .transform(new CircleCrop())
                .into(imageView);
    }
}
