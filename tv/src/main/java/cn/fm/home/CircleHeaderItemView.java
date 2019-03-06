package cn.fm.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import cn.fm.ipv.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2019/2/15.
 */

public class CircleHeaderItemView extends RelativeLayout {

    private ImageView backgroundView;
    private ImageView headerView;

    RequestOptions mRequestOptions = RequestOptions.circleCropTransform().diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true).placeholder(R.drawable.launch_block_default_icon);

    public CircleHeaderItemView(Context context) {
        this(context, null);
    }

    public CircleHeaderItemView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CircleHeaderItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        inflate(getContext(), R.layout.item_home_cirle_header, this);
        headerView = findViewById(R.id.poster_url);
        backgroundView = findViewById(R.id.background_url);
    }

    public void setItemSize(int[] size) {
        setLayoutParams(new LayoutParams(size[0], size[1]));
    }

    public void setBackgroundViewSize(int[] size) {
        backgroundView.setLayoutParams(new LayoutParams(size[0], size[1]));
    }

    public void setHeaderViewSize(int[] size) {
        headerView.setLayoutParams(new LayoutParams(size[0], size[1]));
    }


    public void setBackgroundUrl(String backgroundurl) {

        Glide.with(getContext()).load(backgroundurl).apply(mRequestOptions).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                backgroundView.setImageDrawable(resource);
            }
        });

    }

    public void setHeaderUrl(String foregroundUrl) {
        Glide.with(getContext()).load(foregroundUrl).apply(mRequestOptions).into(new SimpleTarget<Drawable>() {

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                headerView.setImageDrawable(resource);
            }
        });
    }
}
