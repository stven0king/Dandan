package com.dandan.love.common.logger.core;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午8:39
 * Description:
 */
public abstract class PrintStyle {

    private LogPrinter printer;

    public void setPrinter(LogPrinter printer) {
        this.printer = printer;
    }

    @Nullable
    protected abstract String beforePrint();

    @NonNull
    protected abstract String printLog(String message, int line, int wholeLineCount);

    @Nullable
    protected abstract String afterPrint();

    public LogBuilder getSettings() {
        return printer.getLogBuilder();
    }

    public LogPrinter getPrinter() {
        return printer;
    }
}

