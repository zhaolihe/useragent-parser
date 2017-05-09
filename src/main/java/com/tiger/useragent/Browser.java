package com.tiger.useragent;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/5
 */
class Browser {
    public static final Browser DEFAULT_BROWSER = new Browser("-","-",null,null);
    public final String brand; //品牌
    public final String family;
    public final String major;
    public final String minor;

    Browser(String brand,String family,String major,String minor){
        this.brand = brand;
        this.family = family;
        this.major = major;
        this.minor = minor;
    }
}