package com.tiger.useragent;

import static com.tiger.useragent.Constant.DEFAULT_VALUE;

/**
 * com.tiger.useragent
 * author : zhaolihe
 * email : dayingzhaolihe@126.com
 * date : 2017/5/9
 */
class Device {
    public static final Device DEFAULT_PC_SCREEN = new Device("PC", DEFAULT_VALUE, DeviceType.PC, false, DEFAULT_VALUE);
    public static final Device DEFAULT_PHONE_SCREEN = new Device(DEFAULT_VALUE, DEFAULT_VALUE, DeviceType.Phone, true, DEFAULT_VALUE);
    public static final Device DEFAULT_TV = new Device(DEFAULT_VALUE, DEFAULT_VALUE, DeviceType.TV, false, DEFAULT_VALUE);

    public final String brand;
    public final String family;
    public final DeviceType deviceType;
    public final boolean isMobile;

    public final String screenSize;

    Device(String brand, String family, DeviceType deviceType, boolean isMobile) {
        this.brand = brand;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
        this.screenSize = "";
    }

    Device(String brand, String family, DeviceType deviceType, boolean isMobile, String screenSize) {
        this.brand = brand;
        this.family = family;
        this.deviceType = deviceType;
        this.isMobile = isMobile;
        this.screenSize = screenSize;
    }
}
