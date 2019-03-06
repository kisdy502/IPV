package cn.fm.vlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v17.leanback.widget.HorizontalGridView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.fm.iptv.SpaceItemDecoration;

/**
 * Created by Administrator on 2019/1/31.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    private List<ContentBean> contentList;

    private Context context;

    public VideoListAdapter(Context context, List<ContentBean> contentList) {
        this.context = context;
        this.contentList = contentList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("list", "onCreateViewHolder:" + viewType);
        HorizontalGridView view = new HorizontalGridView(context);
        ViewGroup.LayoutParams l = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(l);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ContentBean contentBean = contentList.get(position);
        String beanType = contentBean.getItemViewType();
        Log.d("list", "type:" + beanType);
        holder.view.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager
                .HORIZONTAL, false));
        if (beanType.equals("0")) {
            //holder.view.addItemDecoration(new SpaceItemDecoration(60, 60));
            holder.view.setBackgroundColor(Color.RED);
            holder.view.addItemDecoration(new SpaceItemDecoration(60, 60, 60, 60));
            holder.view.setAdapter(new PerformItemAdapter(context, contentBean));
        } else if (beanType.equals("1")) {
            holder.view.setBackgroundColor(Color.GREEN);
            holder.view.addItemDecoration(new SpaceItemDecoration(60, 60, 60, 60));
            holder.view.setAdapter(new VideoItemAdapter(context, contentBean));
        } else if (beanType.equals("2")) {
            holder.view.setBackgroundColor(Color.BLUE);
            holder.view.addItemDecoration(new SpaceItemDecoration(60, 60, 60, 60));
            holder.view.setAdapter(new PosterItemAdapter(context, contentBean));
        }
    }

    @Override
    public int getItemCount() {
        return contentList != null ? contentList.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        ContentBean contentBean = contentList.get(position);
        String type = contentBean.getItemViewType();
        return Integer.parseInt(type);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        HorizontalGridView view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = (HorizontalGridView) itemView;
        }
    }
}
