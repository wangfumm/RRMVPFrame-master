package com.hansintelligent.rrrmvpframework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * DateUtil
 * Created by wangfu on 2018/5/17.
 */
public class DateUtil {

    public DateUtil() {
    }


    /**
     * 枚举日期格式
     */
    public enum DatePattern {

        /**
         * 格式：yyyy-MM-dd HH:mm:ss
         */
        ALL_TIME {
            public String getValue() {
                return "yyyy-MM-dd HH:mm:ss";
            }
        },


        /**
         * 格式：yyyy-MM-dd HH:mm
         */
        UNTILL_MINUTE {
            public String getValue() {
                return "yyyy-MM-dd HH:mm";
            }
        },


        /**
         * 格式：yyyy-MM-dd HH
         */
        UNTILL_HOUR {
            public String getValue() {
                return "yyyy-MM-dd HH";
            }
        },

        /**
         * 格式：yyyy-MM-dd
         */
        UNTILL_DAY {
            public String getValue() {
                return "yyyy-MM-dd";
            }
        },


        /**
         * 格式：yyyy-MM
         */
        UNTIll_MONTH {
            public String getValue() {
                return "yyyy-MM";
            }
        },


        /**
         * 格式：yyyy
         */
        ONLY_YEAR {
            public String getValue() {
                return "yyyy";
            }
        },

        /**
         * 格式：HH:mm:ss
         */
        ONLY_TIME {
            public String getValue() {
                return "HH:mm:ss";
            }
        },

        /**
         * 格式：HH:mm:ss
         */
        ONLY_HOUR_MINUTE {
            public String getValue() {
                return "HH:mm";
            }
        };


        public abstract String getValue();

    }


    /**
     * 获取当前时间
     *
     * @param datePattern
     * @return
     */
    public static String getCurTime(DatePattern datePattern) {
        String dateString = "";
        try {
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern.getValue(), Locale.CHINA);
            dateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 将一个日期字符串转成Date
     *
     * @param dateString
     * @param pattern
     * @return
     */
    public static Date stringToDate(String dateString, DatePattern pattern) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 将日期转成字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String dateToString(Date date, DatePattern pattern) {
        String dateString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern.getValue(), Locale.CHINA);
            dateString = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateString;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static int getCurDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getCurMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 获取当前年份
     *
     * @return
     */
    public static int getCurYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }


    /**
     * 获取本月的天数
     *
     * @return
     */
    public static int getDaysOfCurMonth() {
        Calendar calendar = Calendar.getInstance();
        return daysOfMonth(calendar.get(Calendar.YEAR), calendar.get(Calendar.DATE) + 1);
    }


    /**
     * 获取指定月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int daysOfMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    /**
     * 获取指定日期是周几
     *
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (weekIndex < 0) {
            weekIndex = 0;
        }
        return weekDays[weekIndex];
    }

    /**
     * 获取指定日期的周序列号
     *
     * @param date
     * @return
     */
    public static int getWeekIndexOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_WEEK);
        if (index == 1) {
            return 7;
        }
        return --index;
    }


}
