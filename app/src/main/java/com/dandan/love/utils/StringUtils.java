package com.dandan.love.utils;

/**
 * Created by Tanzhenxing
 * Date: 2018/9/3 下午3:20
 * Description:
 */
public class StringUtils {
    public static String removeSymbolContent(String content, char startSymbol, char endSymbol) {
        int startIndex = content.indexOf(startSymbol);
        int endIndex = content.indexOf(endSymbol);
        while (startIndex > 0 && endIndex > 0) {
            String symbolStr = content.substring(startIndex, endIndex + 1);
            content = content.replace(symbolStr, "");
            startIndex = content.indexOf(startSymbol);
            endIndex = content.indexOf(endSymbol);
        }
        return content;
    }
}
