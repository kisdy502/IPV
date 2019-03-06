package cn.fm.home;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
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
import cn.fm.ipv.R;

public class HomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContentGroup> contentList;

    private Context context;
    private ColorFlyingFrameView flyingFrameView;

    private final static int ITEM_TYPE_HEADER = 100001;
    LayoutInflater inflater;

    public HomeListAdapter(Context context, List<ContentGroup> contentList, ColorFlyingFrameView flyingFrameView) {
        this.context = context;
        this.contentList = contentList;
        this.flyingFrameView = flyingFrameView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_HEADER) {
            View view = inflater.inflate(R.layout.item_title_view, parent, false);
            return new HeaderViewHolder(view);
        } else {
            FMVerticalGridView view = new FMVerticalGridView(context);
            return new DefaultViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ContentGroup contentGroup = contentList.get(position);
        if (holder instanceof DefaultViewHolder) {
            DefaultViewHolder defaultViewHolder = (DefaultViewHolder) holder;

//            Log.d("HomeItemAdapter", "position:" + position + ",blockType:" + contentGroup.getBlockType());
            final HomeItemAdapter adapter = new HomeItemAdapter(context, contentGroup, flyingFrameView);
            Rect rect = ItemHelper.getPaddingByBlockType(contentGroup.getBlockType());
//            Log.d("HomeItemAdapter", "space:" + rect.toString());

            GridLayoutManager gm = new GridLayoutManager(context, contentGroup.getSpanCount(),
                    contentGroup.getOrientation(), false);
            gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int pos) {
                    ContentBlock contentBlock = contentGroup.getBlockList().get(pos);
                    return contentBlock.getSpanSize();
                }
            });
            int left = context.getResources().getDimensionPixelOffset(R.dimen.size_48);
            defaultViewHolder.view.setPadding(left, 0, 0, 0);  //微调界面，使得比较居中
            defaultViewHolder.view.addItemDecoration(new SpaceItemDecoration(rect.left, rect.top, rect.right, rect.bottom));
            defaultViewHolder.view.setLayoutManager(gm);
            defaultViewHolder.view.setAdapter(adapter);
            defaultViewHolder.view.setOnItemFocusListener(new OnItemFocusChangeListener() {
                @Override
                public boolean onItemPreSelected(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                    return false;
                }

                @Override
                public boolean onItemSelected(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                    return false;
                }

                @Override
                public boolean onReviseFocusFollow(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
//                    Log.e("test:", "onReviseFocusFollow:" + position);
                    if (itemView instanceof CircleHeaderItemView) {
                        flyingFrameView.setShapeMode(ColorFlyingFrameView.SHAPE_MODE_CICRLE);
                    } else if (itemView instanceof NormalItemView) {
                        flyingFrameView.setShapeMode(ColorFlyingFrameView.SHAPE_MODE_ROUND_RECTANGE);
                    }
                    flyingFrameView.onMoveTo(itemView, 1, 1, 0);
                    return false;
                }
            });
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
            return super.getItemViewType(position);
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

    public static class DefaultViewHolder extends RecyclerView.ViewHolder {

        FMVerticalGridView view;

        public DefaultViewHolder(View itemView) {
            super(itemView);
            view = (FMVerticalGridView) itemView;
        }
    }


}
