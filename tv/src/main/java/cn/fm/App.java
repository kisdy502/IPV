package cn.fm;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Administrator on 2019/2/15.
 */

public class App extends Application {

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashCollector.getInstance(this);
        printfTvInfo(this);
    }


    private void printfTvInfo(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metric);
        // 屏幕宽度（像素）
        int width = metric.widthPixels;
        // 屏幕高度（像素）
        int height = metric.heightPixels;
        // 屏幕密度（1.0 / 1.5 / 2.0）
        float density = metric.density;
        // 屏幕密度DPI（160 / 240 / 320）
        int densityDpi = metric.densityDpi;
        StringBuilder sb = new StringBuilder();
        sb.append("System Info\n").append("机顶盒型号:").append(android.os.Build.MODEL).append("\n")
                .append("SDK版本:").append(android.os.Build.VERSION.SDK_INT).append("\n")
                .append("系统版本:").append(android.os.Build.VERSION.RELEASE).append("\n")
                .append("屏幕宽度").append(width).append("px\n")
                .append("屏幕高度").append(height).append("px\n")
                .append("屏幕密度").append(densityDpi).append("DPI\n");
        Log.d("test", sb.toString());
    }
}
