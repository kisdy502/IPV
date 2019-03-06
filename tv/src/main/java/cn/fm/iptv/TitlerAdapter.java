package cn.fm.iptv;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/1/23.
 */

public class TitlerAdapter extends RecyclerView.Adapter<TitlerAdapter.ViewHolder> {
    private Context mContext;
    List<MoviceTitlerMode> mTitlerList;
    private FiltrateMoviceActivity activity;

    public TitlerAdapter(Context context, List<MoviceTitlerMode> mTitlerList) {
        this.mContext = context;
        this.mTitlerList = mTitlerList;
        this.activity = (FiltrateMoviceActivity) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_title_layout, null);
        view.setDuplicateParentStateEnabled(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (null != mTitlerList) {
            holder.itemView.setTag(position);
            holder.nameTv.setText(mTitlerList.get(position).getName());
            holder.itemView.setTag(position);
            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, final boolean b) {
                    Log.e("fitrate", "horizontalGridView focus changed!" + b);
                    final int tag = (int) view.getTag();
                    Log.d("adapter:", "tag:" + tag);
                    TextView tv = view.findViewById(R.id.title_tv);
                    View lineView = view.findViewById(R.id.title_line_view);
                    if (!b) {
                        tv.setTextColor(mContext.getResources().getColor(R.color
                                .title_none_color));
                        lineView.setBackgroundColor(mContext.getResources().getColor(R.color
                                .rgba_00000000));
                    } else {
                        tv.setTextColor(mContext.getResources().getColor(R.color
                                .title_select_color));
                        lineView.setBackgroundColor(mContext.getResources().getColor(R.color
                                .title_select_color));


                        if (activity.mFragment != null) {
                            FragmentTransaction mFt = activity.getSupportFragmentManager()
                                    .beginTransaction();
                            mFt.detach(activity.mFragment);
                            mFt.commit();
                        }

                        activity.mFragment = activity.getSupportFragmentManager().findFragmentByTag
                                (position + "");
                        FragmentTransaction mFt = activity.getSupportFragmentManager()
                                .beginTransaction();
                        if (activity.mFragment == null) {
                            activity.mFragment = Fragment.instantiate(activity, activity
                                    .fragments[position]);
                            mFt.add(R.id.content_layout, activity.mFragment, String.valueOf
                                    (position));
                        } else {
                            mFt.attach(activity.mFragment);
                        }
                        mFt.commit();
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTitlerList != null ? mTitlerList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;

        public ViewHolder(View view) {
            super(view);
            nameTv = view.findViewById(R.id.title_tv);
        }
    }
}
