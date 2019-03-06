package cn.fm.vlist;

/**
 * Created by Administrator on 2019/1/31.
 */

public class PerforInfo {
    private String performName;
    private String performUrl;

    public String getPerformName() {
        return performName;
    }

    public void setPerformName(String performName) {
        this.performName = performName;
    }

    public String getPerformUrl() {
        return performUrl;
    }

    public void setPerformUrl(String performUrl) {
        this.performUrl = performUrl;
    }

    public PerforInfo(String performName, String performUrl) {
        this.performName = performName;
        this.performUrl = performUrl;
    }
}
