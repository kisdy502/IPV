package cn.fm.rt;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.fengmang.libui.flying.ColorFlyingFrameView;
import cn.fm.home.ImgeUrlList;
import cn.fm.home.SpaceItemDecoration;
import cn.fm.ipv.R;

public class GridTestActivity extends Activity {
    private final static String TAG = "GridTest";
    MyRecyclerView gridRecyclerView;
    GridLayoutManager gridLayoutManager;
    MyAdapter myAdapter;
    List<Bean> beanList;

    MyRecyclerView linearRecyclerView;
    LinearLayoutManager linearLayoutManager;
    MyAdapter2 myAdapter2;
    List<Bean> beanList2;

    private ColorFlyingFrameView flyingFrameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_test);

        initFrameView();

        gridRecyclerView = findViewById(R.id.test_list);
        int v = getResources().getDimensionPixelOffset(R.dimen.size_24);
        int h = getResources().getDimensionPixelOffset(R.dimen.size_48);
        gridRecyclerView.addItemDecoration(new SpaceItemDecoration(v, h, v, h));
        initData();
        gridLayoutManager = new GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false);
        gridRecyclerView.setLayoutManager(gridLayoutManager);
        myAdapter = new MyAdapter(this, beanList);
        gridRecyclerView.setAdapter(myAdapter);

        gridRecyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("gridR", "网格RecyclerView 获得焦点");
                } else {
                    Log.e("gridR", "网格RecyclerView 失去焦点");
                }
            }
        });
        gridRecyclerView.setOnChildFocusChangeListener(new OnChildFocusChangeListener() {
            @Override
            public boolean onChildLoseFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                return false;
            }

            @Override
            public boolean onChildFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                flyingFrameView.onMoveTo(itemView, 1.1f, 1.1f, 0);
                return false;
            }

            @Override
            public boolean onFixChildFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                flyingFrameView.onMoveTo(itemView, 1.1f, 1.1f, 0);
                return false;
            }
        });
        Log.i("gridR", "网格RecyclerView Id:" + gridRecyclerView.getId());


        linearRecyclerView = findViewById(R.id.title_list);
        int v2 = getResources().getDimensionPixelOffset(R.dimen.size_42);
        linearRecyclerView.addItemDecoration(new SpaceItemDecoration(v2, v2, v2, v2));
        initData2();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        linearRecyclerView.setLayoutManager(linearLayoutManager);
        myAdapter2 = new MyAdapter2(this, beanList2);
        linearRecyclerView.setAdapter(myAdapter2);
        linearRecyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Log.i("linearR", "水平RecyclerView 获得焦点");
                } else {
                    Log.e("linearR", "水平RecyclerView 失去焦点");
                }
            }
        });
        linearRecyclerView.setOnChildFocusChangeListener(new OnChildFocusChangeListener() {
            @Override
            public boolean onChildLoseFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position) {

                return false;
            }

            @Override
            public boolean onChildFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                flyingFrameView.onMoveTo(itemView, 1.1f, 1.1f, 0);
                return false;
            }

            @Override
            public boolean onFixChildFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position) {
                flyingFrameView.onMoveTo(itemView, 1.1f, 1.1f, 0);
                return false;
            }
        });
        Log.i("linearR", "水平RecyclerView Id:" + linearRecyclerView.getId());
    }

    private void initData() {
        beanList = new ArrayList<>();
        for (int i = 0; i < 483; i++) {
            if (i % 6 == 0) {
                beanList.add(new Bean("test1", ImgeUrlList.L6_360[0]));
            } else if (i % 6 == 1) {
                beanList.add(new Bean("test1", ImgeUrlList.L6_360[1]));
            } else if (i % 6 == 2) {
                beanList.add(new Bean("test1", ImgeUrlList.L6_360[2]));
            } else if (i % 6 == 3) {
                beanList.add(new Bean("test1", ImgeUrlList.L6_360[3]));
            } else if (i % 6 == 4) {
                beanList.add(new Bean("test1", ImgeUrlList.L6_360[4]));
            } else if (i % 6 == 5) {
                beanList.add(new Bean("test1", ImgeUrlList.L6_360[5]));
            }
        }
    }

    private void initData2() {
        beanList2 = new ArrayList<>();
        for (int i = 0; i < 420; i++) {
            if (i % 3 == 0) {
                beanList2.add(new Bean("test1", ImgeUrlList.L4_204[0]));
            } else if (i % 3 == 1) {
                beanList2.add(new Bean("test1", ImgeUrlList.L4_204[1]));
            } else if (i % 3 == 2) {
                beanList2.add(new Bean("test1", ImgeUrlList.L4_204[2]));
            }
        }
    }


    private void initFrameView() {
        flyingFrameView = ColorFlyingFrameView.build(this);
        flyingFrameView.setShapeMode(ColorFlyingFrameView.SHAPE_MODE_RECTANGE);
        int borderWidth = getResources().getDimensionPixelOffset(R.dimen.w6);
        flyingFrameView.setPadding(getResources().getDimensionPixelOffset(R.dimen.w2));
        flyingFrameView.setBorderWidth(borderWidth);
        flyingFrameView.setRoundRadius(0);
        flyingFrameView.setShadowColor(Color.parseColor("#990066"));
        int shadow = getResources().getDimensionPixelOffset(R.dimen.w24);
        flyingFrameView.setShadowWidth(shadow);
    }

    static class Bean {
        String name;
        String url;

        public Bean(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }


    private static class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

        List<Bean> beanList;
        Context context;

        public MyAdapter(Context context, List<Bean> beanList) {
            this.context = context;
            this.beanList = beanList;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final VH holder, int position) {
//            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    Log.e("GridAdapter", "hasFocus:" + hasFocus);
//                    if (hasFocus) {
//                        holder.itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration
//                                (200).start();
//                    }else{
//                        holder.itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration
//                                (200).start();
//                    }
//                }
//            });
            Glide.with(context).load(beanList.get(position).getUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return beanList.size();
        }

        static class VH extends RecyclerView.ViewHolder {
            TextView textView;
            ImageView imageView;

            public VH(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.poster_title);
                imageView = itemView.findViewById(R.id.poster_url);
            }
        }
    }


    private static class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.VH> {

        List<Bean> beanList;
        Context context;

        public MyAdapter2(Context context, List<Bean> beanList) {
            this.context = context;
            this.beanList = beanList;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_opt, parent, false);
            return new VH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final VH holder, int position) {
//            holder.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    Log.e("LinearAdapter", "hasFocus:" + hasFocus);
//                    if (hasFocus) {
//                        holder.itemView.animate().scaleX(1.2f).scaleY(1.2f).setDuration
//                                (200).start();
//                    }else{
//                        holder.itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration
//                                (200).start();
//                    }
//                }
//            });
            Glide.with(context).load(beanList.get(position).getUrl()).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return beanList.size();
        }

        static class VH extends RecyclerView.ViewHolder {
            TextView textView;
            ImageView imageView;

            public VH(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.poster_title);
                imageView = itemView.findViewById(R.id.poster_url);
            }
        }
    }
}
