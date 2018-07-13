package com.dandan.love.config;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/12 下午11:45
 * Description:
 */

@GlideModule
public class GlideModuleConfig extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
        //        .setMemoryCacheScreens(2)
        //        .build();
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));
        int diskCacheSizeBytes = 1024 * 1024 * 100;  //100 MB
        builder.setDiskCache(
                new ExternalCacheDiskCacheFactory(context, FileConfig.GLIDE_IMAGE_CACHE, diskCacheSizeBytes));
    }
}
