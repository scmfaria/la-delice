package br.com.ladelice.util;

import android.content.Context;
import android.os.AsyncTask;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class Utilities {

    public static DisplayImageOptions getDisplayImageOptions(){
        DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        return displayImageOptions;
    }

    public static void loadImageLoaderConfigs(Context context){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .defaultDisplayImageOptions(getDisplayImageOptions())
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .diskCacheSize(150 * 1024 * 1024)
                .memoryCacheSize(25 * 1024 * 1024)
                .threadPoolSize(3)
                .taskExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init(config);
    }
}
