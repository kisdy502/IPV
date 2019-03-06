package cn.fm.vlist;

import android.content.Context;
import android.support.v17.leanback.widget.VerticalGridView;
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

public class VideoItemAdapter extends RecyclerView.Adapter<VideoItemAdapter.ViewHolder> {

    ContentBean contentBean;
    Context mContext;
    List<VideoInfo> videoInfoList;

    public VideoItemAdapter(Context mContext, ContentBean contentBean) {
        this.contentBean = contentBean;
        this.mContext = mContext;
        videoInfoList = (List<VideoInfo>) contentBean.getDataList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_video_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("VideoItemAdapter", "position:" + position);
        holder.tvName.setText(videoInfoList.get(position).getVideoName());
    }

    @Override
    public int getItemCount() {
        return videoInfoList != null ? videoInfoList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvScore;
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.video_name);
            tvScore = itemView.findViewById(R.id.video_score);
            ivIcon = itemView.findViewById(R.id.video_url);
        }
    }
}
