package cn.fengmang.libui.pager;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.List;

import cn.fengmang.libui.R;


/**
 * Created by Administrator on 2018/8/7.
 */
public class AutoBanner<T> extends RelativeLayout {

    AutoTurnViewPager viewPager;
    PageIndicatorListener pageIndicatorListener;
    View indicatorView;
    List<T> mDatas;

    public AutoBanner(Context context) {
        this(context, null);
    }

    public AutoBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttr(attrs);
    }

    private void init(Context context) {
        View hView = LayoutInflater.from(context).inflate(
                R.layout.auto_banner, this, true);
        viewPager = (AutoTurnViewPager) hView.findViewById(R.id.vp_wenld_banner);
        setFocusable(false);
        setFocusableInTouchMode(false);
        setClickable(false);
    }

    private void initAttr(AttributeSet attrs) {
        setRunning(true); //是否正在执行翻页中   如果是canLoop=false  到头了 那就不翻页
        setCanLoop(true);// 是否循环
        setCanTurn(true);   //能否能执行自动翻页

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.AutoTurnViewPager);
        if (a != null) {
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.AutoTurnViewPager_canLoop) {
                    setCanLoop(a.getBoolean(attr, true));
                } else if (attr == R.styleable.AutoTurnViewPager_canTurn) {
                    setCanTurn(a.getBoolean(attr, true));
                } else if (attr == R.styleable.AutoTurnViewPager_isTouchScroll) {
                    setTouchScroll(a.getBoolean(attr, true));
                } else if (attr == R.styleable.AutoTurnViewPager_autoTurnTime) {
                    setAutoTurnTime(a.getInteger(attr, getAutoTurnTime()));
                } else if (attr == R.styleable.AutoTurnViewPager_scrollDuration) {
                    setScrollDuration(a.getInteger(attr, getScrollDuration()));
                } else if (attr == R.styleable.AutoTurnViewPager_reverse) {
                    setReverse(a.getBoolean(attr, false));
                }
            }
            a.recycle();
        }
    }


    /**
     * 设置底部指示器是否可见
     *
     * @param visible
     */
    public AutoBanner setInvaildViewVisible(boolean visible) {
        indicatorView.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * @param gravityAlign RelativeLayout
     * @return
     */
    public AutoBanner setPageIndicatorAlign(int... gravityAlign) {
        LayoutParams layoutParams = (LayoutParams) indicatorView.getLayoutParams();
        for (int i = 0; i < gravityAlign.length; i++) {
            layoutParams.addRule(gravityAlign[i]);
        }
        layoutParams.setMargins(5, 5, 5, 20);
        indicatorView.setLayoutParams(layoutParams);
        return this;
    }

    public AutoBanner setIndicatorView(View indicatorView) {
        removeView(this.indicatorView);
        this.indicatorView = indicatorView;
        addView(this.indicatorView);
        return this;
    }

    public AutoBanner setPageIndicatorListener(PageIndicatorListener pageIndicatorListener) {
        this.pageIndicatorListener = pageIndicatorListener;
        pageIndicatorListener.setDatas(viewPager.getAdapter().getDatas());
        removeListener(pageIndicatorListener);
        viewPager.addOnPageChangeListener(pageIndicatorListener);
        pageIndicatorListener.onPageSelected(viewPager.getCurrentItem());
        return this;
    }

    public void removeListener(PageIndicatorListener pageIndicatorListener) {
        if (pageIndicatorListener == null)
            return;
        if (viewPager == null)
            return;
        viewPager.removeOnPageChangeListener(pageIndicatorListener);
    }


    public AutoBanner startTurn() {
        viewPager.startTurn();
        return this;
    }

    public AutoBanner stopTurning() {
        viewPager.stopTurning();
        return this;
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;
        viewPager.getAdapter().setDatas(mDatas);
    }

    public AutoBanner setScrollDuration(int duration) {
        viewPager.setScrollDuration(duration);
        return this;
    }

    public int getScrollDuration() {
        return viewPager.getScrollDuration();
    }

    public void setCurrentItem(int page) {
        stopTurning();
        viewPager.setCurrentItem(page);
        startTurn();
    }

    public boolean isRunning() {
        return viewPager.isRunning();
    }

    public AutoBanner setRunning(boolean running) {
        viewPager.setRunning(running);
        return this;
    }

    public boolean isCanLoop() {
        return viewPager.isCanLoop();
    }

    public AutoBanner setCanLoop(boolean canLoop) {
        viewPager.setCanLoop(canLoop);
        return this;
    }

    public AutoBanner setCanTurn(boolean canTurn) {
        viewPager.setCanTurn(canTurn);
        return this;
    }

    public void setTouchScroll(boolean isCanScroll) {
        viewPager.setTouchScroll(isCanScroll);
    }

    public int getAutoTurnTime() {
        return viewPager.getAutoTurnTime();
    }

    public AutoBanner setAutoTurnTime(int autoTurnTime) {
        viewPager.setAutoTurnTime(autoTurnTime);
        return this;
    }

    public void setReverse(boolean reverse) {
        viewPager.setReverse(reverse);
    }

    public boolean isReverse() {
        return viewPager.isReverse();
    }

    public void setOnItemClickListener(OnPageClickListener onItemClickListener) {
        viewPager.setOnItemClickListener(onItemClickListener);
    }

    public AutoTurnViewPager getViewPager() {
        return viewPager;
    }

    public void setAdapter(LooperPagerAdapter adapter) {
        this.viewPager.setAdapter(adapter);
    }
}
