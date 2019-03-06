package cn.fm.home;

import android.content.res.Resources;
import android.graphics.Rect;

import cn.fm.App;
import cn.fm.ipv.R;

/**
 * Created by Administrator on 2019/2/15.
 */

public class ItemHelper {

    public final static String BLOCK_TYPE_1_145 = "1_145";
    public final static String BLOCK_TYPE_1_383 = "1_383";
    public final static String BLOCK_TYPE_2_260 = "2_260";
    public final static String BLOCK_TYPE_3_260 = "3_260";
    public final static String BLOCK_TYPE_3_330 = "3_330";
    public final static String BLOCK_TYPE_4_204 = "4_204";
    public final static String BLOCK_TYPE_5_248 = "5_248";
    public final static String BLOCK_TYPE_6_344 = "6_344";
    public final static String BLOCK_TYPE_6_360 = "6_360";

    public static int[] getSizeByBlockType(String blockType, int spanSize) {
        int[] layoutSize = new int[]{
                0, 0
        };
        final Resources resources = App.getInstance().getResources();

        switch (blockType) {
            case BLOCK_TYPE_1_145:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_1609);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_145);
                break;
            case BLOCK_TYPE_1_383:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_1609);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_383);
                break;
            case BLOCK_TYPE_2_260:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_826);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_260);
                break;
            case BLOCK_TYPE_3_260:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_535);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_260);
                break;
            case BLOCK_TYPE_3_330:
                if (spanSize == 1) {
                    layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_553);
                    layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_330);
                } else if (spanSize == 2) {
                    layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_495);
                    layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_702);
                }
                break;
            case BLOCK_TYPE_4_204:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_391);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_204);
                break;
            case BLOCK_TYPE_5_248:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_224);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_248);
                break;
            case BLOCK_TYPE_6_344:
                layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_246);
                layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_344);
                break;
            case BLOCK_TYPE_6_360:
                if (spanSize == 4) {
                    layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_1112);
                    layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_360);
                } else if (spanSize == 2) {
                    layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_535);
                    layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_360);
                } else if (spanSize == 1) {
                    layoutSize[0] = resources.getDimensionPixelOffset(R.dimen.size_246);
                    layoutSize[1] = resources.getDimensionPixelOffset(R.dimen.size_344);
                }
                break;
        }
        return layoutSize;
    }

    public static Rect getPaddingByBlockType(String blockType) {
        Rect rect = new Rect();
        int l = 0, t = 0, r = 0, b = 0;
        final Resources resources = App.getInstance().getResources();

        switch (blockType) {
            case BLOCK_TYPE_1_145:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_4);
                t =  resources.getDimensionPixelOffset(R.dimen.size_48);
                b = resources.getDimensionPixelOffset(R.dimen.size_12);
                break;
            case BLOCK_TYPE_1_383:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_2_260:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_3_260:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_3_330:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_4_204:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_5_248:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_6_344:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
            case BLOCK_TYPE_6_360:
                l = r = resources.getDimensionPixelOffset(R.dimen.size_16);
                t = b = resources.getDimensionPixelOffset(R.dimen.size_16);
                break;
        }
        rect.set(l, t, r, b);
        return rect;
    }
}
