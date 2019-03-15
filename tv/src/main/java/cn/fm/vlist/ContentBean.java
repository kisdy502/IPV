package cn.fm.vlist;

import android.support.annotation.NonNull;

import java.util.List;

public class ContentBean {

    private int layoutType;   //0,Linear ，1 Grid
    private int orientation;  //   HORIZONTAL = 0;  VERTICAL = 1

    private int spanCount;    //layoutType 为Grid时，此值有用
    private int spanSize;     //横跨列数
    @NonNull
    private String itemViewType;

    private String dataType;  //数据类型，决定了业务bean的类型
    @NonNull
    private List<?> dataList;

    public String getItemViewType() {
        return itemViewType;
    }

    public void setItemViewType(String itemViewType) {
        this.itemViewType = itemViewType;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
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
    }

    public int getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
