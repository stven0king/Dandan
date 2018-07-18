package com.dandan.love.config;

import java.io.File;

public interface FileConfig {
    String NETWORK_CACHE = "dandanCache";

    String GLIDE_IMAGE_CACHE = "imageCache";

    //SD文件存储根目录
    String DIR_ROOT = "dandanlove";

    String IMAGE_DOWNLOAD_DISK_DIR = GLIDE_IMAGE_CACHE;

    String CRASH_LOG_FILE_NAME = "carshlog";
}
