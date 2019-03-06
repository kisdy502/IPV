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

public class PerformItemAdapter extends RecyclerView.Adapter<PerformItemAdapter.ViewHolder> {

    ContentBean contentBean;
    Context mContext;
    List<PerforInfo> perforInfoList;

    public PerformItemAdapter(Context mContext, ContentBean contentBean) {
        this.contentBean = contentBean;
        this.mContext = mContext;
        perforInfoList = (List<PerforInfo>) contentBean.getDataList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_perform_info, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("PerformItemAdapter", "position:" + position);
        holder.tvName.setText(perforInfoList.get(position).getPerformName());
    }

    @Override
    public int getItemCount() {
        return perforInfoList != null ? perforInfoList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView ivIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.perform_name);
            ivIcon = itemView.findViewById(R.id.perform_url);
        }
    }
}
