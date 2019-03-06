package cn.fm.iptv;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v17.leanback.widget.BaseGridView;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.OnChildSelectedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.fengmang.libui.flying.ColorFlyingFrameView;
import cn.fm.ipv.R;

public class FiltrateMoviceActivity extends FragmentActivity {

    private Button searchBtn;
    private HorizontalGridView horizontalGridView;

    private TitlerAdapter mTitlerAdapter;
    private List<MoviceTitlerMode> mTitlerList;

    private ColorFlyingFrameView flyingFrameView;

    public ColorFlyingFrameView getFlyingFrameView() {
        return flyingFrameView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrate_movice);

        searchBtn = findViewById(R.id.search_btn);
        horizontalGridView = findViewById(R.id.title_hgridview);
        initFrameView();
        initTitleData();
        initTitle();
        initView();


    }

    private void initTitle() {
        mTitlerAdapter = new TitlerAdapter(this, mTitlerList);
        int left = getResources().getDimensionPixelOffset(R.dimen.size_60);
        horizontalGridView.setPadding(left, 0, 0, 0);
        int spaceTop = 0;
        int spaceRight = getResources().getDimensionPixelOffset(R.dimen.size_20);
        horizontalGridView.addItemDecoration(new SpaceItemDecoration(0, spaceTop, spaceRight, 0));
        horizontalGridView.setAdapter(mTitlerAdapter);
    }

    private void initTitleData() {
        mTitlerList = new ArrayList<MoviceTitlerMode>();
        mTitlerList.add(new MoviceTitlerMode("", "电影"));
        mTitlerList.add(new MoviceTitlerMode("", "电视剧"));
//        mTitlerList.add(new MoviceTitlerMode("", "综艺"));
//        mTitlerList.add(new MoviceTitlerMode("", "动漫"));
//        mTitlerList.add(new MoviceTitlerMode("", "财经"));
//        mTitlerList.add(new MoviceTitlerMode("", "体育"));
    }

    View lastSelectedItem;

    Fragment mFragment;

    private void initView() {
        horizontalGridView.setOnChildSelectedListener(new OnChildSelectedListener() {
            @Override
            public void onChildSelected(ViewGroup parent, View view, int position, long id) {
                Log.e("fitrate", "horizontalGridView onChildSelected!" + position);
                lastSelectedItem = view;
                Log.d("fitrate", "position:" + position);
                TextView tv = view.findViewById(R.id.title_tv);
                tv.setTextColor(getResources().getColor(R.color.title_select_color));
                View lineView = view.findViewById(R.id.title_line_view);
                lineView.setBackgroundColor(getResources().getColor(R.color.title_select_color));


            }
        });

        horizontalGridView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.e("fitrate", "horizontalGridView focus change!" + hasFocus);
                if (!hasFocus) {

                } else {
                    flyingFrameView.setVisibility(View.GONE);
                }
            }
        });

        horizontalGridView.setOnKeyInterceptListener(new BaseGridView.OnKeyInterceptListener() {
            @Override
            public boolean onInterceptKeyEvent(KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
                    // BUG:避免焦点跑到搜索框去.
                    searchBtn.setFocusableInTouchMode(true);
                    searchBtn.setFocusable(true);
                    searchBtn.requestFocusFromTouch();

                    if (lastSelectedItem != null) {
                        Log.d("fitrate", "测试看看执行顺序");
                        TextView tv = lastSelectedItem.findViewById(R.id.title_tv);
                        tv.setTextColor(getResources().getColor(R.color.title_select_color));
                    } else {
                        Log.d("fitrate", "lastSelectedItem is null!");
                    }
                }
                return false;
            }
        });

        searchBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d("fitrate", "searchBtn focus change!" + b);
                searchBtn.setBackgroundResource(b ? R.drawable.trailer_btn_focused : R.drawable.trailer_btn_normal);
                if (b) {
                    flyingFrameView.setVisibility(View.GONE);
                }
            }
        });

        searchBtn.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
                    searchBtn.setFocusable(false);
                    horizontalGridView.requestFocusFromTouch();
                    return true;
                }
                return false;
            }
        });
    }

    final String[] fragments = {
            VideoFragment.class.getName(), MovieFragment.class.getName()
    };


    private void initFrameView() {
        flyingFrameView = ColorFlyingFrameView.build(this);
        flyingFrameView.setShapeMode(ColorFlyingFrameView.SHAPE_MODE_ROUND_RECTANGE);
        int borderWidth = getResources().getDimensionPixelOffset(R.dimen.w6);
        flyingFrameView.setPadding(getResources().getDimensionPixelOffset(R.dimen.w2));
        flyingFrameView.setBorderWidth(borderWidth);
        int radius = getResources().getDimensionPixelOffset(R.dimen.w32);
        flyingFrameView.setRoundRadius(radius);
        flyingFrameView.setShadowColor(Color.parseColor("#990066"));
        int shadow = getResources().getDimensionPixelOffset(R.dimen.w24);
        flyingFrameView.setShadowWidth(shadow);
    }
}
