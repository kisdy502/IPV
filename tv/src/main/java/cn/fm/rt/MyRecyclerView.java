package cn.fm.rt;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by Administrator on 2019/2/22.
 */

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

        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    @Override
    public void onChildAttachedToWindow(View child) {
        if (child.isFocusable() && null == child.getOnFocusChangeListener()) {
            child.setOnFocusChangeListener(this);
        }
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
        Log.e("myrecycler", "gainFocus=" + gainFocus + ",hasFocus=" + hasFocus() +
                ",direction=" + direction);
        if (gainFocus) {
            setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
        } else {
            setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        }
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public void onFocusChange(View itemView, boolean hasFocus) {
        if (null != itemView && itemView != this) {
            final int position = getChildAdapterPosition(itemView);
            itemView.setSelected(hasFocus);
            if (hasFocus) {
                mSelectedPosition = position;
                if (onChildFocusChangeListener != null) {
                    onChildFocusChangeListener.onChildFocused(MyRecyclerView.this, itemView,
                            position);
                }
            } else {
                itemView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!hasFocus()) {
                            //模拟TvRecyclerView失去焦点
                            onFocusChanged(false, FOCUS_DOWN, null);
                        }
                    }
                }, 6);
                if (onChildFocusChangeListener != null) {
                    onChildFocusChangeListener.onChildLoseFocused(MyRecyclerView.this, itemView,
                            position);
                }
            }
        }
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        if (null == getFocusedChild()) {
            //请求默认焦点
            requestDefaultFocus();
        }
        return false;
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

    public void requestDefaultFocus() {
        setSelection(getFirstVisibleAndFocusablePosition());
    }

    public int getFirstVisiblePosition() {
        if (getChildCount() == 0) {
            return 0;
        } else {
            if (getLayoutManager() instanceof LinearLayoutManager) {
                return ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
            }
            return getChildAdapterPosition(getChildAt(0));
        }
    }

    public int getFirstVisibleAndFocusablePosition() {
        int position = getFirstVisiblePosition();
        for (; position < getChildCount(); position++) {
            View item = getLayoutManager().findViewByPosition(position);
            if (null != item && item.isFocusable()) {
                return position;
            }
        }
        return -1;
    }

    public void setSelection(int position) {
        if (null == getAdapter() || position < 0 || position >= getAdapter().getItemCount()) {
            return;
        }
        View view = getLayoutManager().findViewByPosition(position);
        if (null != view) {
            if (!hasFocus()) {
                //模拟TvRecyclerView获取焦点
                onFocusChanged(true, FOCUS_DOWN, null);
            }
            view.requestFocus();
        }
    }

    public void setOnChildFocusChangeListener(OnChildFocusChangeListener onChildFocusChangeListener) {
        this.onChildFocusChangeListener = onChildFocusChangeListener;
    }
}
