package com.dandan.love.config;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/11 下午10:38
 * Description:
 */
public class RetrofitInterfaceConfig {

    public final static String GANK_IO = "http://gank.io";
    //易源接口
    public final static String YIYUAN_API = "http://route.showapi.com";

    public final static String BAI_DU_IMAGE = "http://image.baidu.com";

    public final static String GANK_DATA = GANK_IO + "/api/data";

    public final static String GANK_CLASSIFY_URL = GANK_IO + "/api/data/{type}/{pagesize}/{pagenum}";

    public final static String BAIDU_IMAGE_SEARCH_URL =  BAI_DU_IMAGE + "/search/acjson";

    //花瓣福利
    public final static String YIYUAN_API_HUABAN = YIYUAN_API + "/819-1";

    public final static String GUSHICI_URL = "http://118.25.228.94/work/";

    public final static String GUSHICI_AUTHOR_POPULAR = "author/popular";
    public final static String GUSHICI_AUTHOR_DETAIL = "author/detail";
}
