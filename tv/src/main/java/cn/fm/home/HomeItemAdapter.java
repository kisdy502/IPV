package cn.fm.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.fengmang.libui.flying.ColorFlyingFrameView;
import cn.fengmang.libui.recycler.OnItemFocusChangeListener;
import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/2/1.
 */

public class HomeItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ContentGroup contentGroup;
    List<ContentBlock> contentBlockList;
    int[] layoutSize;
    private ColorFlyingFrameView flyingFrameView;

    public HomeItemAdapter(Context mContext, ContentGroup contentGroup, ColorFlyingFrameView flyingFrameView) {
        this.mContext = mContext;
        this.contentGroup = contentGroup;
        contentBlockList = (List<ContentBlock>) contentGroup.getBlockList();
        this.flyingFrameView = flyingFrameView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ContentGroup.VIEW_TYPE_NORAML) {
            View view = new NormalItemView(mContext);
            return new NormalViewHolder(view);
        } else if (viewType == ContentGroup.VIEW_TYPE_HEADER) {
            View view = new CircleHeaderItemView(mContext);
            return new CilclerHeaderViewHolder(view);
        } else {
            throw new IllegalArgumentException("not support viewType:" + viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            layoutSize = ItemHelper.getSizeByBlockType(contentGroup.getBlockType(),
                    contentBlockList.get(position).getSpanSize());
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            normalViewHolder.normalItemView.setItemSize(layoutSize);
            normalViewHolder.normalItemView.setPosterUrl(contentBlockList.get(position)
                    .getPosterUrl());
            normalViewHolder.normalItemView.setTitle(contentBlockList.get(position).getTitle
                    ());
            normalViewHolder.normalItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
//                    Log.d("test", "hasFocus:" + hasFocus);
                    if (hasFocus) {
                        flyingFrameView.setVisibility(View.VISIBLE);
                        flyingFrameView.setShapeMode(ColorFlyingFrameView.SHAPE_MODE_ROUND_RECTANGE);
                        flyingFrameView.onMoveTo(v, 1, 1, 0);
                    } else {

                    }
                }
            });
        } else if (holder instanceof CilclerHeaderViewHolder) {
            layoutSize = ItemHelper.getSizeByBlockType(contentGroup.getBlockType(),
                    contentBlockList.get(position).getSpanSize());
            CilclerHeaderViewHolder cilclerHeaderViewHolder = (CilclerHeaderViewHolder) holder;
            cilclerHeaderViewHolder.circleHeaderItemView.setItemSize(layoutSize);
            cilclerHeaderViewHolder.circleHeaderItemView.setHeaderUrl(contentBlockList.get
                    (position).getPosterUrl());
            cilclerHeaderViewHolder.circleHeaderItemView.setBackgroundUrl(contentBlockList
                    .get(position).getBackgroundUrl());
            cilclerHeaderViewHolder.circleHeaderItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        flyingFrameView.setVisibility(View.VISIBLE);
                        flyingFrameView.setShapeMode(ColorFlyingFrameView.SHAPE_MODE_CICRLE);
                        flyingFrameView.onMoveTo(v, 1, 1, 0);
                    } else {

                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return contentBlockList != null ? contentBlockList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        //在第一个位置添加头
        return contentGroup.getViewType();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }


    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        NormalItemView normalItemView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            normalItemView = (NormalItemView) itemView;
        }
    }

    public static class CilclerHeaderViewHolder extends RecyclerView.ViewHolder {

        CircleHeaderItemView circleHeaderItemView;

        public CilclerHeaderViewHolder(View itemView) {
            super(itemView);
            circleHeaderItemView = (CircleHeaderItemView) itemView;
        }
    }


}
