package cn.fm.home;

import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.OrientationHelper;
import android.text.TextUtils;

import java.lang.annotation.Target;
import java.util.List;

/**
 * 内容组，确定以何种列表展示
 */
public class ContentGroup {

    public final static int VIEW_TYPE_NORAML = 0;
    public final static int VIEW_TYPE_HEADER = 1;

    public final static int HORIZONTAL = OrientationHelper.HORIZONTAL;
    public final static int VERTICAL = OrientationHelper.VERTICAL;

    private int orientation;    //HORIZONTAL = 0;  VERTICAL = 1
    private int spanCount;      //layoutType 为Grid时，此值有用

    private String groupTitle;

    private boolean isHeaderGroup = false;

    private int groupViewType = VERTICAL;

    public ContentGroup() {
    }

    public ContentGroup(String groupTitle) {
        this.groupTitle = groupTitle;
        if (!TextUtils.isEmpty(groupTitle)) {
            isHeaderGroup = true;
        }
    }

    private int viewType = VIEW_TYPE_NORAML;     //决定了使用哪个布局文件
    @NonNull
    private String blockType;                   //数据类型，决定了业务布局文件尺寸
    @NonNull
    private List<ContentBlock> dataList;

    public List<ContentBlock> getBlockList() {
        return dataList;
    }

    public void setDataList(List<ContentBlock> dataList) {
        this.dataList = dataList;
    }

    public int getSpanCount() {
        return spanCount;
    }

    public void setSpanCount(int spanCount) {
        this.spanCount = spanCount;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        this.groupViewType = orientation;
    }

    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
        if (!TextUtils.isEmpty(groupTitle)) {
            isHeaderGroup = true;
        }
    }

    public boolean isHeaderGroup() {
        return isHeaderGroup;
    }

    public int getGroupViewType() {
        return groupViewType;
    }

    public void setGroupViewType(int groupViewType) {
        this.groupViewType = groupViewType;
    }
}
