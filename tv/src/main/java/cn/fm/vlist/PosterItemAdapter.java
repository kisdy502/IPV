package cn.fm.vlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/2/1.
 */

public class PosterItemAdapter extends RecyclerView.Adapter<PosterItemAdapter.ViewHolder> {

    ContentBean contentBean;
    Context mContext;
    List<PosterInfo> posterInfoList;

    public PosterItemAdapter(Context mContext, ContentBean contentBean) {
        this.contentBean = contentBean;
        this.mContext = mContext;
        posterInfoList = (List<PosterInfo>) contentBean.getDataList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_poster_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("PosterItemAdapter", "position:" + position);
        holder.tvTitle.setText(posterInfoList.get(position).getPosterTitle());
    }

    @Override
    public int getItemCount() {
        return posterInfoList != null ? posterInfoList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.poster_title);
            ivPoster = itemView.findViewById(R.id.poster_url);
        }

    }
}
