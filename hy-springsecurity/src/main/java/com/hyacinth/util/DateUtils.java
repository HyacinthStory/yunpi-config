package com.hyacinth.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Desc: 时间格式处理工具类
 * @Version: v1.0 2018/6/14 11:05
 * @Author: zfb
 * @JDK Version: 1.8.0_51
 */
public class DateUtils {

    /**
     * 时间格式转字串
     * @param date    时间
     * @param pattern 格式
     * @return 时间字串
     */
    public static String getDateString(Date date, String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
}
