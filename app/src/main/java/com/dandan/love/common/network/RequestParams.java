package com.dandan.love.common.network;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Tanzhenxing
 * Date: 2018/7/12 下午11:05
 * Description:
 */
public class RequestParams implements Serializable{

    private static final long serialVersionUID = -5058191002054562555L;
    private Map<String, String> params = new ConcurrentHashMap<>();
    /**
     * Constructs a new empty {@code RequestParams} instance.
     */
    public RequestParams() {
    }


    /**
     * Constructs a new RequestParams instance containing the key/value string params from the
     * specified map.
     *
     * @param source the source key/value string map to add.
     */
    public RequestParams(Map<String, String> source) {
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Adds param with non-string value (e.g. Map, List, Set).
     *
     * @param key   the key name for the new param.
     * @param value the non-string value object for the new param.
     */
    public void put(String key, Object value) {
        if (key != null && value != null) {
            params.put(key, value.toString());
        }
    }

    /**
     * Adds a int value to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value int for the new param.
     */
    public void put(String key, int value) {
        if (key != null) {
            params.put(key, String.valueOf(value));
        }
    }

    /**
     * Adds a long value to the request.
     *
     * @param key   the key name for the new param.
     * @param value the value long for the new param.
     */
    public void put(String key, long value) {
        if (key != null) {
            params.put(key, String.valueOf(value));
        }
    }

    public void put(Map<String,String> map) {
        if (map != null && map.size() >0) {
            //fix bug https://bugly.qq.com/v2/crash/apps/900004945/issues/97932?pid=1
            for(Map.Entry<String,String> m:map.entrySet()){
                params.put(m.getKey(),m.getValue()==null?"":m.getValue());
            }
        }
    }

    /**
     * Removes a parameter from the request.
     *
     * @param key the key name for the parameter to remove.
     */
    public void remove(String key) {
        params.remove(key);
    }

    /**
     * Check if a parameter is defined.
     *
     * @param key the key name for the parameter to check existence.
     * @return Boolean
     */
    public boolean has(String key) {
        return params.get(key) != null;
    }

    public Map params() {
        return params;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (HashMap.Entry<String, String> entry : params.entrySet()) {
            if (result.length() > 0)
                result.append("&");
            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }
        return result.toString();
    }

}
