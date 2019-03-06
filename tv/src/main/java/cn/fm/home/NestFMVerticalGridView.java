package cn.fm.home;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

import cn.fengmang.libui.recycler.OnItemFocusChangeListener;

/**
 * Created by Administrator on 2019/2/18.
 */

public class NestFMVerticalGridView extends RecyclerView {
    private final static String TAG = "NestFM";

    private boolean hasItemDecoration;

    protected OnItemFocusChangeListener onItemListener;

    public NestFMVerticalGridView(Context context) {
        this(context, null);
    }

    public NestFMVerticalGridView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NestFMVerticalGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //setHasFixedSize(true);
        //setChildrenDrawingOrderEnabled(true);
        //setWillNotDraw(true);
        //setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void addItemDecoration(ItemDecoration decor) {
        ItemDecoration oldDecoration = getItemDecorationAt(0);
        if (oldDecoration != null) {
            removeItemDecoration(oldDecoration);
        }
        super.addItemDecoration(decor);
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        Parcelable superData = super.onSaveInstanceState();
        bundle.putParcelable("superData", superData);
        bundle.putBoolean("hasItemDecoration", hasItemDecoration);
        return bundle;


    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        Parcelable superData = bundle.getParcelable("superData");
        hasItemDecoration = bundle.getBoolean("hasItemDecoration");
        super.onRestoreInstanceState(superData);
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == SCROLL_STATE_IDLE) {
//            Log.d("Nest", "Nest state:" + state);
            setScrollValue(0, 0);
            if (onItemListener != null && getFocusedChild() != null) {
                onItemListener.onReviseFocusFollow(NestFMVerticalGridView.this, getFocusedChild(), getChildLayoutPosition(getFocusedChild()));
            }
        }
    }

    @Override
    public void smoothScrollBy(int dx, int dy, Interpolator interpolator) {
        setScrollValue(dx, dy);
        //Log.d("test", "dx:" + dx + ",dy:" + dy);
        super.smoothScrollBy(dx, dy, interpolator);

    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        Log.i(TAG, "onFocusChanged: gainFocus = " + gainFocus + ", direction = " + direction);
    }


    /**
     * 用途飞框位置校准
     */
    private Point mScrollPoint = new Point();

    void setScrollValue(int x, int y) {
        if (x != 0 || y != 0) {
            mScrollPoint.set(0, y);
            setTag(mScrollPoint);
        } else {
            mScrollPoint.set(0, 0);
            setTag(mScrollPoint);
        }
    }

    public void setOnItemFocusListener(OnItemFocusChangeListener onItemListener) {
        this.onItemListener = onItemListener;
    }

}
