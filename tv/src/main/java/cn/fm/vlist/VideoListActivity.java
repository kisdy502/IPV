package cn.fm.vlist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/1/30.
 */

public class VideoListActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
    }

}
