
package cn.fm.vlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.VerticalGridView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.fm.ipv.R;

public class VideoListFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private VerticalGridView mVideoList;

    private List<ContentBean> contentList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mVideoList = view.findViewById(R.id.video_list);
        initData();
        VideoListAdapter adapter = new VideoListAdapter(getContext(), contentList);
        mVideoList.setAdapter(adapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    private void initData() {
        ContentBean performBean = new ContentBean();
        List<PerforInfo> performList = new ArrayList<>();
        performList.add(new PerforInfo("赵又廷", "http://gt.beevideo" +
                ".tv:12000/perform/155/1dbfee2a841d4b25.jpg"));
        performList.add(new PerforInfo("冯绍峰", "http://gt.beevideo" +
                ".tv:12000/perform/155/1dbfee2a841d4b25.jpg"));
        performList.add(new PerforInfo("林更新", "http://gt.beevideo" +
                ".tv:12000/perform/155/1dbfee2a841d4b25.jpg"));
        performList.add(new PerforInfo("沈腾", "http://gt.beevideo" +
                ".tv:12000/perform/155/1dbfee2a841d4b25.jpg"));
        performList.add(new PerforInfo("肖央", "http://gt.beevideo" +
                ".tv:12000/perform/155/1dbfee2a841d4b25.jpg"));
        performBean.setItemViewType("0");
        performBean.setDataList(performList);


        ContentBean videoBean = new ContentBean();
        List<VideoInfo> videoList = new ArrayList<>();
        videoList.add(new VideoInfo("天气预爆", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 5));

        videoList.add(new VideoInfo("毒液", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 8));

        videoList.add(new VideoInfo("印度暴徒", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 6));

        videoList.add(new VideoInfo("中国蓝盔", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 4));

        videoList.add(new VideoInfo("刺杀风云", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 2));

        videoList.add(new VideoInfo("大哥的传说", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 9));

        videoList.add(new VideoInfo("蓝月传", "http://gt.beevideo" +
                ".tv:12000/video/760/8567391ce6a64b40.jpg", 8));


        videoBean.setItemViewType("1");
        videoBean.setDataList(videoList);


        ContentBean posterBean = new ContentBean();

        List<PosterInfo> posterList = new ArrayList<>();

        posterList.add(new PosterInfo("痞子英雄打天下", "http://gt.beevideo" +
                ".tv:12000/video/4kgarden/509/a35cbc087f874aeb.jpg"));

        posterList.add(new PosterInfo("赌神再战江湖", "http://gt.beevideo" +
                ".tv:12000/video/4kgarden/509/a35cbc087f874aeb.jpg"));

        posterBean.setItemViewType("2");
        posterBean.setDataList(posterList);

        contentList.add(posterBean);
        contentList.add(performBean);
        contentList.add(videoBean);
    }


}
