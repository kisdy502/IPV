package cn.fm.home;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/2/14.
 */

public class NormalItemView extends RelativeLayout {

    private ImageView imgPoster;
    private TextView tvTitle;

    RequestOptions myOptions = new RequestOptions().transform(new
            GlideRoundTransform(getContext())).placeholder(R.drawable.launch_block_default_icon);


    public NormalItemView(Context context) {
        this(context, null);
    }

    public NormalItemView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NormalItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        inflate(getContext(), R.layout.item_home_default, this);
        imgPoster = findViewById(R.id.poster_url);
        tvTitle = findViewById(R.id.title);
    }


    public void setPosterUrl(String url) {
        Glide.with(getContext()).load(url).apply(myOptions).into(imgPoster);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setItemSize(int[] size) {
        setLayoutParams(new LayoutParams(size[0], size[1]));
    }

}
