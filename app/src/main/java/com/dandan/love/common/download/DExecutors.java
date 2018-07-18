package com.dandan.love.common.download;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DExecutors {
    private static final int corePoolSize = 10 ;
    private static final int maximumPoolSize = 20 ;
    private static final long keepAliveTime = 60 ;
    private static final BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>() ;

    private ExecutorService executor;

    public static DExecutors getInstance() {
        return Singleton.INSTANCE;
    }

    public static class Singleton {
        public static DExecutors INSTANCE = new DExecutors();
    }

    public void submit(Runnable run) {
        if (null == executor) {
            executor = new ThreadPoolExecutor(
                    corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
        }
        this.executor.execute(run);
    }
}