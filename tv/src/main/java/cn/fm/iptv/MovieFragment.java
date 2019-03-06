package cn.fm.iptv;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.fengmang.libui.flying.ColorFlyingFrameView;
import cn.fm.home.ContentBlock;
import cn.fm.home.ContentGroup;
import cn.fm.home.HomeListAdapter;
import cn.fm.home.ImgeUrlList;
import cn.fm.home.ItemHelper;
import cn.fm.home.NestFMVerticalGridView;
import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/2/20.
 */
public class MovieFragment extends Fragment {

    private static final String TAG = "MovieFragment";

    private NestFMVerticalGridView mHomeList;
    private ColorFlyingFrameView flyingFrameView;
    private List<ContentGroup> contentList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        flyingFrameView = ((FiltrateMoviceActivity) getActivity()).getFlyingFrameView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHomeList = view.findViewById(R.id.home_list);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,
                false);
        mHomeList.setLayoutManager(lm);
        initData();
        HomeListAdapter adapter = new HomeListAdapter(getContext(), contentList, flyingFrameView);
        mHomeList.setAdapter(adapter);
        mHomeList.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("fragment", "is focus:" + hasFocus);
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("fragment", "onDetach");
    }


    private void initData() {
        ContentGroup contentGroupL3 = new ContentGroup();
        contentGroupL3.setOrientation(ContentGroup.VERTICAL);
        contentGroupL3.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL3.setSpanCount(3);
        contentGroupL3.setBlockType(ItemHelper.BLOCK_TYPE_3_260);

        List<ContentBlock> contentBlockListL3 = new ArrayList<>();
        contentBlockListL3.add(new ContentBlock("逆流遇上你", ImgeUrlList.L3_260[0]));
        contentBlockListL3.add(new ContentBlock("独孤皇后", ImgeUrlList.L3_260[1]));
        contentBlockListL3.add(new ContentBlock("知否", ImgeUrlList.L3_260[2]));

        contentBlockListL3.add(new ContentBlock("独孤皇后", ImgeUrlList.L3_260[1]));
        contentBlockListL3.add(new ContentBlock("知否", ImgeUrlList.L3_260[2]));
        contentBlockListL3.add(new ContentBlock("逆流遇上你", ImgeUrlList.L3_260[0]));
        contentGroupL3.setDataList(contentBlockListL3);


        ContentGroup contentGroupL4 = new ContentGroup();
        contentGroupL4.setOrientation(ContentGroup.HORIZONTAL);
        contentGroupL4.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL4.setSpanCount(1);
        contentGroupL4.setBlockType(ItemHelper.BLOCK_TYPE_4_204);
        List<ContentBlock> contentBlockListL4 = new ArrayList<>();
        contentBlockListL4.add(new ContentBlock("今日资讯", ImgeUrlList.L4_204[0]));
        contentBlockListL4.add(new ContentBlock("BesTV", ImgeUrlList.L4_204[1]));
        contentBlockListL4.add(new ContentBlock("奇异果", ImgeUrlList.L4_204[2]));
        contentBlockListL4.add(new ContentBlock("今日资讯", ImgeUrlList.L4_204[0]));
        contentGroupL4.setDataList(contentBlockListL4);

        ContentGroup contentGroupL1_01 = new ContentGroup();
        contentGroupL1_01.setOrientation(ContentGroup.VERTICAL);
        contentGroupL1_01.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL1_01.setSpanCount(1);
        contentGroupL1_01.setBlockType(ItemHelper.BLOCK_TYPE_1_145);
        List<ContentBlock> contentBlockListL0 = new ArrayList<>();
        contentBlockListL0.add(new ContentBlock("开通Vip", ImgeUrlList.L1_102[0]));
        contentGroupL1_01.setDataList(contentBlockListL0);


        ContentGroup contentGroupL3_01 = new ContentGroup();
        contentGroupL3_01.setOrientation(ContentGroup.HORIZONTAL);
        contentGroupL3_01.setSpanCount(2);
        contentGroupL3_01.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL3_01.setBlockType(ItemHelper.BLOCK_TYPE_3_330);
        List<ContentBlock> contentBlockListL3_01 = new ArrayList<>();
        contentBlockListL3_01.add(new ContentBlock("探险日记", ImgeUrlList.L3_702[0], 2));
        contentBlockListL3_01.add(new ContentBlock("王牌对王牌", ImgeUrlList.L3_355[0]));
        contentBlockListL3_01.add(new ContentBlock("国宝奇旅", ImgeUrlList.L3_355[1]));
        contentBlockListL3_01.add(new ContentBlock("来电狂响", ImgeUrlList.L3_355[2]));
        contentBlockListL3_01.add(new ContentBlock("断片", ImgeUrlList.L3_355[3]));
        contentBlockListL3_01.add(new ContentBlock("王牌对王牌", ImgeUrlList.L3_355[0]));
        contentBlockListL3_01.add(new ContentBlock("国宝奇旅", ImgeUrlList.L3_355[1]));
        contentBlockListL3_01.add(new ContentBlock("来电狂响", ImgeUrlList.L3_355[2]));
        contentBlockListL3_01.add(new ContentBlock("断片", ImgeUrlList.L3_355[3]));

        contentGroupL3_01.setDataList(contentBlockListL3_01);


        ContentGroup contentGroupL6_01 = new ContentGroup();
        contentGroupL6_01.setOrientation(ContentGroup.VERTICAL);
        contentGroupL6_01.setSpanCount(6);
        contentGroupL6_01.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL6_01.setBlockType(ItemHelper.BLOCK_TYPE_6_344);
        List<ContentBlock> contentBlockListL6_01 = new ArrayList<>();
        contentBlockListL6_01.add(new ContentBlock("监狱风云", ImgeUrlList.L6_344[0]));
        contentBlockListL6_01.add(new ContentBlock("英雄本色2", ImgeUrlList.L6_344[1]));
        contentBlockListL6_01.add(new ContentBlock("杀手之王", ImgeUrlList.L6_344[2]));
        contentBlockListL6_01.add(new ContentBlock("霸王别姬", ImgeUrlList.L6_344[3]));
        contentBlockListL6_01.add(new ContentBlock("东邪西毒", ImgeUrlList.L6_344[4]));
        contentBlockListL6_01.add(new ContentBlock("倚天屠龙记", ImgeUrlList.L6_344[5]));

        contentBlockListL6_01.add(new ContentBlock("英雄本色2", ImgeUrlList.L6_344[1]));
        contentBlockListL6_01.add(new ContentBlock("杀手之王", ImgeUrlList.L6_344[2]));
        contentBlockListL6_01.add(new ContentBlock("监狱风云", ImgeUrlList.L6_344[0]));
        contentBlockListL6_01.add(new ContentBlock("倚天屠龙记", ImgeUrlList.L6_344[5]));
        contentBlockListL6_01.add(new ContentBlock("东邪西毒", ImgeUrlList.L6_344[4]));
        contentBlockListL6_01.add(new ContentBlock("霸王别姬", ImgeUrlList.L6_344[3]));

        contentGroupL6_01.setDataList(contentBlockListL6_01);


        ContentGroup contentGroupL5_01 = new ContentGroup();
        contentGroupL5_01.setOrientation(ContentGroup.HORIZONTAL);
        contentGroupL5_01.setSpanCount(1);
        contentGroupL5_01.setBlockType(ItemHelper.BLOCK_TYPE_5_248);
        contentGroupL5_01.setViewType(ContentGroup.VIEW_TYPE_HEADER);
        List<ContentBlock> contentBlockListL5_01 = new ArrayList<>();
        contentBlockListL5_01.add(new ContentBlock("佩奇", ImgeUrlList.L5_248_F[0],
                ImgeUrlList.L5_224_B[0]
        ));
        contentBlockListL5_01.add(new ContentBlock("汪汪", ImgeUrlList.L5_248_F[1],
                ImgeUrlList.L5_224_B[1]
        ));
        contentBlockListL5_01.add(new ContentBlock("熊大", ImgeUrlList.L5_248_F[2],
                ImgeUrlList.L5_224_B[2]
        ));
        contentBlockListL5_01.add(new ContentBlock("巧虎", ImgeUrlList.L5_248_F[3],
                ImgeUrlList.L5_224_B[3]
        ));
        contentBlockListL5_01.add(new ContentBlock("人气儿歌", ImgeUrlList.L5_248_F[4],
                ImgeUrlList.L5_224_B[4]
        ));
        contentGroupL5_01.setDataList(contentBlockListL5_01);


        ContentGroup contentGroupL1_02 = new ContentGroup();
        contentGroupL1_02.setOrientation(ContentGroup.VERTICAL);
        contentGroupL1_02.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL1_02.setSpanCount(1);
        contentGroupL1_02.setBlockType(ItemHelper.BLOCK_TYPE_1_383);
        List<ContentBlock> contentBlockListL1_02 = new ArrayList<>();
        contentBlockListL1_02.add(new ContentBlock("育儿块", ImgeUrlList.L1_383[0]));
        contentGroupL1_02.setDataList(contentBlockListL1_02);


        ContentGroup contentGroupL4_02 = new ContentGroup();
        contentGroupL4_02.setOrientation(ContentGroup.HORIZONTAL);
        contentGroupL4_02.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        contentGroupL4_02.setSpanCount(1);
        contentGroupL4_02.setBlockType(ItemHelper.BLOCK_TYPE_4_204);
        List<ContentBlock> contentBlockListL4_02 = new ArrayList<>();
        contentBlockListL4_02.add(new ContentBlock("今日资讯2", ImgeUrlList.L4_204[0]));
        contentBlockListL4_02.add(new ContentBlock("BesTV2", ImgeUrlList.L4_204[1]));
        contentBlockListL4_02.add(new ContentBlock("奇异果2", ImgeUrlList.L4_204[2]));
        contentBlockListL4_02.add(new ContentBlock("今日资讯2", ImgeUrlList.L4_204[0]));

        contentGroupL4_02.setDataList(contentBlockListL4_02);


        ContentGroup contentGroupL5_02 = new ContentGroup();
        contentGroupL5_02.setOrientation(ContentGroup.HORIZONTAL);
        contentGroupL5_02.setSpanCount(1);
        contentGroupL5_02.setBlockType(ItemHelper.BLOCK_TYPE_5_248);
        contentGroupL5_02.setViewType(ContentGroup.VIEW_TYPE_HEADER);
        List<ContentBlock> contentBlockListL5_02 = new ArrayList<>();
        contentBlockListL5_02.add(new ContentBlock("佩奇2", ImgeUrlList.L5_248_F[0],
                ImgeUrlList.L5_224_B[0]
        ));
        contentBlockListL5_02.add(new ContentBlock("汪汪2", ImgeUrlList.L5_248_F[1],
                ImgeUrlList.L5_224_B[1]
        ));
        contentBlockListL5_02.add(new ContentBlock("熊大2", ImgeUrlList.L5_248_F[2],
                ImgeUrlList.L5_224_B[2]
        ));
        contentBlockListL5_02.add(new ContentBlock("巧虎2", ImgeUrlList.L5_248_F[3],
                ImgeUrlList.L5_224_B[3]
        ));
        contentBlockListL5_02.add(new ContentBlock("人气儿歌2", ImgeUrlList.L5_248_F[4],
                ImgeUrlList.L5_224_B[4]
        ));

        contentBlockListL5_02.add(new ContentBlock("佩奇2", ImgeUrlList.L5_248_F[0],
                ImgeUrlList.L5_224_B[0]
        ));
        contentBlockListL5_02.add(new ContentBlock("汪汪2", ImgeUrlList.L5_248_F[1],
                ImgeUrlList.L5_224_B[1]
        ));
        contentBlockListL5_02.add(new ContentBlock("熊大2", ImgeUrlList.L5_248_F[2],
                ImgeUrlList.L5_224_B[2]
        ));
        contentBlockListL5_02.add(new ContentBlock("巧虎2", ImgeUrlList.L5_248_F[3],
                ImgeUrlList.L5_224_B[3]
        ));
        contentBlockListL5_02.add(new ContentBlock("人气儿歌2", ImgeUrlList.L5_248_F[4],
                ImgeUrlList.L5_224_B[4]
        ));

        contentGroupL5_02.setDataList(contentBlockListL5_02);

        ContentGroup contentGroupL2_01 = new ContentGroup();
        contentGroupL2_01.setOrientation(ContentGroup.VERTICAL);
        contentGroupL2_01.setSpanCount(2);
        contentGroupL2_01.setBlockType(ItemHelper.BLOCK_TYPE_2_260);
        contentGroupL2_01.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        List<ContentBlock> contentBlockListL2_01 = new ArrayList<>();
        contentBlockListL2_01.add(new ContentBlock("国家宝藏", ImgeUrlList.L2_260[0]));
        contentBlockListL2_01.add(new ContentBlock("姐姐花店", ImgeUrlList.L2_260[1]));
        contentBlockListL2_01.add(new ContentBlock("青春有你", ImgeUrlList.L2_260[2]));
        contentBlockListL2_01.add(new ContentBlock("身临其境", ImgeUrlList.L2_260[3]));

        contentGroupL2_01.setDataList(contentBlockListL2_01);


        ContentGroup contentGroupL6_02 = new ContentGroup();
        contentGroupL6_02.setOrientation(ContentGroup.VERTICAL);
        contentGroupL6_02.setSpanCount(6);
        contentGroupL6_02.setBlockType(ItemHelper.BLOCK_TYPE_6_360);
        contentGroupL6_02.setViewType(ContentGroup.VIEW_TYPE_NORAML);
        List<ContentBlock> contentBlockListL6_02 = new ArrayList<>();
        contentBlockListL6_02.add(new ContentBlock("武林怪兽", ImgeUrlList.L6_C4_360[0], 4));
        contentBlockListL6_02.add(new ContentBlock("我不是药神", ImgeUrlList.L6_C2_360[0], 2));
        contentBlockListL6_02.add(new ContentBlock("影", ImgeUrlList.L6_360[0]));
        contentBlockListL6_02.add(new ContentBlock("西虹市首富", ImgeUrlList.L6_360[1]));
        contentBlockListL6_02.add(new ContentBlock("22年后的自白", ImgeUrlList.L6_360[2]));
        contentBlockListL6_02.add(new ContentBlock("滴答屋", ImgeUrlList.L6_360[3]));
        contentBlockListL6_02.add(new ContentBlock("无名之辈", ImgeUrlList.L6_360[4]));
        contentBlockListL6_02.add(new ContentBlock("龙猫", ImgeUrlList.L6_360[5]));

        contentGroupL6_02.setDataList(contentBlockListL6_02);


        ContentGroup headerGroup1 = new ContentGroup("VIP购买");
        ContentGroup headerGroup2 = new ContentGroup("节日必看大片");
        ContentGroup headerGroup3 = new ContentGroup("国产经典");
        ContentGroup headerGroup4 = new ContentGroup("国产搞笑");
        ContentGroup headerGroup5 = new ContentGroup("儿童节目");
        ContentGroup headerGroup6 = new ContentGroup("TVB经典");

        contentList.add(headerGroup1);
        contentList.add(contentGroupL1_01);

        contentList.add(headerGroup3);
        contentList.add(contentGroupL3);

        contentList.add(contentGroupL5_02);

        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL4);

        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL5_01);

        contentList.add(contentGroupL2_01);

        contentList.add(headerGroup1);
        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL6_01);

        contentList.add(headerGroup4);
        contentList.add(contentGroupL4);
        contentList.add(contentGroupL3);
        contentList.add(contentGroupL5_01);

        contentList.add(contentGroupL1_02);
        contentList.add(headerGroup6);
        contentList.add(contentGroupL6_02);
        contentList.add(contentGroupL3_01);

        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL2_01);
        contentList.add(contentGroupL4_02);
        contentList.add(headerGroup5);
        contentList.add(contentGroupL5_02);
        contentList.add(contentGroupL6_02);
        contentList.add(contentGroupL3);


        contentList.add(contentGroupL3_01);
        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL2_01);
        contentList.add(contentGroupL4_02);


        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL5_01);
        contentList.add(contentGroupL6_02);
        contentList.add(contentGroupL3);

        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL6_02);
        contentList.add(contentGroupL3_01);
        contentList.add(contentGroupL2_01);
        contentList.add(contentGroupL5_02);

        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL2_01);
        contentList.add(contentGroupL4_02);
        contentList.add(contentGroupL6_02);
        contentList.add(contentGroupL5_02);
        contentList.add(contentGroupL3);


        contentList.add(contentGroupL3_01);
        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL2_01);
        contentList.add(contentGroupL4_02);

        contentList.add(contentGroupL1_02);
        contentList.add(contentGroupL6_02);
        contentList.add(contentGroupL3);
        contentList.add(contentGroupL5_01);

    }


}
