package cn.fm.launcher;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.fm.home.CircleHeaderItemView;
import cn.fm.home.ContentBlock;
import cn.fm.home.ContentGroup;
import cn.fm.home.ItemHelper;
import cn.fm.home.NormalItemView;

/**
 * Created by Administrator on 2019/2/1.
 */

public class LauncherItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ContentGroup contentGroup;
    List<ContentBlock> contentBlockList;
    int[] layoutSize;

    public LauncherItemAdapter(Context mContext, ContentGroup contentGroup) {
        this.mContext = mContext;
        this.contentGroup = contentGroup;
        contentBlockList = (List<ContentBlock>) contentGroup.getBlockList();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ContentGroup.VIEW_TYPE_NORAML) {
            View view = new NormalItemView(mContext);
            return new NormalViewHolder(view);
        } else if (viewType == ContentGroup.VIEW_TYPE_HEADER) {
            View view = new CircleHeaderItemView(mContext);
            return new CilcleHeaderViewHolder(view);
        } else {
            throw new IllegalArgumentException("not support viewType:" + viewType);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalViewHolder) {
            layoutSize = ItemHelper.getSizeByBlockType(contentGroup.getBlockType(),
                    contentBlockList.get(position).getSpanSize());
            Log.d("launchItem","layoutSize:"+layoutSize[0]+","+layoutSize[1]);
            NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
            normalViewHolder.normalItemView.setItemSize(layoutSize);
            normalViewHolder.normalItemView.setPosterUrl(contentBlockList.get(position)
                    .getPosterUrl());
            normalViewHolder.normalItemView.setTitle(contentBlockList.get(position).getTitle
                    ());
            normalViewHolder.normalItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.d("test", "hasFocus:" + hasFocus);
                }
            });
        } else if (holder instanceof CilcleHeaderViewHolder) {
            layoutSize = ItemHelper.getSizeByBlockType(contentGroup.getBlockType(),
                    contentBlockList.get(position).getSpanSize());
            Log.i("launchItem","layoutSize:"+layoutSize[0]+","+layoutSize[1]);
            CilcleHeaderViewHolder cilclerHeaderViewHolder = (CilcleHeaderViewHolder) holder;
            cilclerHeaderViewHolder.circleHeaderItemView.setItemSize(layoutSize);
            cilclerHeaderViewHolder.circleHeaderItemView.setHeaderUrl(contentBlockList.get
                    (position).getPosterUrl());
            cilclerHeaderViewHolder.circleHeaderItemView.setBackgroundUrl(contentBlockList
                    .get(position).getBackgroundUrl());
            cilclerHeaderViewHolder.circleHeaderItemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Log.d("test", "hasFocus:" + hasFocus);
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

    public static class NormalViewHolder extends RecyclerView.ViewHolder {
        NormalItemView normalItemView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            normalItemView = (NormalItemView) itemView;
        }
    }

    public static class CilcleHeaderViewHolder extends RecyclerView.ViewHolder {

        CircleHeaderItemView circleHeaderItemView;

        public CilcleHeaderViewHolder(View itemView) {
            super(itemView);
            circleHeaderItemView = (CircleHeaderItemView) itemView;
        }
    }


}
