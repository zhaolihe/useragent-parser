package com.tiger.useragent.browser;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/5
 */
public class Browser {
    public static final Browser DEFAULT_BROWSER = new Browser("-", "-", null, null);
    //品牌
    final String brand;
    final String family;
    final String major;
    final String minor;

    Browser(String brand, String family, String major, String minor) {
        this.brand = brand;
        this.family = family;
        this.major = major;
        this.minor = minor;
    }

    public static Browser getDefaultBrowser() {
        return DEFAULT_BROWSER;
    }

    public String getBrand() {
        return brand;
    }

    public String getFamily() {
        return family;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }
}