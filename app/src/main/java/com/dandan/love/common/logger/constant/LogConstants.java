package com.dandan.love.common.logger.constant;

import com.dandan.love.config.FileConfig;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/18 下午8:39
 * Description:
 */
public class LogConstants {
    public static final String LOG_TAG_PREFIX = "dandan";
    public static final String TIME_FORMAT_STR_y_M_d_H_m_s_S = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String TIME_FORMAT_STR_yyyyMMdd = "yyyyMMdd";
    public static final String LOG_FILE_DIR = FileConfig.DIR_ROOT + "/Log/";
    public static final String LOG_FILE_PATH = FileConfig.DIR_ROOT + "/Log//Log_%s_%s.txt";
    public static final String LOG_FILE_NAME_FORMAT = "%s %s-%s/%s %s/%s %s";


}
