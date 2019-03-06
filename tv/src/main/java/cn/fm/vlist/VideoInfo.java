package cn.fm.vlist;

/**
 * Created by Administrator on 2019/1/31.
 */

public class VideoInfo {

    private String videoName;
    private String videoImgUrl;
    private int videoScore;

    public VideoInfo(String videoName, String videoImgUrl, int videoScore) {
        this.videoName = videoName;
        this.videoImgUrl = videoImgUrl;
        this.videoScore = videoScore;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public int getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(int videoScore) {
        this.videoScore = videoScore;
    }
}
