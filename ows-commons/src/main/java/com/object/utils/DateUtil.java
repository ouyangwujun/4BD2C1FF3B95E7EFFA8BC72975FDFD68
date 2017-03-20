package com.object.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public final class DateUtil {

    private static final Object lock = new Object();

    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    /**
     * 根据Pattern获取SimpleDateFormat
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = sdfMap.get(pattern);
        if (simpleDateFormatThreadLocal == null) {
            synchronized (lock) {
                if (simpleDateFormatThreadLocal == null) {
                    simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, simpleDateFormatThreadLocal);
                }
            }
        }
        return simpleDateFormatThreadLocal.get();
    }

    /**
     * 方法说明：获得指定格式当前系统时间字符串
     *
     * @param pattern PatternType枚举
     * @return
     * @throws Exception
     */
    public static String getCDateString(PatternType pattern) throws Exception {
        SimpleDateFormat sf = getSdf(pattern.getPattern());
        return sf.format(new Date());
    }

    /**
     * 方法说明：获得指定格式当前系统时间字符串
     *
     * @return
     * @throws Exception
     */
    public static String getCurrentDateTimeOfStr() {
        String result = "";
        try {
            result = getCDateString(PatternType.DATE_V1_PATTERN);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return result;
    }

    /**
     * 方法说明：获得指定格式当前系统时间字符串 格式：yyyy-MM-dd HH:mm:ss
     *
     * @return
     * @throws Exception
     */
    public static String getCurrentDateTime() {
        String result = "";
        try {
            result = getCDateString(PatternType.DATE_DEF_PATTERN);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return result;
    }

    public static String getDateForLong(long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        SimpleDateFormat sdf = getSdf(PatternType.DATE_DEF_PATTERN.getPattern());
        return sdf.format(c.getTime());
    }

    public static String getDateForLong(PatternType pattern, long time) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        SimpleDateFormat sdf = getSdf(pattern.getPattern());
        return sdf.format(c.getTime());
    }
    /**
     * 解析Date字符串为Date
     * @param dataStr
     * @param pattern
     * @return
     * @throws Exception
     */
    public static Date parseDate(String dataStr, PatternType pattern) {
        if (StringUtils.isEmpty(dataStr)) {
            return null;
        }
        try {
            SimpleDateFormat df = getSdf(pattern.getPattern());
            return df.parse(dataStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * Pattern Type Enum
     */
    enum PatternType{

        DATE_DEF_PATTERN("yyyy-MM-dd HH:mm:ss","2016-01-01 12:00:00"),

        DATE_V1_PATTERN("yyyyMMdd","20160101"),

        DATE_V2_PATTERN("yyyy-MM-dd","2016-01-01");

        private String pattern;

        private String patternDesc;

        PatternType(String pattern,String patternDesc){
            this.pattern = pattern;
            this.patternDesc = patternDesc;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public String getPatternDesc() {
            return patternDesc;
        }

        public void setPatternDesc(String patternDesc) {
            this.patternDesc = patternDesc;
        }
    }
}
