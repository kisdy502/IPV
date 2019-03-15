package cn.fm.rt;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;
import android.view.animation.Interpolator;

public class MyRecyclerView extends RecyclerView implements View.OnFocusChangeListener {

    private OnChildFocusChangeListener onChildFocusChangeListener;

    private int mSelectedPosition;
    private int mFocusedPos;

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setChildrenDrawingOrderEnabled(true);
        setWillNotDraw(true); // 自身不作onDraw处理
        setHasFixedSize(true);
        setOverScrollMode(View.OVER_SCROLL_NEVER);

        setClipChildren(false);
        setClipToPadding(false);
    }

    @Override
    public void onChildAttachedToWindow(View child) {
        if (child.isFocusable() && null == child.getOnFocusChangeListener()) {
            child.setOnFocusChangeListener(this);
        }
    }


    @Override
    public void onFocusChange(View itemView, boolean hasFocus) {
        if (null != itemView && itemView != this) {
            final int position = getChildAdapterPosition(itemView);
            itemView.setSelected(hasFocus);
            if (hasFocus) {
                mSelectedPosition = position;
                if (onChildFocusChangeListener != null) {
                    Log.d("test", "onChildFocused");
                    onChildFocusChangeListener.onChildFocused(MyRecyclerView.this, itemView,
                            position);
                }
            } else {
                if (onChildFocusChangeListener != null) {
                    Log.d("test", "onChildLoseFocused");
                    onChildFocusChangeListener.onChildLoseFocused(MyRecyclerView.this, itemView,
                            position);
                }
            }
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        if (state == SCROLL_STATE_IDLE) {
            setScrollValue(0, 0);
            if (onChildFocusChangeListener != null && getFocusedChild() != null) {
                onChildFocusChangeListener.onFixChildFocused(MyRecyclerView.this, getFocusedChild(),
                        getChildLayoutPosition(getFocusedChild()));
            }
        }
    }

    @Override
    public void smoothScrollBy(int dx, int dy, Interpolator interpolator) {
        setScrollValue(dx, dy);
        super.smoothScrollBy(dx, dy, interpolator);
    }

    private Point mScrollPoint = new Point();

    void setScrollValue(int x, int y) {
        if (x != 0 || y != 0) {
            mScrollPoint.set(x, y);
            setTag(mScrollPoint);
        } else {
            mScrollPoint.set(0, 0);
            setTag(null);
        }
    }

    public void setOnChildFocusChangeListener(OnChildFocusChangeListener onChildFocusChangeListener) {
        this.onChildFocusChangeListener = onChildFocusChangeListener;
    }

}
