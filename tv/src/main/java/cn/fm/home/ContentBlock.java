package cn.fm.home;

/**
 * Created by Administrator on 2019/2/14.
 */

public class ContentBlock {

    public ContentBlock(String title, String posterUrl) {
        this(title, posterUrl, 1);
    }

    public ContentBlock(String title, String posterUrl, int spanSize) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.spanSize = spanSize;
    }

    public ContentBlock(String title, String posterUrl, String backUrl) {
        this(title, posterUrl);
        this.backgroundUrl = backUrl;
    }

    public ContentBlock(String title, String posterUrl, String backUrl, int spanSize) {
        this(title, posterUrl, spanSize);
        this.backgroundUrl = backUrl;
    }

    protected String title;
    protected String posterUrl;
    protected String backgroundUrl;

    protected int spanSize;     //横跨列数

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    @Override
    public String toString() {
        return title + "|" + spanSize;
    }

}
