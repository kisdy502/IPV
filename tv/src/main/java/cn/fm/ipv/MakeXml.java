package cn.fm.ipv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by SDT13411 on 2018/1/12.
 */

public class MakeXml {

    private final static String rootPath = "D:\\layoutroot\\values-{0}x{1}\\";

    //以1920*1080电视分辨率为标准
    private final static float dw = 1920f;
    private final static float dh = 1080f;

    private final static String WTemplate = "   <dimen name=\"size_{0}\">{1}px</dimen>\n";


    //电视机,OTT盒子主流分辨率
    public static void main(String[] args) {
        makeString(800, 480);
        makeString(1184, 720);
        makeString(1196, 720);
        makeString(1280, 720);
        makeString(1280, 672);
        makeString(1024, 768);
        makeString(1280, 800);
        makeString(1812, 1080);
        makeString(1920, 1080);
        makeString(2560, 1440);
    }

    public static void makeString(int width, int height) {

        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        sb.append("<resources>\n");
        float cellw = width / dw;
        for (int i = 1; i <= 1920; i++) {
            sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
                    change(cellw * i) + ""));
        }
//        sb.append(WTemplate.replace("{0}", "1920").replace("{1}", width + ""));
        sb.append("</resources>");



        String path = rootPath.replace("{0}", width + "").replace("{1}", height + "");
        File rootFile = new File(path);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }
        File layoutWFile = new File(path + "dimens.xml");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(layoutWFile));
            pw.print(sb.toString());
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static float change(float a) {
        int temp = (int) (a * 100);
        return temp / 100f;
    }
}
