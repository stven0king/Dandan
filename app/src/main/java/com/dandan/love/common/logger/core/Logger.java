package com.dandan.love.common.logger.core;

import android.os.Environment;
import android.os.Process;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dandan.love.common.download.DExecutors;
import com.dandan.love.common.logger.constant.LogConstants;
import com.dandan.love.common.logger.parser.ObjParser;
import com.dandan.love.common.logger.parser.XmlJsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午8:39
 * Description:
 */
public class Logger {
    /**
     * 日志开关
     */
    private static boolean DEBUG = true;
    public static final int STACK_OFFSET = 9;
    private static LogPrinter printer;

    // @formatter:off
    protected Logger () {
    }
    // @formatter:on

    public static void initialize(LogBuilder logBuilder) {
        PrintStyle style = logBuilder.style;
        if (style == null) {
            style = new LogPrintStyle();
            logBuilder.logPrintStyle(style);
        }
        printer = new LogPrinter(logBuilder);
        style.setPrinter(printer);
        Timber.plant(printer);
    }

    /**
     * the dEBUG to set
     */
    public static void setDebug (boolean d) {
        DEBUG = d;
    }

    /**
     * @param priority one of
     *                 {@link Log#VERBOSE},
     *                 {@link Log#DEBUG},
     *                 {@link Log#INFO},
     *                 {@link Log#WARN},
     *                 {@link Log#ERROR}
     */
    public static void openLog(int priority) {
        changeLogLev(priority);
    }

    /**
     * @param priority one of
     *                 {@link Log#VERBOSE},
     *                 {@link Log#DEBUG},
     *                 {@link Log#INFO},
     *                 {@link Log#WARN},
     *                 {@link Log#ERROR}
     */
    public static void changeLogLev(int priority) {
        printer.getLogBuilder().logPriority(priority).build();
    }

    public static void closeLog() {
        printer.getLogBuilder().logPriority(Log.ASSERT);
    }

    public static LogBuilder getBuild() {
        return printer.getLogBuilder();
    }




    /**
     * info级别日志输出
     */
    public static void ti (String tag, Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.tag(tag);
        Timber.v(msg);
    }

    /**
     * info日志输出简易调用
     */
    public static void i (Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.i(msg, args);
    }

    public static void i(String msg){
        if (!DEBUG) {
            return;
        }
        Timber.i(msg,new Object());
    }

    /**
     * 打印debug级别的日志
     *
     * @author liam.zhuang(庄乾六)
     */
    public static void td (String tag, Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.tag(tag);
        Timber.d(msg);
    }

    /**
     * debug日志输出简易调用
     */
    public static void d (Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.d(msg, args);
    }

    public static void d(String msg){
        if (!DEBUG) {
            return;
        }
        Timber.d(msg,new Object());
    }


    /**
     * warning级别日志调用
     */
    public static void tw (String tag, Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.tag(tag);
        Timber.w(msg);
    }

    /**
     * warning日志输出简易调用
     */
    public static void w (Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.w(msg, args);
    }

    public static void w(String msg){
        if (!DEBUG) {
            return;
        }
        Timber.w(msg,new Object());
    }

    /**
     * 打印Error日志
     *
     * @author lijc e:(这里用一句话描述这个方法的作用). <br/>
     */
    public static void te (String tag, Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        Timber.tag(tag);
        Timber.e(msg);
    }

    /**
     * error日志输出简易调用
     */
    public static void e (Object... args) {
        if (!DEBUG) {
            return;
        }
        String msg = getMsg(args);
        msg = handleNullMsg(msg);
        Timber.e(msg, args);
    }

    public static void e(String msg){
        if (!DEBUG) {
            return;
        }
        Timber.e(msg,new Object());
    }

    /**
     * 统一的日志内容拼接方法
     *
     * @author lijc getMsg:(这里用一句话描述这个方法的作用). <br/>
     */
    private static String getMsg (Object[] args) {
        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            Object obj = args[i];
            if (obj == null) {
                msg.append("null");
            } else if (obj instanceof Throwable) {
                msg.append(getStackTraceString(((Throwable) obj)));
            } else {
                msg.append(obj.toString());
            }
            msg.append(" ");
        }
        return msg.toString();
    }

    /**
     * 将堆栈信息转化为字符串
     *
     * @author lijc getStackTraceString:(这里用一句话描述这个方法的作用). <br/>
     */
    public static String getStackTraceString (Throwable e) {
        try {
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            printWriter.append(e.getMessage());
            e.printStackTrace(printWriter);
            Log.getStackTraceString(e);
            Throwable cause = e.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            String msg = result.toString();
            printWriter.close();
            return msg;
        } catch (Exception ex) {
            return "";
        }
    }


    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        Timber.d(XmlJsonParser.json(json));
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        Timber.d(XmlJsonParser.xml(xml));
    }

    /**
     * Formats the json content and print it
     *
     * @param object Bean,Array,Collection,Map,Pojo and so on
     */
    public static void object(Object object) {
        Timber.d(ObjParser.parseObj(object));
    }

    /**
     * 插入自定义的tree
     */
    public static void plant(Timber.Tree tree) {
        Timber.plant(tree);
    }

    /**
     * 清空所有tree
     */
    public static void uprootAll() {
        Timber.uprootAll();
    }

    /**
     * Timber will swallow message if it's null and there's no throwable.
     */
    @NonNull
    private static String handleNullMsg(String message) {
        if (message == null) {
            message = "null";
        }
        return message;
    }


    public static void WriteToFile(final String level , final String tag , final Throwable throwable,
                                   final String formater , final Object... args){

        Runnable logtoFileTask = new Runnable() {
            @Override
            public void run () {
                logToFileInner(level,tag,throwable,formater,args);
            }
        };

        DExecutors.getInstance().submit(logtoFileTask);

    }

    /**
     * 日志内容写入文件
     * @param level
     * @param tag
     * @param tr
     * @param format
     * @param args
     */
    private static void logToFileInner(String level, String tag, Throwable tr, String format, Object[] args) {
        PrintWriter writer = null;
        try {
            File logFile = getLogFile();
            File sDir = new File(Environment.getExternalStorageDirectory(), LogConstants.LOG_FILE_DIR);
            if (!( sDir.exists() && sDir.isDirectory())) {
                return;
            }
            //超过大小限制删除
            if (printer.getLogFileMaxSize() > 0){
                if (logFile.length() > printer.getLogFileMaxSize()) {
                    logFile.delete();
                }
                writer = new PrintWriter(new FileWriter(logFile, true));
                String msg = String.format(format, args);
                SimpleDateFormat sFormat = new SimpleDateFormat(LogConstants.TIME_FORMAT_STR_y_M_d_H_m_s_S);
                String log = String.format(LogConstants.LOG_FILE_NAME_FORMAT, sFormat.format(new Date()), Process
                        .myPid(), Process.myUid(), "?", level, tag, msg);
                writer.println(log);
                if (tr != null) {
                    tr.printStackTrace(writer);
                    writer.println();
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (Throwable e) {
                }
            }
        }
    }

    /**
     * 获取日志要写入的文件
     * @return 日志文件
     */
    private static File getLogFile() {
        SimpleDateFormat sFormat = new SimpleDateFormat(LogConstants.TIME_FORMAT_STR_yyyyMMdd);
        File file = new File(Environment.getExternalStorageDirectory(), String.format(LogConstants.LOG_FILE_PATH, sFormat.format(new Date()), Process.myPid()));
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return file;
    }

    public static void e2File(String tag , String formater , Object... args){
        e2File(tag,null,formater,args);
    }

    public static void e2File(String tag , Throwable throwable, String formater , Object... args){
        WriteToFile("E",tag,throwable,formater,args);
    }

    public static void d2File(String tag , String formater , Object... args){
        d2File(tag,null,formater,args);
    }

    public static void d2File(String tag , Throwable throwable, String formater , Object... args){
        WriteToFile("D",tag,throwable,formater,args);
    }

    public static void w2File(String tag , String formater , Object... args){
        w2File(tag,null,formater,args);
    }

    public static void w2File(String tag , Throwable throwable, String formater , Object... args){
        WriteToFile("W",tag,throwable,formater,args);
    }
    public static void v2File(String tag , String formater , Object... args){
        v2File(tag,null,formater,args);
    }

    public static void v2File(String tag , Throwable throwable, String formater , Object... args){
        WriteToFile("V",tag,throwable,formater,args);
    }

    public static void i2File(String tag , String formater , Object... args){
        i2File(tag,null,formater,args);
    }

    public static void i2File(String tag , Throwable throwable, String formater , Object... args){
        WriteToFile("I",tag,throwable,formater,args);
    }
    public static void wtf2File(String tag , String formater , Object... args){
        wtf2File(tag,null,formater,args);
    }

    public static void wtf2File(String tag , Throwable throwable, String formater , Object... args){
        WriteToFile("A",tag,throwable,formater,args);
    }

}
