package com.sxs.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 序列化为JSON字符串
     * @param obj obj
     * @return {@link String}
     */
    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        String result = null;
        try {
            result = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 解析对象
     * 反序列化为Object
     *
     * @param clazz   clazz
     * @param jsonStr json str
     * @return {@link T}
     */
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr) || clazz == null) {
            return null;
        }
        T t = null;
        try {
            t = objectMapper.readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 解析List
     * 反序列化为List集合
     *
     * @param clazz       clazz
     * @param listJsonStr 列表json str
     * @return {@link List}<{@link T}>
     */
    public static <T> List<T> parseList(String listJsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(listJsonStr) || clazz == null) {
            return Collections.emptyList();
        }
        List<T> list = Collections.emptyList();
        try {
            list = objectMapper.readValue(listJsonStr, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 解析Map
     * 反序列化为Map集合
     * @param mapJsonStr 地图json str
     * @param kClazz     k clazz
     * @param vClazz     v clazz
     * @return {@link Map}<{@link K}, {@link V}>
     */
    public static <K,V> Map<K,V> parseMap(String mapJsonStr, Class<K> kClazz, Class<V> vClazz) {
        if (StringUtils.isBlank(mapJsonStr) || kClazz == null || vClazz == null) {
            return Collections.emptyMap();
        }
        Map<K,V> map = Collections.EMPTY_MAP;
        try {
            map = objectMapper.readValue(mapJsonStr, objectMapper.getTypeFactory().constructParametricType(Map.class, kClazz, vClazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
}
