package cn.fm.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.fengmang.libui.flying.ColorFlyingFrameView;
import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/2/1.
 */

public class HomeHeaderAdapter extends RecyclerView.Adapter<HomeHeaderAdapter.HeaderViewHolder> {

    Context mContext;
    ContentGroup contentGroup;

    public HomeHeaderAdapter(Context mContext, ContentGroup contentGroup) {
        this.mContext = mContext;
        this.contentGroup = contentGroup;
    }

    @Override
    public HeaderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_title_view, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HeaderViewHolder holder, int position) {
        holder.textView.setText(contentGroup.getGroupTitle());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return contentGroup.getViewType();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView;
        }
    }

}
