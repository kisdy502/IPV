package cn.fm.launcher;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.fengmang.libui.flying.ColorFlyingFrameView;
import cn.fengmang.libui.recycler.OnItemFocusChangeListener;
import cn.fm.home.CircleHeaderItemView;
import cn.fm.home.ContentBlock;
import cn.fm.home.ContentGroup;
import cn.fm.home.FMVerticalGridView;
import cn.fm.home.HomeItemAdapter;
import cn.fm.home.ItemHelper;
import cn.fm.home.NormalItemView;
import cn.fm.home.SpaceItemDecoration;
import cn.fm.ipv.R;


/**
 * Created by Administrator on 2019/1/31.
 */

public class LauncherListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContentGroup> contentList;

    private Context context;

    private final static int ITEM_TYPE_HEADER = 100001;
    LayoutInflater inflater;

    public LauncherListAdapter(Context context, List<ContentGroup> contentList) {
        this.context = context;
        this.contentList = contentList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("LaunchList", "viewType:" + viewType);
        if (viewType == ITEM_TYPE_HEADER) {
            View view = inflater.inflate(R.layout.item_title_view, parent, false);
            return new HeaderViewHolder(view);
        } else if (viewType == ContentGroup.VERTICAL) {
            VerticalGridView view = new VerticalGridView(context);
            return new VerticalViewHolder(view);
        } else if (viewType == ContentGroup.HORIZONTAL) {
            HorizontalGridView view = new HorizontalGridView(context);
            return new HorizontalViewHolder(view);
        } else {
            throw new IllegalArgumentException("not support ItemType");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ContentGroup contentGroup = contentList.get(position);
        if (holder instanceof HorizontalViewHolder) {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) holder;
            final LauncherItemAdapter adapter = new LauncherItemAdapter(context, contentGroup);
            Rect rect = ItemHelper.getPaddingByBlockType(contentGroup.getBlockType());

            horizontalViewHolder.horizontalGridView.setNumRows(contentGroup.getSpanCount());
            horizontalViewHolder.horizontalGridView.setRowHeight(240);
            horizontalViewHolder.horizontalGridView.setHorizontalSpacing(rect.left);
            horizontalViewHolder.horizontalGridView.setVerticalSpacing(rect.top);
            horizontalViewHolder.horizontalGridView.setAdapter(adapter);
        } else if (holder instanceof VerticalViewHolder) {
            VerticalViewHolder verticalViewHolder = (VerticalViewHolder) holder;
            final LauncherItemAdapter adapter = new LauncherItemAdapter(context, contentGroup);
            Rect rect = ItemHelper.getPaddingByBlockType(contentGroup.getBlockType());
            verticalViewHolder.verticalGridView.setNumColumns(contentGroup.getSpanCount());
            verticalViewHolder.verticalGridView.setColumnWidth(150);
            verticalViewHolder.verticalGridView.setHorizontalSpacing(rect.left);
            verticalViewHolder.verticalGridView.setVerticalSpacing(rect.top);
            verticalViewHolder.verticalGridView.setAdapter(adapter);

        } else if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.textView.setText(contentGroup.getGroupTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        final ContentGroup contentBean = contentList.get(position);
        if (contentBean.isHeaderGroup()) {
            return ITEM_TYPE_HEADER;
        } else {
            return contentBean.getGroupViewType();
        }
    }

    @Override
    public int getItemCount() {
        return contentList != null ? contentList.size() : 0;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

    public static class HorizontalViewHolder extends RecyclerView.ViewHolder {

        HorizontalGridView horizontalGridView;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            horizontalGridView = (HorizontalGridView) itemView;
        }
    }

    public static class VerticalViewHolder extends RecyclerView.ViewHolder {

        VerticalGridView verticalGridView;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            verticalGridView = (VerticalGridView) itemView;
        }
    }


}
