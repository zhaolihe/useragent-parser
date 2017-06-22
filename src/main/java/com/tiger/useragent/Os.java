package com.tiger.useragent;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/8
 */
class Os {
    public static final Os DEFAULT_OS = new Os("-", "-", "0", "0", false, false);
    public final String brand;
    public final String family;
    public final String major;
    public final String minor;
    public final boolean isMobile;
    public final boolean isTv;

    public Os(String brand, String family, String major, String minor, boolean isMobile, boolean isTv) {
        this.brand = brand;
        this.family = family;
        this.major = major;
        this.minor = minor;
        this.isMobile = isMobile;
        this.isTv = isTv;
    }
}
