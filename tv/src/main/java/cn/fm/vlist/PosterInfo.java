package cn.fm.vlist;

/**
 * Created by Administrator on 2019/1/31.
 */

public class PosterInfo {

    private String posterTitle;
    private String posterImgUrl;

    public PosterInfo(String posterTitle, String posterImgUrl) {
        this.posterTitle = posterTitle;
        this.posterImgUrl = posterImgUrl;
    }

    public String getPosterTitle() {
        return posterTitle;
    }

    public void setPosterTitle(String posterTitle) {
        this.posterTitle = posterTitle;
    }

    public String getPosterImgUrl() {
        return posterImgUrl;
    }

    public void setPosterImgUrl(String posterImgUrl) {
        this.posterImgUrl = posterImgUrl;
    }
}
