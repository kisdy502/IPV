package cn.fm.rt;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2019/2/22.
 */

public interface OnChildFocusChangeListener {

    boolean onChildLoseFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position);

    boolean onChildFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position);

    boolean onFixChildFocused(@NonNull RecyclerView parent, @NonNull View itemView, int position);
}
