package com.dandan.love.common.logger.core;

import android.util.Log;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午8:39
 * Description:
 */
public class LogBuilder {

    public int methodOffset = 0;

    public boolean showMethodLink = true;

    public boolean showThreadInfo = false;

    public String tagPrefix = null;

    public String globalTag = null;

    /**
     * 设置日志文件大小上限
     */
    private long logFileMaxSize = 8 * 1024 * 1024 ;


    int priority = Log.VERBOSE;

    public PrintStyle style;


    public static LogBuilder create() {
        return new LogBuilder();
    }

    public LogBuilder methodOffset(int methodOffset) {
        this.methodOffset = methodOffset;
        return this;
    }

    public LogBuilder showThreadInfo(boolean showThreadInfo) {
        this.showThreadInfo = showThreadInfo;
        return this;
    }

    public LogBuilder showMethodLink(boolean showMethodLink) {
        this.showMethodLink = showMethodLink;
        return this;
    }

    /**
     * @param priority one of
     *                 {@link Log#VERBOSE},
     *                 {@link Log#DEBUG},
     *                 {@link Log#INFO},
     *                 {@link Log#WARN},
     *                 {@link Log#ERROR}
     */
    public LogBuilder logPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public LogBuilder logPrintStyle(PrintStyle style) {
        this.style = style;
        return this;
    }

    public LogBuilder globalTag(String globalTag) {
        this.globalTag = globalTag;
        return this;
    }

    public LogBuilder tagPrefix(String prefix) {
        this.tagPrefix = prefix;
        return this;
    }

    public LogBuilder build() {
        return this;
    }

    /**
     * 获取log文件大小上限
     * @return
     */
    public long getLogFileMaxSize () {
        return logFileMaxSize;
    }

    /**
     * 设置log文件大小上限
     * @param logFileMaxSize
     */
    public void setLogFileMaxSize (long logFileMaxSize) {
        this.logFileMaxSize = logFileMaxSize;
    }
}
