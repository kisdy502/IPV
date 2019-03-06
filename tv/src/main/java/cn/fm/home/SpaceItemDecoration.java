package cn.fm.home;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2019/1/23.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int mLeftSpace;
    private int mBottomSpace;
    private int mRightSpace;
    private int mTopSpace;

    public SpaceItemDecoration(Rect rect) {
        this.mLeftSpace = rect.left;
        this.mTopSpace = rect.top;
        this.mRightSpace = rect.right;
        this.mBottomSpace = rect.bottom;
    }

    public SpaceItemDecoration(int l, int t, int r, int b) {
        this.mLeftSpace = l;
        this.mTopSpace = t;
        this.mRightSpace = r;
        this.mBottomSpace = b;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = mLeftSpace;
        outRect.bottom = mBottomSpace;
        outRect.right = mRightSpace;
        outRect.top = mTopSpace;
    }

}
