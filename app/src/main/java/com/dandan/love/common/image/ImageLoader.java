package com.dandan.love.common.image;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.dandan.love.App;
import com.dandan.love.R;
import com.dandan.love.config.GlideApp;
import com.dandan.love.config.GlideRequest;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/3 下午3:40
 * Description:
 */
public class ImageLoader {
    public static final int DEFAULT_TYPE = 0;
    public static final int CIRCLE_TYPE = 1;

    public static void loadImageCircleIcon(final String imageUrl, final ImageView imageView) {
        loadImage(imageUrl, R.drawable.default_user_icon, imageView, CIRCLE_TYPE);
    }

    public static void loadImage(final String imageUrl, final ImageView imageView) {
        loadImage(imageUrl, -1, imageView, DEFAULT_TYPE);
    }

    public static void loadImage(final String imageUrl, final ImageView imageView, int type) {
        loadImage(imageUrl, -1, imageView, type);
    }


    public static void loadImage(final String imageUrl, int errorImageId, final ImageView imageView, int type) {
        loadImage(App.application, imageUrl, errorImageId, imageView, type);
    }


    public static void loadImage(Context context, final String imageUrl, int errorImageId, final ImageView imageView, int type) {
        GlideRequest<Drawable> glideRequests = GlideApp.with(context).load(imageUrl);
        if (DEFAULT_TYPE == type) {
            glideRequests.into(imageView);
        } else if (CIRCLE_TYPE == type) {
            glideRequests.transform(new GlideCircleTransform(context)).into(imageView);
        }
    }
}
