package cn.fm;

import android.content.Context;
import android.content.pm.PackageInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2019/2/21.
 */

public class CrashCollector implements Thread.UncaughtExceptionHandler {
    private final static String TAG = "CrashCollector";
    private static volatile CrashCollector mInstance = null;

    private Lock mLock = new ReentrantLock();
    private Condition mCondition = mLock.newCondition();

    private Context mContext;
    private String mDeviceInfo = null;
    private Thread.UncaughtExceptionHandler mOldHandler;

    private CrashCollector(Context context) {
        mContext = context;
        try { // ljf++ 极少数设备会报NullPointerException，暂时不做处理
            mDeviceInfo = getDeviceInfo(context);
            mOldHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static CrashCollector getInstance(Context context) {
        if (null == mInstance) {
            mInstance = new CrashCollector(context);
        }
        return mInstance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (null != ex) {
            new SaveLogThread(ex).start();

            mLock.lock();
            try {
                mCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mLock.unlock();
        }

        if (null != mOldHandler) {
            mOldHandler.uncaughtException(thread, ex);
        }
    }

    private class SaveLogThread extends Thread {
        private Throwable ex = null;

        public SaveLogThread(Throwable ex) {
            this.ex = ex;
        }

        @Override
        public void run() {
            saveCrash2sdcard();

            mLock.lock();
            mCondition.signalAll();
            mLock.unlock();
        }

        /**
         * 只保存崩溃日志到内部sdcard,不复制崩溃日志到U盘
         */
        private void saveCrash2sdcard() {
            String writeDir = mContext.getCacheDir().toString();
            if (null == writeDir) {
                return;
            }

            final String logFileName = "log";
            File writeFile = new File(writeDir, logFileName);
            if (!writeFile.getParentFile().canWrite()) {
                return;
            }
            writeStackTrace(mContext, writeFile, mDeviceInfo, this.ex);
        }
    }


    public String getDeviceInfo(Context context) {
        StringBuilder builder = new StringBuilder();
        final int level = android.os.Build.VERSION.SDK_INT;
        builder.append("Build.VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE + "\n");
        builder.append("Build.VERSION.SDK_INT: " + android.os.Build.VERSION.SDK_INT + "\n");
        if (level < 21) {
            builder.append("Build.CPU_ABI: " + android.os.Build.CPU_ABI + "\n");
            builder.append("Build.CPU_ABI2: " + android.os.Build.CPU_ABI2 + "\n");
        }
        builder.append("Build.BOARD: " + android.os.Build.BOARD + "\n");
        builder.append("Build.PRODUCT: " + android.os.Build.PRODUCT + "\n");
        builder.append("Build.MODEL: " + android.os.Build.MODEL + "\n");
        builder.append("Build.ID: " + android.os.Build.ID + "\n");
        builder.append("Build.HARDWARE: " + android.os.Build.HARDWARE + "\n");

        if (null == context.getPackageManager()) {
            return builder.toString();
        }

        if (null == context.getPackageName()) {
            return builder.toString();
        }

        PackageInfo pkinfo = null;
        try {
            pkinfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return builder.toString();
        }

        if (null != pkinfo) {
            builder.append("packageName: " + context.getPackageName() + "\n");
            builder.append("versionCode: " + pkinfo.versionCode + "\n");
            builder.append("versionName: " + pkinfo.versionName + "\n");
        }
        return builder.toString();
    }

    public boolean writeStackTrace(Context context, File logFile, String deviceInfo, Throwable ex) {
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        if (!logFile.exists() || !logFile.canWrite()) {
            return false;
        }

        FileOutputStream os = null;
        OutputStreamWriter ow = null;
        BufferedWriter writer = null;
        try {
            os = new FileOutputStream(logFile);
            ow = new OutputStreamWriter(os, "utf-8");
            writer = new BufferedWriter(ow);

            // java 标准格式的stack trace
            writer.write("===========printStackTrace()============\n");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw, true);
            ex.printStackTrace(pw);
            writer.write(sw.getBuffer().toString());
            writer.flush();

            // 手动答应的stack trace, 防止java标准的没打印成功
            writer.write("\n\n===========next============\n");
            writer.flush();
            writer.write(ex.getMessage() + "\n");
            writer.flush();
            StackTraceElement[] elements = ex.getStackTrace();
            if (null != elements) {
                for (StackTraceElement e : elements) {
                    writer.write(e.toString() + '\n');
                }
                writer.flush();
            }
            writer.write("Cause by " + ex.getCause() + "\n");
            writer.flush();

            writer.write("\n\n===========device info============\n");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            writer.write("Local time: " + df.format(Calendar.getInstance().getTime()) + "\n");
            writer.flush();

            writer.write(deviceInfo);
            writer.flush();
        } catch (IOException io) {
            io.printStackTrace();
            return false;
        } finally {
            try {
                if (null != writer) {
                    writer.close();
                    writer = null;
                }
                if (null != ow) {
                    ow.close();
                    ow = null;
                }
                if (null != os) {
                    os.close();
                    os = null;
                }

                // set permission
                logFile.setReadable(true, false);
            } catch (IOException ignore) {
                ignore.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
