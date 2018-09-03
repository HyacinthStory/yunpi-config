package com.hyacinth.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Class Description: 通用工具类
 *  1、引用对象判空
 * @Version v1.0 2018/6/14 21:42
 * @JDK version 1.8.0_51
 * @Author zfb
 */
public class XaUtils {
    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     * @param pObj 待检查对象
     * @return boolean 返回的布尔值
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        } else if (pObj.getClass().isArray()) {
            if (Array.getLength(pObj) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(Object pObj) {
        return !isEmpty(pObj);
    }
}
