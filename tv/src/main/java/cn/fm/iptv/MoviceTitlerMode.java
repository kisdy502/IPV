package cn.fm.iptv;

/**
 * Created by Administrator on 2019/1/23.
 */

public class MoviceTitlerMode {

    String id;
    String name;

    public MoviceTitlerMode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
